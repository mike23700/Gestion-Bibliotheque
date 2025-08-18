package com.tp.dao.interfaces;


import com.tp.model.User;

import java.util.List;

public interface UserDAO {
      boolean addUser(User user) ;
      boolean updateUser(User user) ;
      boolean deleteUser(String userId) ;
      User findById(String userId) ;
      User findByUsername(String name);
      List<User> getAllUsers() ;

}
