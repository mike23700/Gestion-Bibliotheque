package com.tp.service;

import com.tp.dao.DAOFactory;
import com.tp.dao.interfaces.UserDAO;
import com.tp.model.User;
import com.tp.model.generateID.GenerateUserID;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;
    private final GenerateUserID idGenerator;

    public UserService(DAOFactory daoFactory) {
        this.userDAO = daoFactory.getUserDAO();
        this.idGenerator = new GenerateUserID();
    }

    public boolean createAndAddUser(String name, String surname, int telNum, String email) {
        User user = new User(
                idGenerator.generateID(),
                name,
                surname,
                telNum,
                email,
                "0000",
                "MEMBER",
                LocalDateTime.now()
        );

        return userDAO.addUser(user);
    }

    public User authenticate(String userId, String password) {
        User user = userDAO.findById(userId);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public boolean addUser(User user) {
        user.setUser_id(idGenerator.generateID());
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

    public List<User> findUserByName(String name) {
        return userDAO.findByname(name);
    }

    public List<User> getAllMembers() {
        return userDAO.getAllMembers();
    }

    public int countMembers() {
        return userDAO.countMembers();
    }
}