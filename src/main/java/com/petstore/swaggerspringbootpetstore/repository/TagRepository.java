package com.petstore.swaggerspringbootpetstore.repository;

import com.petstore.swaggerspringbootpetstore.enity.pet.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
