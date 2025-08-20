package com.tp.controller.reservation;

import com.tp.dao.DAOFactory;
import com.tp.service.ReservationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancelReservations")
public class CancelReservationController extends HttpServlet {

    private ReservationService reservationService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.reservationService = new ReservationService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reservationId = Integer.parseInt(request.getParameter("id"));

        boolean success = reservationService.cancelReservation(reservationId);

        if (success) {
            request.setAttribute("message", "Réservation annulée avec succès !");
        } else {
            request.setAttribute("error", "Erreur lors de l'annulation de la réservation.");
        }
        response.sendRedirect("memberListReservations");
    }
}
