package com.tp.dao.interfaceImpl;

import com.tp.dao.interfaces.ReservationDAO;
import com.tp.model.Reservation;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAllReservations() throws Exception {
        return List.of();
    }
}
