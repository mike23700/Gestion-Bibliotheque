package com.tp.controller.books;

import com.tp.model.Book;
import com.tp.model.Loan;
import com.tp.model.User;
import com.tp.service.BookService;
import com.tp.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/manageBook")
public class SearchBookController extends HttpServlet {
    BookService bookService = new BookService();
    List<Book> books = new ArrayList<>();

    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session == null )? null : (User) session.getAttribute("user");

        String searchType = request.getParameter("searchtype");
        String search = request.getParameter("search");

        assert currentUser != null;

        switch (searchType){
            case "title":
                try {
                    bookService.findByTitle(search);
                    request.setAttribute("books",books);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "year":
                try {
                    bookService.findByYear(Integer.parseInt(search));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "category":
                try {
                    bookService.findByCategory(search);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "author":
                try {
                    bookService.findByAuthor(search);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.err.println("Impossible");
        }
    }
}
