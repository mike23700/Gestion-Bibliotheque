package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.User;

import java.util.UUID;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService(DAOFactory daoFactory) {
        this.userDAO = daoFactory.getUserDAO();
    }


    public User authenticate(String userId, String password) {
        User user = userDAO.findById(userId);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }


    public boolean addUser(User user) {
        user.setUser_id(UUID.randomUUID().toString());
        user.setPassword("0000");
        user.setRole("MEMBER");

        return userDAO.addUser(user);
    }

    public boolean updateUser(User user)  {
        return userDAO.updateUser(user);
    }


    public boolean deleteUser(String userId) {
        return userDAO.deleteUser(userId);
    }


    public User findUserById(String userId) {
        return userDAO.findById(userId);
    }

    public User findUserByName(String name) {
        return userDAO.findById(name);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}