package com.tp.controller.user;

import com.tp.dao.DAOFactory;
import com.tp.model.User;
import com.tp.service.BookService;
import com.tp.service.LoanService;
import com.tp.service.ReservationService;
import com.tp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/adminDashboard")
public class AdminDashboardController extends HttpServlet {

    private UserService userService;
    private ReservationService reservationService;
    private BookService bookService;
    private LoanService loanService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.userService = new UserService(daoFactory);
        this.reservationService = new ReservationService(daoFactory);
        bookService = new BookService();
        loanService = new LoanService();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            response.sendRedirect("login");
        }

        int memberCount = userService.countMembers();
        int reservationCount = reservationService.countReservations();

        int bookCount = 0;
        try {
            bookCount = bookService.getAllBook().size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int loanCount = 0;
        try {
            loanCount = loanService.getAllLoans().size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("bookCount", loanCount);
        request.setAttribute("loanCount", loanCount);
        request.setAttribute("memberCount", memberCount);
        request.setAttribute("reservationCount", reservationCount);

        request.getRequestDispatcher("/WEB-INF/Vues/admin/adminDashboard.jsp").forward(request, response); //push
    }
}
