package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;
import com.tp.dao.DBConnection;
import com.tp.dao.interfaces.LoanDAO;
import com.tp.model.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalTime;

public class LoanDAOImpl implements LoanDAO {
    public LoanDAOImpl(DAOFactory daoFactory) {
    }

    @Override
    public void AddLoan(Loan loan) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO loans (loan_id , user_id , book_id , return_date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, loan.getLoan_id());
            stmt.setString(2, loan.getUser_id());
            stmt.setString(3, loan.getBook_id());
            stmt.setDate(4, loan.getReturn_date());

            stmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Erreur lors de l'emprunt");
        }
    }

    @Override
    public void DeleteLoan(String loan_id) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "DELETE * FROM loans WHERE loan_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, loan_id);

            stmt.executeUpdate();
        }catch (Exception e){
            System.err.println("Erreur lors de la suppression du loan");
        }
    }

    @Override
    public List<Loan> getAllLoans() throws Exception {
        List<Loan> loans = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            /*
            String sql = "SELECT L.loan_id , U.name , B.title , L.borrow_date , L.due_date , L.return_date  " +
                    "FROM loans.L , users.U , books.B " +
                    "WHERE L.user_id = U.user_id AND L.book_id = B.book_id";
             */
            String sql = " SELECT" +
                           " L.loan_id, U.name, B.title, L.borrow_date, L.`due-date`, -- Toujours avec les backticks si le nom de colonne contient un tiret L.return_date"+
                       "FROM " +
                          "loans L"+
                       "INNER JOIN"+
                          "users U ON L.user_id = U.user_id"+
                       "INNER JOIN"+
                          "books B ON L.book_id = B.book_id";

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan loan = new Loan(
                        rs.getString("loan_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getTimestamp("borrow_date").toLocalDateTime(),
                        rs.getTimestamp("due_date").toLocalDateTime(),
                        rs.getDate("return_date")
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de ");
        }
        return loans;
    }

    @Override
    public List<Loan> searchLoan(LocalDateTime date) throws Exception {
            List<Loan> loans = new ArrayList<>();
            Connection connection = DBConnection.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            LocalDateTime startOfDay = date.with(LocalTime.MIN);

            LocalDateTime endOfDay = date.with(LocalTime.MAX);


            try {
                String sql = "SELECT L.loan_id, L.user_id, L.book_id, L.borrow_date, L.`due-date`, L.return_date " +
                        "FROM EMPRUNT L " + // Assurez-vous d'utiliser le nom correct de votre table d'emprunts
                        "WHERE L.borrow_date BETWEEN ? AND ?";

                stmt = connection.prepareStatement(sql);
                stmt.setTimestamp(1, Timestamp.valueOf(startOfDay));
                stmt.setTimestamp(2, Timestamp.valueOf(endOfDay));

                rs = stmt.executeQuery();

                while (rs.next()) {
                    Timestamp borrowTimestamp = rs.getTimestamp("borrow_date");
                    Timestamp dueTimestamp = rs.getTimestamp("due_date");

                    LocalDateTime borrowDate = (borrowTimestamp != null) ? borrowTimestamp.toLocalDateTime() : null;
                    LocalDateTime dueDate = (dueTimestamp != null) ? dueTimestamp.toLocalDateTime() : null;

                    Loan loan = new Loan(
                            rs.getString("loan_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            borrowDate,
                            dueDate,
                            rs.getDate("return_date")
                    );
                    loans.add(loan);
                }
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
                if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
                if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
            }
            return loans;
    }
}
