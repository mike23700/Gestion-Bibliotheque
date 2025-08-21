package com.tp.test;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.Book;
import com.tp.model.User;
import com.tp.model.generateID.GenerateIdBooks;
import com.tp.service.BookService;

import java.time.LocalDateTime;
import java.util.List;

public class BookTest {
    public static void main(String[] args) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        GenerateIdBooks g = new GenerateIdBooks();

        System.out.println("\n=== TEST AJOUT UTILISATEUR ===");
        Book u1 = new Book(g.generateID() , "Jean", "Dupont", 2002 , "ADMIN", "fiction", "rien" , "rendu" , 1 , LocalDateTime.now());
        //User u2 = new User("U002", "Marie", "Durand", "5678", "MEMBER", LocalDateTime.now());
        //User u3 = new User("U003", "Mike", "Kent", "5679", "MEMBER", LocalDateTime.now());
        bookDAO.AddBook(u1);
        //userDAO.addUser(u2);
        //userDAO.addUser(u3);

        /*
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
        User foundByName = userDAO.findByUsername("Marie");
        if (foundByName != null) {
            System.out.println("Trouvé: " + foundByName.getName() + " " + foundByName.getSurname());
        } else {
            System.out.println("Utilisateur introuvable !");
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
        */

    }
}

