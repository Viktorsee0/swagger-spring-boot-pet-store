package com.petstore.swaggerspringbootpetstore.enity.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private String name;
    private PetStatus status;
}
