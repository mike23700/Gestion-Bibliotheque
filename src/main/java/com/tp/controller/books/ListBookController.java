package com.tp.controller.books;

import com.tp.dao.DAOFactory;
import com.tp.model.Book;
import com.tp.model.User;
import com.tp.service.BookService;
import com.tp.service.ReservationService;

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

    private BookService bookService;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.bookService = new BookService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null) {
            response.sendRedirect("login");
            return;
        }

        List<Book> books = new ArrayList<>();
        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");
        String filterType = request.getParameter("filterType");

        try {
            if (searchValue != null && !searchValue.trim().isEmpty()) {
                switch (searchType) {
                    case "title":
                        books = bookService.findByTitle(searchValue);
                        break;
                    case "author":
                        books = bookService.findByAuthor(searchValue);
                        break;
                    case "year":
                            books = bookService.findByYear(Integer.parseInt(searchValue));
                        break;
                    case "category":
                        books = bookService.findByCategory(searchValue);
                        break;
                    default:
                        books = bookService.getAllBook();
                }
            } else if (filterType != null && !filterType.isEmpty()) {
                switch (filterType) {
                    case "recent":
                        books = bookService.findByRecent();
                        break;
                    case "old":
                        books = bookService.findByOld();
                        break;
                    case "disponible":
                        books = bookService.findByDisponible();
                        break;
                    case "emprunter":
                        books = bookService.findByEmprunter();
                        break;
                    case "popularity":
                        books = bookService.findByPopularity();
                        break;
                    default:
                        books = bookService.getAllBook();
                }
            } else {
                books = bookService.getAllBook();
            }


            request.setAttribute("listbooks", books);
            System.out.println("Connexion réussie à la BD et livres récupérés.");
            System.out.println("Nombre de livres: " + books.size());

        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération ou de la recherche des livres: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Impossible de charger la liste des livres.");
        }

        if ("MEMBER".equals(currentUser.getRole())) {
            request.setAttribute("activePage", "livres");
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookMember.jsp").forward(request, response);
        } else if ("ADMIN".equals(currentUser.getRole())) {
            request.setAttribute("activePage", "livres");
            this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/ListBookAdmin.jsp").forward(request, response);
        } else {
            System.err.println("Rôle utilisateur inconnu ou non géré: " + currentUser.getRole());
            response.sendRedirect("login");
        }
    }
}