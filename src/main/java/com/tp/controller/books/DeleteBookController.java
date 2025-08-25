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

    BookService bookService = new BookService();

    /*
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/DeleteBook.jsp").forward(request,response);
    }
    */
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null )? null : (User) session.getAttribute("user");

        if(currentUser == null || !currentUser.getRole().equals("ADMIN")){
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String book_id = request.getParameter("book_id");

        try {
            bookService.deleteBook(book_id);
            request.getSession().setAttribute("succes","Book deleted successfully");
        } catch (Exception e) {
            request.getSession().setAttribute("error","Failed to delete book");
        }

        response.sendRedirect("listBooks");
    }
}
