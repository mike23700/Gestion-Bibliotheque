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

        /*
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        assert currentUser != null;
        if (currentUser.getRole().equals("MEMBER")) {
            try {
                books = bookService.getAllBook();
            } catch (Exception e) {
                System.out.println("Erreur lors de la recuperation des livres pour le member");
            }

            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookMember.jsp").forward(request,response);
        }
        */

        try {
            books = bookService.getAllBook();
            request.setAttribute("listbooks",books);
            System.out.println("connexion reussis a la BD");
            System.out.println(books.size());
        } catch (Exception e) {
            System.out.println("Erreur lors de la recuperation des livres pour l'admin");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookAdmin.jsp").forward(request,response);
    }
}
