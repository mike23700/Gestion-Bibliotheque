package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;

import com.tp.dao.DBConnection;
import com.tp.dao.interfaces.BookDAO;
import com.tp.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private final DAOFactory daoFactory;

    public BookDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void AddBook(Book book) throws Exception {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "INSERT INTO books(book_id , title , author , year , image , category , description , status , loan_count ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getId_Book());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getYear());
            stmt.setString(5, book.getImage());
            stmt.setString(6, book.getCategory());
            stmt.setString(7, book.getDescription());
            stmt.setString(8, book.getStatus());
            stmt.setInt(9, book.getLoan_count());

            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur lors de l'enregistrement "+e.getMessage());
        }
    }

    @Override
    public List<Book> findByTitle(String title) throws Exception{
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE title = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByYear(int year) throws Exception {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE year = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, year);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) throws Exception {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE author = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, author);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByCategory(String category) throws Exception {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE category = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, category);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByDisponible() throws Exception {
        List<Book> books = new ArrayList<>();
        String Rendu = "disponible";
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE status = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Rendu);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByEmprunter() throws Exception {
        List<Book> books = new ArrayList<>();
        String Encour = "emprunte";
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE status = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Encour);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByPopularity() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books ORDER BY loan_count DESC LIMIT 5";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByRecent() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books ORDER BY year DESC ";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> findByOld() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books ORDER BY year ASC";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation "+e.getMessage());
        }
        return books;
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Book book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation "+e.getMessage());
        }
        return books;
    }

    @Override
    public void DeleteBook(String book_id) throws Exception {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "DELETE FROM books WHERE book_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book_id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur lors de la suppression "+e.getMessage());
        }
    }

    @Override
    public void updateBook(Book book) throws Exception {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "UPDATE books SET title = ?, author = ?, year = ?, image = ?, category = ?, description = ?, is_available = ?, loan_count = ? WHERE book_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setString(4, book.getImage());
            stmt.setString(5, book.getCategory());
            stmt.setString(6, book.getDescription());
            stmt.setString(7, book.getStatus());
            stmt.setInt(8, book.getLoan_count());
            stmt.setString(9, book.getId_Book());

            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erreur lors de la modification "+e.getMessage());
        }
    }

    @Override
    public Book getBook(String book_id) throws Exception {
        Book book = null;
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "SELECT * FROM books WHERE book_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                book = new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("image"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("loan_count"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la recuperation "+e.getMessage());
        }
        return book;
    }


    @Override
    public boolean updateBookStatus(String bookId, String status) {
        String query = "UPDATE books SET status = ? WHERE book_id = ?";
        boolean success = false;

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setString(2, bookId);

            int updated = stmt.executeUpdate();
            if (updated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du statut du livre : " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void AddLoanCountOfBook(String book_id) throws Exception {
        try {
            Connection connection = daoFactory.getConnection();
            String sql = "UPDATE books " +
                    " SET loan_count = loan_count + 1 "+
                    " WHERE book_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book_id);

            stmt.executeUpdate();
        }catch (Exception e){
            System.out.println("Erreur lors de l'incrementation");
        }
    }

}
