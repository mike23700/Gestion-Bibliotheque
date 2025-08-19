package com.tp.dao.interfaces;

import com.tp.model.Reservation;

import java.util.List;

public interface ReservationDAO {
    boolean addReservation(Reservation reservation);
    boolean deleteReservation(Reservation reservation);
    boolean updateReservation(Reservation reservation);
    Reservation find
    List<Reservation> getAllReservation() ;

}
