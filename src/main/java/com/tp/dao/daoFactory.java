package com.tp.dao;


import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.UserDAO;
//import com.tp.dao.interfaces.EmpruntDAO;
//import com.tp.dao.interfaces.ReservationDAO;


import com.tp.dao.interfaceImpl.BookDAOImpl;
import com.tp.dao.interfaceImpl.UserDAOImpl;
//import com.tp.dao.interfaceImpl.EmpruntDAOImpl;
//import com.tp.dao.interfaceImpl.ReservationDAOImpl;


public class daoFactory {

    private static daoFactory instance;


    private DataSource dataSource;


    private daoFactory() {
        try {

            Context initialContext = new InitialContext();

            this.dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/ma_base_de_donnees");
            System.out.println("DAOFactory: Pool de connexions JNDI initialisé avec succès.");
        } catch (NamingException e) {

            System.err.println("Erreur JNDI critique: Impossible de trouver la ressource DataSource.");
            e.printStackTrace();
            throw new RuntimeException("Erreur d'initialisation de DAOFactory via JNDI", e);
        }
    }


    public static daoFactory getInstance() {
        if (instance == null) {
            instance = new daoFactory();
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }



    public BookDAO getBookDAO() {
        return new BookDAOImpl(this);
    }


    public UserDAO getUserDAO() {
        return new UserDAOImpl(this);
    }

    /*
    public EmpruntDAO getEmpruntDAO() {
        return new EmpruntDAOImpl(this);
    }
    */
    /*
    public ReservationDAO getReservationDAO() {
        return new ReservationDAOImpl(this);
    }
    */
}
