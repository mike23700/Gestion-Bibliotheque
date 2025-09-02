package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.model.Reservation;
import com.tp.model.generateID.GenerateReservationID;

import java.util.List;
import java.time.LocalDateTime;

public class ReservationService {
    private final ReservationDAO reservationDAO;

    public ReservationService(DAOFactory daoFactory) {
        this.reservationDAO = daoFactory.getReservationDAO();
    }

    public boolean createReservation(String userId, String bookId) {

        GenerateReservationID generator = new GenerateReservationID();
        String reservationId = generator.generateID();

        Reservation reservation = new Reservation(
                reservationId,
                userId,
                bookId,
                LocalDateTime.now(),
                "ACTIVE"
        );
        return reservationDAO.addReservation(reservation);
    }

    public boolean updateReservationStatus(String reservationId, String newStatus) {
        Reservation reservation = reservationDAO.findById(reservationId);
        if (reservation != null) {
            reservation.setStatus(newStatus);
            return reservationDAO.updateReservation(reservation);
        }
        return false;
    }

    public boolean fulfillReservation(String reservationId) {
        return updateReservationStatus(reservationId, "FULFILLED");
    }

    public boolean cancelReservation(String reservationId) {
        return updateReservationStatus(reservationId, "CANCELLED");
    }

    public void cancelExpiredReservations() {
        List<Reservation> expiredReservations = reservationDAO.findExpiredReservations();
        for (Reservation reservation : expiredReservations) {
            boolean success = updateReservationStatus(reservation.getReservation_id(), "EXPIRED");
            if (success) {
                System.out.println("Réservation expirée et annulée : " + reservation.getReservation_id());
            }
        }
    }

    public List<Reservation> getReservationsByUserId(String userId) {
        return reservationDAO.findByUserId(userId);
    }

    public List<Reservation> getReservationsByUserName(String userName) {
        return reservationDAO.findByUserName(userName);
    }

    public List<Reservation> getReservationsByBookId(String bookId) {
        return reservationDAO.findByBookId(bookId);
    }

    public List<Reservation> getReservationsByBookName(String bookName) {
        return reservationDAO.findByBookName(bookName);
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

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationDAO.findByStatus(status);
    }

    public Reservation findById(String reservationId) {
        return reservationDAO.findById(reservationId);
    }

    public List<Reservation> getReservationsByUserIdAndBookName(String userId) {
        return reservationDAO.findByUserIdAndBookName(userId, "");
    }

    public List<Reservation> getActiveReservationsByUserId(String userId) {
        return reservationDAO.findActiveByUserId(userId);
    }

    public int countReservations() {
        return reservationDAO.countReservations();
    }
}