package com.tp.controller.books;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBookController extends HttpServlet {

    private BookService bookService;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.bookService = new BookService(daoFactory);
    }


    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null )? null : (User) session.getAttribute("user");

        if(currentUser == null || !currentUser.getRole().equals("ADMIN")){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String book_id = request.getParameter("id_book");

        try {
            bookService.deleteBook(book_id);
            session.setAttribute("succes","Livre supprimer avec Succes");
        } catch (Exception e) {
            session.setAttribute("error","Erreur lors de la suppression");
        }

        response.sendRedirect("listBooks");
    }

}
