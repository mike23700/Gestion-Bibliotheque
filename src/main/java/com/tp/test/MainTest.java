package com.tp.test;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        System.out.println("\n=== TEST AJOUT UTILISATEUR ===");
        User u1 = new User("U001", "Jean", "Dupont", "1234", "ADMIN", LocalDateTime.now());
        User u2 = new User("U002", "Marie", "Durand", "5678", "MEMBER", LocalDateTime.now());
        User u3 = new User("U003", "Mike", "Kent", "5679", "MEMBER", LocalDateTime.now());
        userDAO.addUser(u1);
        userDAO.addUser(u2);
        userDAO.addUser(u3);

        // Lister tous les utilisateurs
        System.out.println("\n=== LISTE DES UTILISATEURS ===");
        List<User> allUsers = userDAO.getAllUsers();
        for (User u : allUsers) {
            System.out.println(u.getUser_id() + " - " + u.getName() + " " + u.getSurname() + " (" + u.getRole() + ")");
        }

        //  Rechercher un utilisateur par ID
        System.out.println("\n=== RECHERCHE PAR ID ===");
        User foundById = userDAO.findById("U001");
        if (foundById != null) {
            System.out.println("Trouvé: " + foundById.getName() + " " + foundById.getSurname());
        } else {
            System.out.println("Utilisateur introuvable !");
        }

        //  Rechercher par username (name)
        System.out.println("\n=== RECHERCHE PAR NOM ===");
        List<User> foundUsers = userDAO.findByname("Marie");

        if (foundUsers != null && !foundUsers.isEmpty()) {
            for (User u : foundUsers) {
                System.out.println("Trouvé: " + u.getName() + " " + u.getSurname());
            }
        } else {
            System.out.println("Aucun utilisateur trouvé !");
        }

        // Supprimer un utilisateur
        System.out.println("\n=== SUPPRESSION ===");
        boolean deleted = userDAO.deleteUser("U002");
        System.out.println("Suppression U002: " + (deleted ? "✅ OK" : "❌ Échec"));

        // Vérifions après suppression
        System.out.println("\n=== LISTE APRES SUPPRESSION ===");
        allUsers = userDAO.getAllUsers();
        for (User u : allUsers) {
            System.out.println(u.getUser_id() + " - " + u.getName() + " " + u.getSurname());
        }
    }
}

