package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.enity.Token;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.repository.TokenRepository;
import com.petstore.swaggerspringbootpetstore.util.CreateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token save(long userId) {
        if (tokenRepository.existsByUserId(userId)) {
            throw new InvalidPetIdException();
        }

        Token save = tokenRepository.save(new Token(CreateToken.createUUID(), userId));

        return save;
    }


    public void delete(HttpServletRequest request) {
        String tokenID = request.getHeader("X-Token");
        if (tokenRepository.existsById(tokenID)) {
            tokenRepository.deleteById(tokenID);
        }
    }

    public boolean contains(String tokenID) {
        return tokenRepository.existsById(tokenID);
    }

}
