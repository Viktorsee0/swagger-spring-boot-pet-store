package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.pet.Category;
import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.pet.PetStatus;
import com.petstore.swaggerspringbootpetstore.enity.pet.Tag;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.repository.CategoryRepository;
import com.petstore.swaggerspringbootpetstore.repository.PetRepository;
import com.petstore.swaggerspringbootpetstore.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {


    @Autowired
    TagRepository tagRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private Category category(Pet pet) {
        Category category = pet.getCategory();
        Optional<Category> byName = categoryRepository.findByName(pet.getCategory().getName());

        if (byName.isPresent()) {
            category.setId(byName.get().getId());
        } else {
            category = categoryRepository.save(pet.getCategory());
        }
        return category;
    }

    private List<Tag> tags(Pet pet){
        List<Tag> tags = new ArrayList<>();

        for (Tag tag : pet.getTags()) {
            Optional<Tag> tagByName = tagRepository.findByName(tag.getName());
            tagByName.ifPresent(value -> tag.setId(value.getId()));
            tags.add(tag);
        }

        return tagRepository.saveAll(tags);
    }

    public void save(Pet pet) {
        Category category = category(pet);
        List<Tag> tags = tags(pet);

        pet.setCategory(category);
        pet.setTags(tags);

        petRepository.save(pet);
    }

    public void update(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            Category category = category(pet);
            List<Tag> tags = tags(pet);

            pet.setCategory(category);
            pet.setTags(tags);

            petRepository.save(pet);
        }
        throw new InvalidPetIdException();
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
        if (petRepository.existsById(id)) {
            Pet byId = petRepository.getById(id);
            byId.setName(name);
            byId.setStatus(status);
            petRepository.save(byId);
        }
        throw new InvalidPetIdException();
    }

    public void delete(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        }
        throw new InvalidPetIdException();
    }
}
