package com.petstore.swaggerspringbootpetstore.dao;

import com.petstore.swaggerspringbootpetstore.enity.store.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao {
    private List<Order> orders = new ArrayList<>();
    private int incId = 1;

    public void save(Order order){
        order.setId(incId++);
        orders.add(order);
    }

    public Order getById(long id){
       return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    public void delete(long id){
        orders.remove(getById(id));
    }

    public boolean contains(long id){
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null) != null;
    }

}
