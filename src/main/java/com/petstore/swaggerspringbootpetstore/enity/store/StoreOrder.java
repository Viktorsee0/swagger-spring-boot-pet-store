package com.petstore.swaggerspringbootpetstore.enity.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StoreOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long petId;
    private long quantity;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime shipDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private boolean complete;
}
