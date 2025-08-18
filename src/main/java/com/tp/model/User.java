package com.tp.model;

import java.time.LocalDateTime;

public class User {
    private String user_id;
    private String name;
    private String surname;
    private String password;
    private String role;
    private LocalDateTime registration_date;

    public User(String user_id, String name, String surname, String password, String role, LocalDateTime registration_date) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.registration_date = registration_date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDateTime registration_date) {
        this.registration_date = registration_date;
    }
}
