package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.User;
import com.tp.model.generateID.GenerateIdBooks;
import com.tp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookController extends HttpServlet {

    BookService bookService = new BookService();
    Book book = null;
    GenerateIdBooks ID = new GenerateIdBooks();

    /*
    protected void doGet(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {
        this.getServletContext().getRequestDispatcher("WEB-INF/AddBook.jsp").forward(request,response);
    }
     */
    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        book.setId_Book(ID.generateID());
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        String yearString = request.getParameter("year");
        book.setYear(Integer.parseInt(yearString));
        book.setImage(request.getParameter("image"));
        book.setCategory(request.getParameter("category"));
        book.setDescription(request.getParameter("description"));
        book.setStatus(request.getParameter("status"));
        String loan_count = request.getParameter("loan_count");
        book.setLoan_count(Integer.parseInt(loan_count));

        try {
            bookService.addBook(book);
            request.getSession().setAttribute("succes","Book add successfully");
        } catch (Exception e) {
            request.getSession().setAttribute("error","Failed to delete book");
        }
        response.sendRedirect("listBooks");
    }
}
