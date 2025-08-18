package com.tp.dao;

import com.tp.dao.interfaceImpl.BookDAOImpl;
import com.tp.dao.interfaceImpl.HistoryDAOImpl;
import com.tp.dao.interfaceImpl.LoanDAOImpl;
import com.tp.dao.interfaceImpl.ReservationDAOImpl;
import com.tp.dao.interfaceImpl.UserDAOImpl;
import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.HistoryDAO;
import com.tp.dao.interfaces.LoanDAO;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.dao.interfaces.UserDAO;

public class DAOFactory {

    private DAOFactory() {
    }

    public  BookDAO getBookDAO() {
        return new BookDAOImpl(this);
    }

    public  UserDAO getUserDAO() {
        return new UserDAOImpl(this);
    }

    public  LoanDAO getLoanDAO() {
        return new LoanDAOImpl(this);
    }

    public  ReservationDAO getReservationDAO() {
        return new ReservationDAOImpl(this);
    }

    public HistoryDAO getHistoryDAO() {
        return new HistoryDAOImpl(this);
    }
}

