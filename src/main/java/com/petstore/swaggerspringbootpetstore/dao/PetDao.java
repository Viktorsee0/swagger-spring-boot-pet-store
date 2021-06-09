package com.petstore.swaggerspringbootpetstore.dao;

import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PetDao {

    private List<Pet> pets = new ArrayList<>();
    private int incId = 1;

    public void add(Pet pet){
        pet.setId(incId++);
        pets.add(pet);
        System.out.println(pets);
    }

    public void update(Pet pet){
        Stream<Pet> petStream = pets.stream().filter(item -> item.getId() == pet.getId());
    }

    public List<Pet> getByStatus(PetStatus status){
        return pets.stream().filter(item -> item.getStatus().equals(status)).collect(Collectors.toList());
    }

    public Pet gePetById(Long id){
        return pets.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public boolean containsById(Long id){
        return gePetById(id) != null;
    }

    public void delete(Long id) {
        pets.remove(gePetById(id));
    }

    public List<Pet> getAll(){
        return pets;
    }
}
