package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.model.Reservation;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    public ReservationDAOImpl(DAOFactory daoFactory) {
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        return false;
    }

    @Override
    public boolean deleteReservation(Reservation reservation) {
        return false;
    }

    @Override
    public List<Reservation> getAllReservation() {
        return List.of();
    }
}
