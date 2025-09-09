package com.tp.dao;

import com.tp.dao.interfaceImpl.BookDAOImpl;
import com.tp.dao.interfaceImpl.LoanDAOImpl;
import com.tp.dao.interfaceImpl.ReservationDAOImpl;
import com.tp.dao.interfaceImpl.UserDAOImpl;
import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.LoanDAO;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.dao.interfaces.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static DAOFactory instance = null;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erreur: Pilote JDBC introuvable.", e);
        }
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

}

