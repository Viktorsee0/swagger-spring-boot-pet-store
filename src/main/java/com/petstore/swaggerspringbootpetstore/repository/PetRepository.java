package com.petstore.swaggerspringbootpetstore.repository;

import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<List<Pet>> findAllByStatus(PetStatus status);
}
