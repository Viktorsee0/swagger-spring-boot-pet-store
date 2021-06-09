package com.petstore.swaggerspringbootpetstore.dao;

import com.petstore.swaggerspringbootpetstore.enity.Token;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenDao {

    private List<Token> tokens = new ArrayList<>();

    public void save(Token token) {
        tokens.add(token);
    }

    public void delete(Token token){
        tokens.remove(token);
    }

    public boolean contains(long userId) {
        return tokens.stream().filter(token -> token.getUserId() == userId).findFirst().orElse(null) != null;
    }

    public boolean contains(String tokenID) {
        return tokens.stream().filter(token -> token.getId().equals(tokenID)).findFirst().orElse(null) != null;
    }


    public Token getToken(String tokenId){
        return tokens.stream().filter(token -> token.getId().equals(tokenId)).findFirst().orElse(null);
    }

}
