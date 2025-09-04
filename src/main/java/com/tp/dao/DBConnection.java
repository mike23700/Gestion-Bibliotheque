package com.tp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pacha12345";

    private static Connection connection = null;
    private static DBConnection instance = null;

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur: Pilote JDBC introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur: Échec de la connexion à la base de données.");
            e.printStackTrace();
        }
        return connection;
    }

    public DBConnection getInstance(){
        if(instance == null){
            return new DBConnection();
        }
        return instance;
    }
}