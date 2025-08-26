package com.tp.dao.interfaceImpl;

import com.tp.dao.DAOFactory;
import com.tp.dao.DBConnection;
import com.tp.dao.interfaces.HistoryDAO;
import com.tp.model.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAOImpl implements HistoryDAO {

    private DAOFactory daoFactory;

    public HistoryDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public boolean addHistory(History history) {
        String query = "INSERT INTO history (user_id, book_id, action_type, action_description, action_date) VALUES (?, ?, ?, ?, ?)";
        boolean success = false;
        ResultSet autoId = null;

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, history.getUser_id());
            stmt.setString(2, history.getBook_id());
            stmt.setString(3, history.getAction_type());
            stmt.setString(4, history.getAction_description());
            stmt.setTimestamp(5, Timestamp.valueOf(history.getAction_date()));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
                autoId = stmt.getGeneratedKeys();
                if (autoId.next()) {
                    history.setHistory_id(autoId.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'historique : " + e.getMessage());
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<History> getAllHistory() {
        String query = "SELECT history_id, user_id, book_id, action_type, action_description, action_date FROM history ORDER BY action_date DESC";
        List<History> list = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de tout l'historique : " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<History> findByActionType(String actionType) {
        String query = "SELECT history_id, user_id, book_id, action_type, action_description, action_date FROM history WHERE action_type = ? ORDER BY action_date DESC";
        List<History> historyList = new ArrayList<>();

        try (Connection connexion = DBConnection.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setString(1, actionType);

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
                    historyList.add(history);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'historique par type d'action : " + e.getMessage());
            e.printStackTrace();
        }
        return historyList;
    }

    /*@Override
    public List<History> findByDate(LocalDateTime date) {
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
    }*/

}
