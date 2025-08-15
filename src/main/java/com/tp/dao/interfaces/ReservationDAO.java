package com.tp.dao.interfaces;

import com.tp.models.Reservation;

import java.util.List;

public interface ReservationDAO {
    List<Reservation> getAllReservations() throws Exception;
}
