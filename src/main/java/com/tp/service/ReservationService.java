package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.model.Reservation;

import java.util.List;
import java.time.LocalDateTime;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService(DAOFactory daoFactory) {
        this.reservationDAO = daoFactory.getReservationDAO();
    }

    public boolean createReservation(String userId, String bookId) {
        Reservation reservation = new Reservation(
                0,
                userId,
                bookId,
                LocalDateTime.now(),
                "ACTIVE"
        );
        return reservationDAO.addReservation(reservation);
    }


    public boolean cancelReservation(int reservationId) {
        Reservation reservation = reservationDAO.findById(reservationId);
        if (reservation == null) {
            System.err.println("Erreur: La réservation avec l'ID " + reservationId + " n'a pas été trouvée.");
            return false;
        }
        reservation.setStatus("CANCELLED");
        return reservationDAO.updateReservation(reservation);
    }


    public boolean fulfillReservation(int reservationId) {
        Reservation reservation = reservationDAO.findById(reservationId);
        if (reservation == null) {
            System.err.println("Erreur: La réservation avec l'ID " + reservationId + " n'a pas été trouvée.");
            return false;
        }
        reservation.setStatus("FULFILLED");
        return reservationDAO.updateReservation(reservation);
    }

    public List<Reservation> getReservationsByUserId(String userId) {
        return reservationDAO.findByUserId(userId);
    }


    public List<Reservation> getReservationsByBookId(String bookId) {
        return reservationDAO.findByBookId(bookId);
    }


    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public List<Reservation> getActiveReservations() {
        return reservationDAO.findByStatus("ACTIVE");
    }

    public List<Reservation> getCancelledReservations() {
        return reservationDAO.findByStatus("CANCELLED");
    }

    public List<Reservation> getFulfilledReservations() {
        return reservationDAO.findByStatus("FULFILLED");
    }
}