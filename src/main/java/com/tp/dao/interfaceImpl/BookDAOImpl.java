package com.tp.dao.interfaceImpl;

import com.tp.dao.daoFactory;
import com.tp.dao.interfaces.BookDAO;
import com.tp.model.Book;

import java.util.List;

public class BookDAOImpl implements BookDAO {

    daoFactory Daofactory;
    public BookDAOImpl(daoFactory daofactory){
        Daofactory = daofactory;
    }


    @Override
    public void AddBook(Book book) throws Exception {

    }

    @Override
    public List<Book> searchByName(String name) throws Exception {
        return List.of();
    }

    @Override
    public List<Book> getAllBooks() throws Exception {
        return List.of();
    }

    @Override
    public void DeleteBook(String id_book) throws Exception {

    }

    @Override
    public void updateStudent(Book book) throws Exception {

    }

    @Override
    public Book getBook(String id_book) throws Exception {
        return null;
    }
}
