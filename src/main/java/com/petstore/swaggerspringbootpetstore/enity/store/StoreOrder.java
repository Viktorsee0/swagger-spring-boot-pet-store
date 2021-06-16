package com.petstore.swaggerspringbootpetstore.enity.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull @Min(1)
    private long quantity;
    private LocalDateTime shipDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private boolean complete;
}
