package com.petstore.swaggerspringbootpetstore.service;

import com.petstore.swaggerspringbootpetstore.dao.TokenDao;
import com.petstore.swaggerspringbootpetstore.enity.Token;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.util.CreateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenService {

    @Autowired
    private TokenDao tokenDao;

    public String save(long userId, HttpServletResponse response){
        if (tokenDao.contains(userId)){
            throw new  InvalidPetIdException();
        }

        Token token = new Token(response.getHeader("X-Expires-After"),
                Integer.parseInt(response.getHeader("X-Rate-Limit")),
                CreateToken.createUUID(),
                userId);

        tokenDao.save(token);

        return token.getId();
    }


    public void delete(HttpServletRequest request){
        String tokenID = request.getHeader("X-Token-Id");
        Token token = tokenDao.getToken(tokenID);
        if (token != null){
            tokenDao.delete(token);
        }
    }

    public boolean contains(String tokenId){
        return tokenDao.contains(tokenId);
    }
}
