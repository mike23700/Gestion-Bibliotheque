package com.tp.dao.interfaces;

import com.tp.model.History;
import com.tp.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryDAO {

    boolean addHistory(History history);
    List<History> getAllHistory() ;
    List<History> findByActionType(String actionType);

    //List<History> findByDate(LocalDateTime date);
}
