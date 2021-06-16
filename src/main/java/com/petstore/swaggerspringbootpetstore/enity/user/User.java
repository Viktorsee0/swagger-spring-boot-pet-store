package com.petstore.swaggerspringbootpetstore.enity.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @NotNull
    private String username;
    @NotBlank @NotNull
    private String firstName;
    @NotBlank @NotNull
    private String lastName;
    @NotBlank @NotNull
    private String email;
    @NotBlank @NotNull
    private String password;
    @NotBlank @NotNull
    private String phone;
    private int userStatus;
}
