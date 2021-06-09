package com.petstore.swaggerspringbootpetstore.controller;

import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidPetIdException;
import com.petstore.swaggerspringbootpetstore.enity.pet.exception.InvalidStatusValue;
import com.petstore.swaggerspringbootpetstore.enity.store.exception.InvalidOrderIdException;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.AuthorizationException;
import com.petstore.swaggerspringbootpetstore.enity.user.exception.InvalidUsernameException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<AwesomeException> iue() {
        return new ResponseEntity<>(new AwesomeException("Invalid username supplied"), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<AwesomeException> ae() {
        return new ResponseEntity<>(new AwesomeException("Invalid username/password supplied"), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidOrderIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<AwesomeException> ioie() {
        return new ResponseEntity<>(new AwesomeException("Invalid ID supplied"), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidStatusValue.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<AwesomeException> isv() {
        return new ResponseEntity<>(new AwesomeException("Invalid status value"), HttpStatus.BAD_REQUEST);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPetIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<AwesomeException> ipie() {
        return new ResponseEntity<>(new AwesomeException("Invalid ID supplied"), HttpStatus.BAD_REQUEST);
    }


    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
