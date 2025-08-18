package com.tp.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservation_id;
    private String user_id;
    private String book_id;
    private LocalDateTime reservation_date;
    private String status;

    public Reservation(int reservation_id, String user_id, String book_id, LocalDateTime reservation_date, String status) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.reservation_date = reservation_date;
        this.status = status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public LocalDateTime getReservation_date() {
        return reservation_date;
    }

    public String getStatus() {
        return status;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setReservation_date(LocalDateTime reservation_date) {
        this.reservation_date = reservation_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
