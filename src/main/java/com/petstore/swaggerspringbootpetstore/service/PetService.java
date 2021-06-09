package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.dao.PetDao;
import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidStatusValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetService {

    @Autowired
    private PetDao petDao;

    public void add(Pet pet) {
        petDao.add(pet);
    }

    public List<Pet> getByStatus(String status) {

        try {
            PetStatus petStatus = PetStatus.valueOf(status);
            return petDao.getByStatus(petStatus);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusValue();
        }

    }

    public Pet getPetById(Long id) {
        if (!petDao.containsById(id)) {
            throw new InvalidPetIdException();
        }
        return petDao.gePetById(id);
    }

    public void updatePetById(long id, String name, String status) {
        try {
            Pet pet = getPetById(id);
            PetStatus petStatus = PetStatus.valueOf(status);
            pet.setName(name);
            pet.setStatus(petStatus);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusValue();
        }
    }

    public void update(Pet pet){
        Pet petById = getPetById(pet.getId());
        petById.setName(pet.getName());
        petById.setStatus(pet.getStatus());
        petById.setCategory(pet.getCategory());
        petById.setTags(pet.getTags());
    }

    public void delete(Long id) {
        if (!petDao.containsById(id)) {
            throw new  InvalidPetIdException();
        }
        petDao.delete(id);
    }
}
