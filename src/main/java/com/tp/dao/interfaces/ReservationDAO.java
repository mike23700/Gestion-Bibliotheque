package com.tp.dao.interfaces;

import com.tp.model.History;
import com.tp.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationDAO {

    boolean addReservation(Reservation reservation);
    boolean updateReservation(Reservation reservation);
    Reservation findById(int reservationId);
    List<Reservation> findByUserId(String userId);
    List<Reservation> findByUserName(String name);
    List<Reservation> findByBookId(String bookId);
    List<Reservation> findByBookName(String bookName);
    List<Reservation> findByStatus(String status);
    List<Reservation> getAllReservations();
    List<Reservation> findByUserIdAndBookName(String userId, String bookName);
    //List<History> findByDate(LocalDateTime date);
}