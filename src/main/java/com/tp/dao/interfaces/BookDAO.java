package com.tp.dao.interfaces;


import com.tp.model.Book;

import java.util.List;

public interface BookDAO {
    void AddBook(Book book) throws Exception;
    List<Book> searchByTitle(String title) throws Exception;
    List<Book> getAllBooks() throws Exception ;
    void DeleteBook(String book_id) throws Exception;
    void updateBook(Book book) throws Exception;
    Book getBook(String book_id) throws Exception;
}
