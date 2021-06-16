package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.pet.Category;
import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import com.petstore.swaggerspringbootpetstore.enity.pet.Tag;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.enity.user.User;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.AuthorizationException;
import com.petstore.swaggerspringbootpetstore.repository.CategoryRepository;
import com.petstore.swaggerspringbootpetstore.repository.PetRepository;
import com.petstore.swaggerspringbootpetstore.repository.TagRepository;
import com.petstore.swaggerspringbootpetstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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