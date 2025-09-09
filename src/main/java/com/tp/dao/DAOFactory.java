package com.tp.dao;

import com.tp.dao.interfaceImpl.BookDAOImpl;
import com.tp.dao.interfaceImpl.LoanDAOImpl;
import com.tp.dao.interfaceImpl.ReservationDAOImpl;
import com.tp.dao.interfaceImpl.UserDAOImpl;
import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.LoanDAO;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.dao.interfaces.UserDAO;

public class DAOFactory {

    private static DAOFactory instance = null;

    private DAOFactory() {
        String URL = "jdbc:mysql://localhost:3306/bibliotheque_db";
        String USERNAME = "root";
        String PASSWORD = "pacha12345";
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public  BookDAO getBookDAO() {return new BookDAOImpl(this);}

    public  UserDAO getUserDAO() {
        return new UserDAOImpl(this);
    }

    public  LoanDAO getLoanDAO() {
        return new LoanDAOImpl(this);
    }

    public  ReservationDAO getReservationDAO() {
        return new ReservationDAOImpl(this);
    }

}

