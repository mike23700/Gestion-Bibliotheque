package com.tp.model;

import java.time.LocalDateTime;
import java.sql.Date;

public class Loan {

    private String loan_id;
    private String user_id;
    private String book_id;
    private LocalDateTime borrow_date;
    private LocalDateTime due_date;
    private LocalDateTime return_date;

    public Loan(String loan_id, String user_id, String book_id, LocalDateTime borrow_date, LocalDateTime due_date, LocalDateTime return_date) {
        this.loan_id = loan_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.due_date = due_date;
        this.return_date = return_date;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public LocalDateTime getBorrow_date() {
        return borrow_date;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public LocalDateTime getReturn_date() {
        return return_date;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setBorrow_date(LocalDateTime borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
        this.return_date = return_date;
    }
}
