package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.store.StoreOrder;
import com.petstore.swaggerspringbootpetstore.enity.store.exception.InvalidOrderIdException;
import com.petstore.swaggerspringbootpetstore.repository.PetRepository;
import com.petstore.swaggerspringbootpetstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class OrderService {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PetRepository petRepository;

    public Map<String, Integer> inventory() {
        Map<String, Integer> petMap = new HashMap<>();
        List<Pet> pets = petRepository.findAll();
        for (Pet pet : pets) {
            String key = pet.getCategory().getName();
            if (petMap.containsKey(key)) {
                petMap.put(key, petMap.get(key) + 1);
            } else {
                petMap.put(pet.getCategory().getName(), 1);
            }
        }
        return petMap;
    }

    public void save(StoreOrder order) {
        storeRepository.save(order);
    }

    public StoreOrder getById(long id) {
        Optional<StoreOrder> byId = storeRepository.findById(id);
        if (byId.isEmpty()) {
            throw new InvalidOrderIdException();
        }

        return byId.get();
    }

    public boolean delete(long id) {
        if (!storeRepository.existsById(id)) {
            throw new InvalidOrderIdException();
        }
        storeRepository.deleteById(id);
        return true;
    }
}
