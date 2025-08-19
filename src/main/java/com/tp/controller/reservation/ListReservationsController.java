package com.tp.controller.reservation;


import com.tp.dao.DAOFactory;
import com.tp.model.Reservation;
import com.tp.service.ReservationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listReservations")
public class ListReservationsController extends HttpServlet {

    private ReservationService reservationService;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.reservationService = new ReservationService(daoFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = (String) request.getSession().getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            request.getSession().removeAttribute("message");
        }
        List<Reservation> reservations = reservationService.getAllReservations();
        request.setAttribute("reservations", reservations);
        request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
    }
}
