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

    public static BookDAO getBookDAO() {
        return new BookDAOImpl();
    }

    /**
     * Retourne une instance du UserDAO.
     * @return L'instance du UserDAO.
     */
    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    /**
     * Retourne une instance du LoanDAO.
     * @return L'instance du LoanDAO.
     */
    public static LoanDAO getLoanDAO() {
        return new LoanDAOImpl();
    }

    /**
     * Retourne une instance du ReservationDAO.
     * @return L'instance du ReservationDAO.
     */
    public static ReservationDAO getReservationDAO() {
        return new ReservationDAOImpl();
    }

    /**
     * Retourne une instance du HistoryDAO.
     * @return L'instance du HistoryDAO.
     */
    public static HistoryDAO getHistoryDAO() {
        return new HistoryDAOImpl();
    }
}