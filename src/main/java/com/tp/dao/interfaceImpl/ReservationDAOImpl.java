package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;
import com.tp.dao.DBConnection;
import com.tp.dao.interfaces.ReservationDAO;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.Reservation;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {

    private DAOFactory daoFactory;
    private UserDAO userDAO;

    public ReservationDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.userDAO = daoFactory.getUserDAO();
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (user_id, book_id, reservation_date, status) VALUES (?, ?, ?, ?)";
        boolean success = false;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, reservation.getUser_id());
            stmt.setString(2, reservation.getBook_id());
            stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservation_date()));
            stmt.setString(4, reservation.getStatus());

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                success = true;
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
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
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
        String query = "SELECT * FROM reservations WHERE user_id = ? ORDER BY reservation_date";
        List<Reservation> liste = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    liste.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par user_id : " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Reservation> findByUserName(String name) {
        String query = "SELECT r.* FROM reservations r JOIN users u ON r.user_id = u.user_id WHERE u.name LIKE ? ORDER BY r.reservation_date";
        List<Reservation> liste = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    liste.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par nom d'utilisateur : " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Reservation> findByBookId(String bookId) {
        String query = "SELECT * FROM reservations WHERE book_id = ? ORDER BY reservation_date";
        List<Reservation> liste = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    liste.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par book_id : " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Reservation> findByStatus(String status) {
        String query = "SELECT * FROM reservations WHERE status = ? ORDER BY reservation_date";
        List<Reservation> liste = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation(
                            rs.getInt("reservation_id"),
                            rs.getString("user_id"),
                            rs.getString("book_id"),
                            rs.getTimestamp("reservation_date").toLocalDateTime(),
                            rs.getString("status")
                    );
                    liste.add(reservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des réservations par statut : " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Reservation> getAllReservations() {
        String query = "SELECT * FROM reservations ORDER BY reservation_date";
        List<Reservation> liste = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("user_id"),
                        rs.getString("book_id"),
                        rs.getTimestamp("reservation_date").toLocalDateTime(),
                        rs.getString("status")
                );
                liste.add(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des réservations : " + e.getMessage());
            e.printStackTrace();
        }
        return liste;
    }
}