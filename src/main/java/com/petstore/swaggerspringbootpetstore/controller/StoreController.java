package com.petstore.swaggerspringbootpetstore.controller;

import com.petstore.swaggerspringbootpetstore.enity.store.Order;
import com.petstore.swaggerspringbootpetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/inventory")
    public ResponseEntity<Order> inventory(){
        Map<String, Integer> inventory = orderService.inventory();
        return new ResponseEntity(inventory, HttpStatus.ACCEPTED);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        orderService.save(order);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> createOrder(@PathVariable long orderId){
        Order order = orderService.getById(orderId);
        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Order> delete(@PathVariable long orderId){
        boolean delete = orderService.delete(orderId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
