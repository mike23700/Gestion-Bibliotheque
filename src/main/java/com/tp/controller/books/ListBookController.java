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
    private BookService bookService = new BookService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
        
            response.sendRedirect("login");
            return;
            
        }

        List<Book> Allbooks = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        
        try {
            Allbooks = bookService.getAllBook();
            books = bookService.verifyBookStatus(Allbooks , currentUser.getUser_id());

            request.setAttribute("listbooks", books);
            System.out.println("connexion reussie a la BD et livres recuperes.");
            System.out.println("Nombre de livres: " + books.size());
        } catch (Exception e) {
            System.err.println("Erreur lors de la recuperation des livres: " + e.getMessage());
            e.printStackTrace();

            request.setAttribute("errorMessage", "Impossible de charger la liste des livres.");

        }
        

        if (currentUser.getRole().equals("MEMBER")) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookMember.jsp").forward(request, response);
            return;
        } else if (currentUser.getRole().equals("ADMIN")) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookAdmin.jsp").forward(request, response);
            return;
        } else {

            System.err.println("Rôle utilisateur inconnu ou non géré: " + currentUser.getRole());

            response.sendRedirect("login"); 
            return;
        }

    }
}
