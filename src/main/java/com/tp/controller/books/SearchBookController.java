package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.User;
import com.tp.service.BookService;
import com.tp.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/searchBook")
@MultipartConfig
public class SearchBookController extends HttpServlet {
    private final BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null) ? null : (User) session.getAttribute("user");

        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Vous devez être connecté pour effectuer une recherche.");
            return;
        }

        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");

        System.out.println("DEBUG: getParameter() initial - searchType: " + searchType);
        System.out.println("DEBUG: getParameter() initial - searchValue: " + searchValue);

        List<Book> searchResults = new ArrayList<>();

        try {
            if (searchValue == null || searchValue.trim().isEmpty()) {
                searchResults = bookService.getAllBook();
            } else {
                switch (searchType) {
                    case "title":
                        searchResults = bookService.findByTitle(searchValue);
                        break;
                    case "year":
                        try {
                            searchResults = bookService.findByYear(Integer.parseInt(searchValue));
                        } catch (NumberFormatException e) {
                            System.err.println("Erreur de format pour l'année: " + searchValue);
                            searchResults = new ArrayList<>();
                        }
                        break;
                    case "category":
                        searchResults = bookService.findByCategory(searchValue);
                        break;
                    case "author":
                        searchResults = bookService.findByAuthor(searchValue);
                        break;
                    case "disponible":
                        searchResults = bookService.findByDisponible();
                        break;
                    case "emprunter":
                        searchResults = bookService.findByEmprunter();
                        break;
                    case "popularity":
                        searchResults = bookService.findByPopularity();
                        break;
                    default:
                        System.err.println("Type de recherche inconnu ou non géré: " + searchType);
                        searchResults = new ArrayList<>();
                }
            }


        } catch (Exception e) {
            System.err.println("Erreur inattendue lors de la recherche de livres: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Une erreur interne du serveur est survenue lors de la recherche.");
            return;
        }

        request.setAttribute("searchResults", searchResults);
        response.setContentType("text/html;charset=UTF-8");

        this.getServletContext().getRequestDispatcher("/WEB-INF/Vues/books/SearchBook.jsp").include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported for search. Please use POST.");
    }
}
