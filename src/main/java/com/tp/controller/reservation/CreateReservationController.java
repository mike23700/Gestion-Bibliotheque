package com.tp.controller.reservation;

import com.tp.dao.DAOFactory;
import com.tp.service.ReservationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createReservation")
public class CreateReservationController extends HttpServlet {

    private ReservationService reservationService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.reservationService = new ReservationService(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");

        boolean success = reservationService.createReservation(userId, bookId);

        if (success) {
            request.getSession().setAttribute("message", "Réservation créée avec succès !");
            response.sendRedirect("memberListReservations");
        } else {
            request.setAttribute("error", "Erreur lors de la création de la réservation.");
            request.getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);
        }
    }
}
