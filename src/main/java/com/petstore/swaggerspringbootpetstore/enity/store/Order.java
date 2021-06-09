package com.petstore.swaggerspringbootpetstore.enity.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long petId;
    private long quantity;
    private LocalDateTime shipDate;
    private OrderStatus status;
    private boolean complete;
}
