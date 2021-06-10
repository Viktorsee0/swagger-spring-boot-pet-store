package com.petstore.swaggerspringbootpetstore.repository;

import com.petstore.swaggerspringbootpetstore.enity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);

    void deleteByUsername(String username);
}
