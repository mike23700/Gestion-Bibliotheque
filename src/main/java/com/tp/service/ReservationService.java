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
                userId,
                bookId,
                LocalDateTime.now(),
                "ACTIVE"
        );
        return reservationDAO.addReservation(reservation);
    }

    public boolean updateReservationStatus(int reservationId, String newStatus) {
        Reservation reservation = reservationDAO.findById(reservationId);
        if (reservation != null) {
            reservation.setStatus(newStatus);
            return reservationDAO.updateReservation(reservation);
        }
        return false;
    }

    public boolean fulfillReservation(int reservationId) {
        return updateReservationStatus(reservationId, "FULFILLED");
    }

    public boolean cancelReservation(int reservationId) {
        return updateReservationStatus(reservationId, "CANCELLED");
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

    public Reservation findById(int reservationId) {
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