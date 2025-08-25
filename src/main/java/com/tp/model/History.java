package com.tp.model;

import java.time.LocalDateTime;

public class History {
    private int history_id;
    private String book_id;
    private String user_id;
    private String action_type;
    private String action_description;
    private LocalDateTime action_date;


    // Constructeur pour les opérations d'ajout'
    public History(String book_id, String user_id, String action_type, String action_description, LocalDateTime action_date) {
        this.book_id = book_id;
        this.user_id = user_id;
        this.action_type = action_type;
        this.action_description = action_description;
        this.action_date = action_date;
    }

    //Constructeur pour les opérations de listing
    public History(int history_id , String book_id, String user_id, String action_type, String action_description, LocalDateTime action_date) {
        this.history_id = history_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.action_type = action_type;
        this.action_description = action_description;
        this.action_date = action_date;
    }

    public int getHistory_id(){
        return this.history_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setHistory_id(int history_id){
        this.history_id = history_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getAction_description() {
        return action_description;
    }

    public void setAction_description(String action_description) {
        this.action_description = action_description;
    }

    public LocalDateTime getAction_date() {
        return action_date;
    }

    public void setAction_date(LocalDateTime action_date) {
        this.action_date = action_date;
    }
}
