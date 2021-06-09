package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.dao.UserDao;
import com.petstore.swaggerspringbootpetstore.enity.user.User;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.AuthorizationException;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.InvalidUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public void createWithList(List<User> users){
        for (User user : users){
           createUser(user);
        }
    }

    public void createUser(User user){
        userDao.add(user);
    }

    public User getByUsername(String username){
        if (!userDao.containsByUsername(username)){
            throw  new InvalidUsernameException();
        }
        return userDao.getByUsername(username);
    }

    public void updateUsername(String username, User user){
        if (!userDao.containsByUsername(username)){
            throw  new InvalidUsernameException();
        }
        userDao.updateUsername(username, user);
    }

    public void delete(String username) {
        if (!userDao.containsByUsername(username)) {
            throw new InvalidUsernameException();
        }
        userDao.delete(username);
    }

    public User login(String username, String passwrod){
        User byUsername = userDao.getByUsername(username);
        if (byUsername == null && !byUsername.getPassword().equals(passwrod)){
            throw new AuthorizationException();
        }
        return byUsername;
    }
}