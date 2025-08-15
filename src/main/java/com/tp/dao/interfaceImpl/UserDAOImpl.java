package com.tp.dao.interfaceImpl;

import com.tp.dao.daoFactory;
import com.tp.dao.interfaces.UserDAO;
import com.tp.models.User;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    daoFactory Daofactory;

    public UserDAOImpl(daoFactory daofactory){
        Daofactory = daofactory;
    }

    @Override
    public void AddUser(User user) throws Exception {

    }

    @Override
    public List<User> searchByName(String name) throws Exception {
        return List.of();
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return List.of();
    }

    @Override
    public void DeleteUser(String id_user) throws Exception {

    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public User getUser(String id_user) throws Exception {
        return null;
    }
}
