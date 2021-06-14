package com.petstore.swaggerspringbootpetstore.controller;

import com.petstore.swaggerspringbootpetstore.enity.Token;
import com.petstore.swaggerspringbootpetstore.enity.user.User;
import com.petstore.swaggerspringbootpetstore.service.TokenService;
import com.petstore.swaggerspringbootpetstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/createWithList")
    public void createWithList(@RequestBody List<User> users) {
        userService.createWithList(users);
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User byUsername = userService.getByUsername(username);
        return ResponseEntity.ok(byUsername);
    }

    @PutMapping("/{username}")
    public void changeUsername(@PathVariable String username,
                               @RequestBody User user) {

        userService.updateUsername(username, user);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        System.out.println(username);
        userService.delete(username);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(String username,
                                        String password) {

        User byUsername = userService.login(username, password);
        Token token = tokenService.save(byUsername.getId());

        return ResponseEntity.ok(token.getId().toString());
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        tokenService.delete(request);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/createWithArray")
    public void createWithArray(@RequestBody List<User> users) {
        userService.createWithList(users);
    }

    @PostMapping()
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
