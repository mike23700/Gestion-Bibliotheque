package com.tp.test;

import com.tp.dao.DAOFactory; // Remplacez com.tp.dao.DAOFactory par com.tp.DAO.DAOFactory si votre package est celui-ci
import com.tp.dao.interfaces.BookDAO; // Utilisez LivreDAO si c'est votre interface pour les livres, pas BookDAO

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DatabaseMetaData; // N'oubliez pas l'import pour DatabaseMetaData

public class BookTest {

    public static void main(String[] args) {
        Connection connection = null; // Initialisée à null
        try {
            // Obtenez l'instance unique de votre DAOFactory
            DAOFactory daoFactory = DAOFactory.getInstance();

            // --- C'EST LA LIGNE CLÉ MANQUANTE OU MAL PLACÉE ---
            // Tentez d'obtenir une connexion à partir de la fabrique
            //connection = daoFactory.getConnection(); // <-- Assigne la connexion réelle à la variable 'connection'
            // --- FIN DE LA LIGNE CLÉ MANQUANTE ---

            // Si vous avez besoin d'un BookDAO pour d'autres tests futurs, vous pouvez le garder.
            // Actuellement, il n'est pas utilisé pour le test de connexion lui-même.
            // LivreDAO livreDAO = daoFactory.getLivreDAO(); // Utilisez getLivreDAO() si c'est la bonne méthode

            // Vérifiez si la connexion est bien établie et ouverte
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connexion à la base de données réussie ! 🎉");

                // --- Vérification supplémentaire pour getMetaData() (ajoutée précédemment) ---
                DatabaseMetaData metaData = connection.getMetaData();
                if (metaData != null) {
                    System.out.println("Nom de la base de données : " + metaData.getDatabaseProductName());
                    System.out.println("URL de connexion : " + metaData.getURL());
                } else {
                    System.err.println("Avertissement : connection.getMetaData() a retourné null. Impossible d'obtenir les métadonnées de la base de données.");
                }
                // --- FIN Vérification supplémentaire ---

            } else {
                // Ce bloc est atteint si getConnection() renvoie null ou une connexion fermée
                System.err.println("Échec de la connexion à la base de données : La connexion est nulle ou fermée.");
            }

        } catch (SQLException e) {
            // Attrapez les exceptions SQL si la connexion échoue (problème DB)
            System.err.println("Erreur SQL lors de la tentative de connexion : " + e.getMessage());
            e.printStackTrace(); // Affiche la trace complète de l'erreur pour le débogage
        } catch (RuntimeException e) {
            // Attrapez les RuntimeException si DAOFactory échoue (ex: JNDI non trouvé)
            System.err.println("Erreur lors de l'initialisation de DAOFactory ou problème inattendu : " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Assurez-vous que la connexion est toujours fermée pour libérer les ressources
            // (avec un pool de connexions, cela la remet simplement dans le pool)
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connexion fermée (ou remise au pool).");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
