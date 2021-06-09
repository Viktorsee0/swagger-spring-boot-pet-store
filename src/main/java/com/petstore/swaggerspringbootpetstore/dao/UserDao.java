package com.petstore.swaggerspringbootpetstore.dao;

import com.petstore.swaggerspringbootpetstore.enity.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private List<User> users = new ArrayList<>();
    private int incId = 1;

    public void add(User user){
        user.setId(incId++);
        users.add(user);
    }

    public User getByUsername(String username){
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public boolean containsByUsername(String username){
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null) != null;
    }

    public void updateUsername(String username, User user){
        User byUser = getByUsername(username);
        byUser.setUsername(user.getUsername());
        byUser.setEmail(user.getEmail());
        byUser.setFirstName(user.getFirstName());
        byUser.setLastName(user.getLastName());
        byUser.setPassword(user.getPassword());
        byUser.setPhone(user.getPhone());
    }

    public void delete(String username) {
        users.remove(getByUsername(username));
    }

}