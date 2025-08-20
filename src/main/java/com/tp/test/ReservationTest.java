package com.tp.test;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.BookDAO;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.Book;
import com.tp.model.Reservation;
import com.tp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationTest {
    public static void main(String[] args) throws Exception {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ReservationDAO reservationDAO = daoFactory.getReservationDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        BookDAO bookDAO = daoFactory.getBookDAO();

        // Ajout de 5 utilisateurs de test
        System.out.println("=== PRÉPARATION DE 5 UTILISATEURS DE TEST ===");
        User user1 = new User("user_test_1", "Jean", "Dupont", "pass1", "MEMBER", LocalDateTime.now());
        User user2 = new User("user_test_2", "Marie", "Curie", "pass2", "MEMBER", LocalDateTime.now());
        User user3 = new User("user_test_3", "Albert", "Camus", "pass3", "MEMBER", LocalDateTime.now());
        User user4 = new User("user_test_4", "Rosa", "Parks", "pass4", "MEMBER", LocalDateTime.now());
        User user5 = new User("user_test_5", "Leonardo", "Da Vinci", "pass5", "MEMBER", LocalDateTime.now());
        userDAO.addUser(user1);
        userDAO.addUser(user2);
        userDAO.addUser(user3);
        userDAO.addUser(user4);
        userDAO.addUser(user5);
        System.out.println("5 utilisateurs ajoutés avec succès.");

        // Ajout de 5 livres de test
        System.out.println("\n=== PRÉPARATION DE 5 LIVRES DE TEST ===");
        Book book1 = new Book("book_test_1", "Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, "img1.jpg", "Fantasy", "Description...", 1, 0, LocalDateTime.now());
        Book book2 = new Book("book_test_2", "1984", "George Orwell", 1949, "img2.jpg", "Science-Fiction", "Description...", 1, 0, LocalDateTime.now());
        Book book3 = new Book("book_test_3", "Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "img3.jpg", "Conte philosophique", "Description...", 1, 0, LocalDateTime.now());
        Book book4 = new Book("book_test_4", "Don Quichotte", "Miguel de Cervantes", 1605, "img4.jpg", "Roman", "Description...", 1, 0, LocalDateTime.now());
        Book book5 = new Book("book_test_5", "Orgueil et Préjugés", "Jane Austen", 1813, "img5.jpg", "Roman d'amour", "Description...", 1, 0, LocalDateTime.now());
        bookDAO.AddBook(book1);
        bookDAO.AddBook(book2);
        bookDAO.AddBook(book3);
        bookDAO.AddBook(book4);
        bookDAO.AddBook(book5);
        System.out.println("5 livres ajoutés avec succès.");

        // Création et ajout de 6 réservations
        System.out.println("\n=== TEST D'AJOUT DE 6 RÉSERVATIONS ===");
        Reservation r1 = new Reservation(user1.getUser_id(), book1.getId_Book(), LocalDateTime.now(), "ACTIVE");
        Reservation r2 = new Reservation(user2.getUser_id(), book2.getId_Book(), LocalDateTime.now(), "ACTIVE");
        Reservation r3 = new Reservation(user3.getUser_id(), book3.getId_Book(), LocalDateTime.now(), "ACTIVE");
        Reservation r4 = new Reservation(user1.getUser_id(), book4.getId_Book(), LocalDateTime.now(), "PENDING");
        Reservation r5 = new Reservation(user4.getUser_id(), book5.getId_Book(), LocalDateTime.now(), "PENDING");
        Reservation r6 = new Reservation(user5.getUser_id(), book1.getId_Book(), LocalDateTime.now(), "PENDING");

        boolean addedR1 = reservationDAO.addReservation(r1);
        boolean addedR2 = reservationDAO.addReservation(r2);
        boolean addedR3 = reservationDAO.addReservation(r3);
        boolean addedR4 = reservationDAO.addReservation(r4);
        boolean addedR5 = reservationDAO.addReservation(r5);
        boolean addedR6 = reservationDAO.addReservation(r6);

        System.out.println("Réservation 1 ajoutée : " + (addedR1 ? "OUI" : "NON") + ", ID: " + r1.getReservation_id());
        System.out.println("Réservation 2 ajoutée : " + (addedR2 ? "OUI" : "NON") + ", ID: " + r2.getReservation_id());
        System.out.println("Réservation 3 ajoutée : " + (addedR3 ? "OUI" : "NON") + ", ID: " + r3.getReservation_id());
        System.out.println("Réservation 4 ajoutée : " + (addedR4 ? "OUI" : "NON") + ", ID: " + r4.getReservation_id());
        System.out.println("Réservation 5 ajoutée : " + (addedR5 ? "OUI" : "NON") + ", ID: " + r5.getReservation_id());
        System.out.println("Réservation 6 ajoutée : " + (addedR6 ? "OUI" : "NON") + ", ID: " + r6.getReservation_id());

        // Test des méthodes de recherche (avec noms et titres)
        System.out.println("\n=== LISTE DE TOUTES LES RÉSERVATIONS ===");
        List<Reservation> allReservations = reservationDAO.getAllReservations();
        for (Reservation r : allReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle() +
                    " | Statut=" + r.getStatus());
        }

        System.out.println("\n=== RECHERCHE PAR ID (" + r1.getReservation_id() + ") ===");
        Reservation foundById = reservationDAO.findById(r1.getReservation_id());
        if (foundById != null) {
            System.out.println("Trouvé : ID=" + foundById.getReservation_id() +
                    " → Utilisateur=" + foundById.getUserName() +
                    " | Livre=" + foundById.getBookTitle() +
                    " | Statut=" + foundById.getStatus());
        } else {
            System.out.println("⚠️ Réservation introuvable !");
        }

        System.out.println("\n=== RECHERCHE PAR USER_ID ('" + user1.getUser_id() + "') ===");
        List<Reservation> userReservations = reservationDAO.findByUserId(user1.getUser_id());
        for (Reservation r : userReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle());
        }

        System.out.println("\n=== RECHERCHE PAR NOM UTILISATEUR ('Marie') ===");
        List<Reservation> nameReservations = reservationDAO.findByUserName("Marie");
        for (Reservation r : nameReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle());
        }

        System.out.println("\n=== RECHERCHE PAR BOOK_ID ('" + book1.getId_Book() + "') ===");
        List<Reservation> bookReservations = reservationDAO.findByBookId(book1.getId_Book());
        for (Reservation r : bookReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle());
        }

        System.out.println("\n=== RECHERCHE PAR NOM DE LIVRE ('Petit Prince') ===");
        List<Reservation> bookNameReservations = reservationDAO.findByBookName("Petit Prince");
        for (Reservation r : bookNameReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle());
        }

        System.out.println("\n=== RECHERCHE PAR STATUT ('ACTIVE') ===");
        List<Reservation> pendingReservations = reservationDAO.findByStatus("ACTIVE");
        for (Reservation r : pendingReservations) {
            System.out.println("ID=" + r.getReservation_id() +
                    " | Utilisateur=" + r.getUserName() +
                    " | Livre=" + r.getBookTitle() +
                    " | Statut=" + r.getStatus());
        }

        System.out.println("\n=== TEST DE MISE À JOUR : ANNULATION DE R2 ===");
        r2.setStatus("CANCELLED");
        boolean updatedR2 = reservationDAO.updateReservation(r2);
        System.out.println("Réservation " + r2.getReservation_id() + " annulée : " + (updatedR2 ? "OUI" : "NON"));
        Reservation updatedR2Check = reservationDAO.findById(r2.getReservation_id());
        System.out.println("Statut de R2 après mise à jour : " + (updatedR2Check != null ? updatedR2Check.getStatus() : "Non trouvé"));

        System.out.println("\n=== TEST DE MISE À JOUR : VALIDATION DE R3 ===");
        r3.setStatus("FULFILLED");
        boolean updatedR3 = reservationDAO.updateReservation(r3);
        System.out.println("Réservation " + r3.getReservation_id() + " validée : " + (updatedR3 ? "OUI" : "NON"));
        Reservation updatedR3Check = reservationDAO.findById(r3.getReservation_id());
        System.out.println("Statut de R3 après mise à jour : " + (updatedR3Check != null ? updatedR3Check.getStatus() : "Non trouvé"));
    }
}