package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaceImpl.BookDAOImpl;
import com.tp.dao.interfaces.BookDAO;
import com.tp.model.Book;

import java.time.LocalDateTime;
import java.util.List;

public class BookService {

    private BookDAO bookDao;

    public BookService(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.bookDao = daoFactory.getBookDAO();
    }

    public void addBook(Book book) throws Exception {
        bookDao.AddBook(book);
    }

    public Book getBook(String book_id) throws Exception {
        return bookDao.getBook(book_id);
    }

    public List<Book> getAllBook() throws Exception {
        return bookDao.getAllBooks();
    }

    public void deleteBook(String book_id) throws Exception {
        bookDao.DeleteBook(book_id);
    }

    public List<Book> searchByTitle(String title) throws Exception {
        return bookDao.searchByTitle(title);
    }

    public void updateBook(Book book) throws Exception {
        bookDao.updateBook(book);
    }
}
