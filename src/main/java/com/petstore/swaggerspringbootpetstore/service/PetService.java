package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.repository.PetRepository;
import com.petstore.swaggerspringbootpetstore.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PetService {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PetRepository petRepository;

    public void save(Pet pet) {
        petRepository.save(pet);
    }

    public void update(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            petRepository.save(pet);
        }
        throw new  InvalidPetIdException();
    }

    public List<Pet> getByStatus(PetStatus status) {
        Optional<List<Pet>> allByStatus = petRepository.findAllByStatus(status);
        return allByStatus.get();
    }

    public Pet getPetById(Long id) {
        Optional<Pet> byId = petRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new InvalidPetIdException();
    }

    public void updatePetById(long id, String name, PetStatus status) {
        if (petRepository.existsById(id)){
            Pet byId = petRepository.getById(id);
            byId.setName(name);
            byId.setStatus(status);
            petRepository.save(byId);
        }
        throw new  InvalidPetIdException();
    }

    public void delete(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        }
        throw new  InvalidPetIdException();
    }
}
