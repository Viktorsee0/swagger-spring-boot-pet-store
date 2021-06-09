package com.petstore.swaggerspringbootpetstore.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String time;
    private int countCalls;
    private String id;
    private long userId;
}
