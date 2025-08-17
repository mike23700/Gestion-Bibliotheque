
package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


    private static final String URL = "jdbc:mysql://localhost:3306/personne?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "pacha12345";


    private final Connection connection;


    private daoFactory() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("DAOFactory: Connexion directe à la base de données établie (Singleton).");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur: Pilote JDBC non trouvé. Assurez-vous que mysql-connector-j est dans le classpath.");
            //e.printStackTrace();
            throw new RuntimeException("Impossible de charger le pilote JDBC.", e);
        } catch (SQLException e) {
            System.err.println("Erreur SQL critique lors de l'initialisation de DAOFactory: " + e.getMessage());
            //e.printStackTrace();
            throw new RuntimeException("Impossible d'établir la connexion à la base de données.", e);
        }
    }


    public static daoFactory getInstance() {
        if (instance == null) {
            instance = new daoFactory();
        }
        return instance;
    }


    public Connection getConnection() {
        return this.connection;
    }


    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("DAOFactory: Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            //e.printStackTrace();
        }
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
