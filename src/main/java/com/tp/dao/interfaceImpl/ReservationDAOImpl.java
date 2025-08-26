package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;
import com.tp.dao.DBConnection;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.model.Reservation;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    private DAOFactory daoFactory;

    public ReservationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (user_id, book_id, reservation_date, status) VALUES (?, ?, ?, ?)";
        boolean success = false;
        ResultSet autoId = null;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, reservation.getUser_id());
            stmt.setString(2, reservation.getBook_id());
            stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservation_date()));
            stmt.setString(4, reservation.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                autoId = stmt.getGeneratedKeys();
                if (autoId.next()) {
                    reservation.setReservation_id(autoId.getInt(1));
                    success = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la réservation : " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        String query = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        boolean success = false;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, reservation.getStatus());
            stmt.setInt(2, reservation.getReservation_id());

            int updated = stmt.executeUpdate();
            if (updated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour du statut de réservation : " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Reservation findById(int reservationId) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE r.reservation_id = ?";
        Reservation reservation = null;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setInt(1, reservationId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la réservation par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> findByUserId(String userId) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE r.user_id = ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par user_id : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByUserName(String name) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE u.name LIKE ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par nom d'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByBookId(String bookId) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE r.book_id = ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par book_id : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByBookName(String bookName) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE b.title LIKE ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            // Utilisation de "%" pour permettre la recherche d'une partie du titre du livre
            stmt.setString(1, "%" + bookName + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par nom de livre : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByStatus(String status) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE r.status = ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par statut : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> getAllReservations() {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getString("user_name"),
                        rs.getString("book_title"),
                        rs.getTimestamp("reservation_date").toLocalDateTime(),
                        rs.getString("status")
                );
                list.add(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des réservations : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reservation> findByUserIdAndBookName(String userId, String bookName) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, r.reservation_date, r.status FROM reservations r JOIN users u ON r.user_id = u.user_id JOIN books b ON r.book_id = b.book_id WHERE r.user_id = ? AND b.title LIKE ? AND r.status = ? ORDER BY r.reservation_date DESC";
        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, userId);
            stmt.setString(2, "%" + bookName + "%");
            stmt.setString(3,"ACTIVE");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par user_id et nom de livre : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
/*
    @Override
    public List<Reservation> findByDate(LocalDateTime date) {
        String query = "SELECT history_id, user_id, book_id, action_type, action_description, action_date FROM history WHERE action_date BETWEEN ? AND ?";
        List<History> list = new ArrayList<>();

        LocalDateTime startOfDay = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = date.withHour(23).withMinute(59).withSecond(59);

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setTimestamp(1, Timestamp.valueOf(startOfDay));
            stmt.setTimestamp(2, Timestamp.valueOf(endOfDay));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    History history = new History(
                            rs.getInt("history_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("action_type"),
                            rs.getString("action_description"),
                            rs.getTimestamp("action_date").toLocalDateTime()
                    );
                    list.add(history);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'historique par date: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
 */

    @Override
    public List<Reservation> findActiveByUserId(String userId) {
        String query = "SELECT r.reservation_id, r.user_id, r.book_id, u.name AS user_name, b.title AS book_title, " +
                "r.reservation_date, r.status " +
                "FROM reservations r " +
                "JOIN users u ON r.user_id = u.user_id " +
                "JOIN books b ON r.book_id = b.book_id " +
                "WHERE r.user_id = ? AND r.status = ? " +
                "ORDER BY r.reservation_date DESC";

        List<Reservation> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, userId);
            stmt.setString(2, "ACTIVE");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getString("user_name"),
                            rs.getString("book_title"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    list.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations actives : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public int countReservations() {
        String query = "SELECT COUNT(*) FROM reservations WHERE status = 'ACTIVE'";
        int count = 0;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}