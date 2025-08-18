package com.tp.controller.books;

import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookController extends HttpServlet {

    BookService bookService = new BookService();

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteBook.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        String book_id = request.getParameter("book_id");

        try {
            bookService.deleteBook(book_id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
