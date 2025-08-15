package com.tp.dao.interfaces;

import com.tp.models.Book;

import java.util.List;

public interface BookDAO {
    void AddBook(Book book) throws Exception;
    List<Book> searchByName(String name) throws Exception;
    List<Book> getAllBooks() throws Exception ;
    void DeleteBook(String id_book) throws Exception;
    void updateStudent(Book book) throws Exception;
    Book getBook(String id_book) throws Exception;
}
