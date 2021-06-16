package com.petstore.swaggerspringbootpetstore.repository;

import com.petstore.swaggerspringbootpetstore.enity.pet.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
