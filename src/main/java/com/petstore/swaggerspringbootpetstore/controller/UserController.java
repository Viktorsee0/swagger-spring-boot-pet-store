package com.petstore.swaggerspringbootpetstore.controller;

import com.petstore.swaggerspringbootpetstore.enity.Token;
import com.petstore.swaggerspringbootpetstore.enity.user.User;
import com.petstore.swaggerspringbootpetstore.service.TokenService;
import com.petstore.swaggerspringbootpetstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/createWithList")
    public void createWithList(@Valid @RequestBody List<User> users) {
        userService.createWithList(users);
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@Size(min = 3, max = 10, message = "About Me must be between 3 and 10 characters")
                                              @NotNull
                                              @PathVariable String username) {
        System.out.println(username);
        User byUsername = userService.getByUsername(username);
        return ResponseEntity.ok(byUsername);
    }

    @PutMapping("/{username}")
    public void changeUsername(@PathVariable String username,
                               @Valid @RequestBody User user) {
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
    public void createWithArray(@Valid @RequestBody List<User> users) {
        userService.createWithList(users);
    }

    @PostMapping()
    public void createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
    }
}
