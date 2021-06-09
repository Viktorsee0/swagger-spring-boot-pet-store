package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.dao.OrderDao;
import com.petstore.swaggerspringbootpetstore.dao.PetDao;
import com.petstore.swaggerspringbootpetstore.enity.pet.Pet;
import com.petstore.swaggerspringbootpetstore.enity.store.Order;
import com.petstore.swaggerspringbootpetstore.enity.store.exception.InvalidOrderIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    PetDao petDao;

    public Map<String, Integer> inventory() {
        Map<String, Integer> petMap = new HashMap<>();
        List<Pet> pets = petDao.getAll();
        for (Pet pet : pets){
            String key = pet.getCategory().getName();
            if (petMap.containsKey(key)){
                petMap.put(key, petMap.get(key)+1);
            }else {
                petMap.put(pet.getCategory().getName(), 1);
            }
        }
        return petMap;
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public Order getById(long id) {
        chekId(id);
        return orderDao.getById(id);
    }

    public boolean delete(long id) {
        chekId(id);
        orderDao.getById(id);
        return true;
    }

    private void chekId(long id) {
        if (!orderDao.contains(id)) {
            throw new InvalidOrderIdException();
        }
    }
}
