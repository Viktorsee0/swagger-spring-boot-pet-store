package com.petstore.swaggerspringbootpetstore.util;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateToken {
    public static String createTime(){
        return LocalDateTime.now().plusHours(2).toString();
    }

    public static int createCalls(){
        return 10;
    }

    public static String createUUID(){
        return  UUID.randomUUID().toString();
    }
}
