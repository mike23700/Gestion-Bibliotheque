package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manageBook")
public class SearchBook extends HttpServlet {
    BookService bookService = new BookService();
    List<Book> books = new ArrayList<>();

    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {

        String title = request.getParameter("title");

        try {
            books = bookService.searchByTitle(title);
            request.setAttribute("books",books);
        }catch (Exception e ){
            System.out.println("erreur lors de la recherche");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/SearchBook.jsp").forward(request,response);
    }
}
