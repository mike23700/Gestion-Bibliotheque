package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.HistoryDAO;
import com.tp.model.History;

import java.time.LocalDateTime;
import java.util.List;

public class HistoryService {

    private final HistoryDAO historyDAO;

    public HistoryService(DAOFactory daoFactory) {
        this.historyDAO = daoFactory.getHistoryDAO();
    }

    public boolean addHistory(String userId, String bookId, String actionType, String actionDescription) {
        History history = new History(userId, bookId, actionType, actionDescription, LocalDateTime.now());
        return historyDAO.addHistory(history);
    }

    public List<History> getAllHistory() {
        return historyDAO.getAllHistory();
    }
}