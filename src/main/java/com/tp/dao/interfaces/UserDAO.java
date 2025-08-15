package com.tp.dao.interfaces;

import com.tp.models.User;
import java.util.List;

public interface UserDAO {
    void AddUser(User user) throws Exception;
    List<User> searchByName(String name) throws Exception;
    List<User> getAllUsers() throws Exception ;
    void DeleteUser(String id_user) throws Exception;
    void updateUser(User user) throws Exception;
    User getUser(String id_user) throws Exception;
}
