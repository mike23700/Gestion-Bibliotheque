package com.tp.models;

import java.time.LocalDateTime;

public class User {
    private String Id_User;
    private String Name;
    private String Surname;
    private String Password;
    private String Role;
    private LocalDateTime Date_Register;

    public User(String id_user , String name , String surname , String password , String role , LocalDateTime date_register){
        this.Id_User = id_user;
        this.Name = name;
        this.Surname = surname;
        this.Password = password;
        this.Role = role;
        this.Date_Register = date_register;
    }

    public String getId_User(){
        return this.Id_User;
    }
    public String getName(){
        return this.Name;
    }
    public String getSurname(){
        return this.Surname;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getRole(){
        return this.Role;
    }
    public LocalDateTime getDate_Register(){
        return this.Date_Register;
    }
    public void setId_User(String id_user){
        this.Id_User = id_user;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setSurname(String surname){
        this.Surname = surname;
    }
    public void setPassword(String password){
        this.Password = password;
    }
    public void setRole(String role){
        this.Role = role;
    }
    public void setDate_Register(LocalDateTime date_register){
        this.Date_Register = date_register;
    }
}
