package com.tp.controller.books;

import com.google.gson.Gson;
import com.tp.service.BookService;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet("/status")
public class UpdateStatusBookController extends HttpServlet {

    BookService bookService = new BookService();

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = request.getReader()) {

           Map<String, String> data = gson.fromJson(reader, Map.class);

            String bookId = data.get("bookId");
            String action = data.get("action");

            System.out.println("Données reçues du client:");
            System.out.println("ID du livre: " + bookId);
            System.out.println("Action demandée: " + action);

            boolean success = bookService.updateBookStatus(bookId, action);

        } catch (Exception e) {

            System.err.println("Erreur lors de la modification du statut du livre: " + e.getMessage());
            e.printStackTrace();

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\":false, \"message\":\"Erreur interne du serveur.\"}");
        }
    }
}
