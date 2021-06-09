package com.petstore.swaggerspringbootpetstore.controller;

import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping()
    public ResponseEntity<Pet> add(@RequestBody Pet pet) {
        petService.add(pet);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @PutMapping
    public ResponseEntity update(@RequestBody Pet pet) {

        petService.update(pet);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> getByStatus(String status) {
        List<Pet> pets = petService.getByStatus(status);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return ResponseEntity.ok(pet);
    }

    //TODO
    @PostMapping("/{petId}")
    public ResponseEntity updatePetById(@PathVariable long petId, String name, String status) {
        petService.updatePetById(petId, name, status);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{petId}")
    public ResponseEntity delete(@PathVariable long petId) {
        petService.delete(petId);
        return (ResponseEntity) ResponseEntity.accepted();

    }


}
