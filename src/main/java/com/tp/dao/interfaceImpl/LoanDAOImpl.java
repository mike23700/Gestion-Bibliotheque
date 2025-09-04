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
import java.util.List;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.time.LocalTime;

public class LoanDAOImpl implements LoanDAO {
    public LoanDAOImpl(DAOFactory daoFactory) {
    }

    @Override
    public boolean AddLoan(Loan loan) throws Exception {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO loans (loan_id, user_id, book_id, borrow_date, due_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, loan.getLoan_id());
            stmt.setString(2, loan.getUser_id());
            stmt.setString(3, loan.getBook_id());
            stmt.setTimestamp(4, Timestamp.valueOf(loan.getBorrow_date()));
            stmt.setTimestamp(5, Timestamp.valueOf(loan.getDue_date()));
            stmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Erreur lors de l'emprunt");
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public List<Loan> getAllLoansByUser(String user_id) {
        List<Loan> loans = new ArrayList<>();

        String sql = " SELECT l.loan_id, l.user_id, l.book_id, l.borrow_date, l.due_date, l.return_date, b.title " +
                " FROM " +
                " loans l " +
                " INNER JOIN " +
                " books b ON l.book_id = b.book_id " +
                " WHERE " +
                " l.user_id = ? AND b.status = 'emprunté' ";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan(
                            rs.getString("loan_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("title"),
                            rs.getTimestamp("borrow_date").toLocalDateTime(),
                            rs.getTimestamp("due_date").toLocalDateTime(),
                            (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null
                    );
                    loans.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public List<Loan> getEveryLoanByUser(String user_id) {
        List<Loan> loans = new ArrayList<>();

        String sql = " SELECT l.loan_id, l.user_id,u.name, l.book_id, l.borrow_date, l.due_date, l.return_date, b.title " +
                " FROM " +
                " loans l "+
                " INNER JOIN "+
                " users u ON l.user_id = u.user_id "+
                " INNER JOIN "+
                " books b ON l.book_id = b.book_id WHERE " +
                " l.user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Loan loan = new Loan(
                            rs.getString("loan_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("title"),
                            rs.getString("name"),
                            rs.getTimestamp("borrow_date").toLocalDateTime(),
                            rs.getTimestamp("due_date").toLocalDateTime(),
                            (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null

                    );
                    loans.add(loan);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loans;
    }

    /*
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
     */

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        try {

            String sql = " SELECT L.loan_id, L.user_id , L.book_id , U.name, B.title, B.status , L.borrow_date, L.due_date, L.return_date "+
                    " FROM " +
                    " loans L "+
                    " INNER JOIN "+
                    " users U ON L.user_id = U.user_id "+
                    " INNER JOIN "+
                    " books B ON L.book_id = B.book_id  ORDER BY borrow_date DESC"
                    ;

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan loan = new Loan(
                        rs.getString("loan_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getTimestamp("borrow_date").toLocalDateTime(),
                        rs.getTimestamp("due_date").toLocalDateTime(),
                        (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts de l'utilisateur");
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public List<Loan> findByDate(LocalDateTime date) throws Exception {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        LocalDateTime startOfDay = date.with(LocalTime.MIN);

        LocalDateTime endOfDay = date.with(LocalTime.MAX);


        try {
            String sql = " SELECT L.loan_id, L.use_id, L.book_id, U.name, B.title, B.status L.borrow_date, L.due_date, L.return_date"+
                    "FROM " +
                    "loans L"+
                    "INNER JOIN"+
                    "users U ON L.user_id = U.user_id"+
                    "INNER JOIN"+
                    "books B ON L.book_id = B.book_id" +
                    "WHERE L.borrow_date BETWEEN ? AND ? ";

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
                        rs.getString("title"),
                        rs.getString("username"),
                        rs.getString("status"),
                        borrowDate,
                        dueDate,
                        rs.getTimestamp("return_date").toLocalDateTime()
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts");
        }
        return loans;
    }

    @Override
    public List<Loan> findByUsername(String user_name) throws Exception {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        try {
            String sql = " SELECT l.loan_id, l.user_id,u.name, l.book_id, l.borrow_date, l.due_date, l.return_date, b.title, b.status " +
                    " FROM " +
                    " loans l "+
                    " INNER JOIN "+
                    " users u ON l.user_id = u.user_id "+
                    " INNER JOIN "+
                    " books b ON l.book_id = b.book_id WHERE " +
                    " u.name = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user_name);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan loan = new Loan(
                        rs.getString("loan_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getTimestamp("borrow_date").toLocalDateTime(),
                        rs.getTimestamp("due_date").toLocalDateTime(),
                        (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts par username");
        }
        return loans;
    }

    @Override
    public List<Loan> findByBooktile(String book_title) throws Exception {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        try {

            String sql = " SELECT l.loan_id, l.user_id,u.name, l.book_id, l.borrow_date, l.due_date, l.return_date, b.title, b.status" +
                    " FROM " +
                    " loans l "+
                    " INNER JOIN "+
                    " users u ON l.user_id = u.user_id "+
                    " INNER JOIN "+
                    " books b ON l.book_id = b.book_id WHERE " +
                    "b.title = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book_title);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan loan = new Loan(
                        rs.getString("loan_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getTimestamp("borrow_date").toLocalDateTime(),
                        rs.getTimestamp("due_date").toLocalDateTime(),
                        (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts par titre de livre");
        }
        return loans;
    }

    @Override
    public List<Loan> findByDateAndByUser(LocalDateTime date, String user_id) throws Exception {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        LocalDateTime startOfDay = date.with(LocalTime.MIN);

        LocalDateTime endOfDay = date.with(LocalTime.MAX);


        try {
            String sql = " SELECT L.loan_id, L.user_id, L.book_id, B.title, B.status , L.borrow_date, L.due_date, L.return_date "+
                    " FROM " +
                    " loans L "+
                    " INNER JOIN "+
                    " users U ON L.user_id = U.user_id "+
                    " INNER JOIN "+
                    " books B ON L.book_id = B.book_id " +
                    " WHERE L.borrow_date BETWEEN ? AND ? AND U.user_id = ? ";

            stmt = connection.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(startOfDay));
            stmt.setTimestamp(2, Timestamp.valueOf(endOfDay));
            stmt.setString(3, user_id);

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
                        rs.getString("title"),
                        rs.getString("status"),
                        borrowDate,
                        dueDate,
                        rs.getTimestamp("return_date").toLocalDateTime()
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts");
        }
        return loans;
    }

    @Override
    public List<Loan> findByBooktitleAndByUser(String book_title, String user_id) throws Exception {
        List<Loan> loans = new ArrayList<>();
        Connection connection = DBConnection.getConnection();

        try {

            String sql = " SELECT L.loan_id, L.user_id, L.book_id, U.name, B.title, B.status , L.borrow_date, L.due_date, L.return_date "+
                    " FROM " +
                    " loans L "+
                    " INNER JOIN "+
                    " users U ON L.user_id = U.user_id "+
                    " INNER JOIN "+
                    " books B ON L.book_id = B.book_id " +
                    " WHERE B.title = ? AND U.user_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book_title);
            stmt.setString(2, user_id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Loan loan = new Loan(
                        rs.getString("loan_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("status"),
                        rs.getTimestamp("borrow_date").toLocalDateTime(),
                        rs.getTimestamp("due_date").toLocalDateTime(),
                        rs.getTimestamp("return_date").toLocalDateTime()
                );
                loans.add(loan);
            }
        }catch (Exception e){
            System.err.println("Erreur lors de la recuperation des emprunts par titre de livre");
        }
        return loans;
    }

    @Override
    public boolean updateLoanReturnDate(String loanId, LocalDateTime returnDate) {
        String sql = "UPDATE loans SET return_date = ? WHERE loan_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(returnDate));
            stmt.setString(2, loanId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Loan getLoanById(String loanId) {
        String sql = "SELECT * FROM loans WHERE loan_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, loanId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Loan(
                            rs.getString("loan_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getTimestamp("borrow_date").toLocalDateTime(),
                            rs.getTimestamp("due_date").toLocalDateTime(),
                            (rs.getTimestamp("return_date") != null) ? rs.getTimestamp("return_date").toLocalDateTime() : null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isBookBorrowedBy(String user_id, String book_id) {
        String query = "SELECT COUNT(*) FROM loans WHERE book_id = ? AND user_id = ? AND return_date IS NULL ";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book_id);
            stmt.setString(2, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la vérification de l'emprunt: " + e.getMessage());
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean countLoanByUser(String user_id) throws Exception {

        String query = " SELECT COUNT(*) " +
                "FROM " +
                " loans L " +
                " INNER JOIN "+
                " books B ON B.book_id = L.book_id "+
                "WHERE " +
                " L.user_id = ? AND B.status = 'emprunté' ";


        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user_id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 2;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la vérification de l'emprunt: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
