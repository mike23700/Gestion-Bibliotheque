package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.User;
import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listBooks")
public class ListBookController extends HttpServlet {
    BookService bookService = new BookService();
    List<Book> books = new ArrayList<>();

    protected void doGet(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        /*
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
         */

        try {
            books = bookService.getAllBook();
            request.setAttribute("listbooks",books);
            System.out.println("connexion reussis a la BD");
            System.out.println(books.size());
        } catch (Exception e) {
            System.out.println("Erreur lors de la recuperation des livres");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBook.jsp").forward(request,response);
    }
}
