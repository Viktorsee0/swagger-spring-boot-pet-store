package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.user.User;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.AuthorizationException;
import com.petstore.swaggerspringbootpetstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createWithList(List<User> users) {
        userRepository.saveAll(users);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username).orElse(null);
    }

    public void updateUsername(String username, User user) {
        user.setUsername(username);
        userRepository.save(user);
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User login(String username, String passwrod) {
        Optional<User> byUsername = userRepository.getByUsername(username);

        if (byUsername.isEmpty()) {
            throw new AuthorizationException();
        }

        User user = byUsername.get();

        if (!user.getPassword().equals(passwrod)){
            throw new AuthorizationException();
        }

        return user;
    }
}