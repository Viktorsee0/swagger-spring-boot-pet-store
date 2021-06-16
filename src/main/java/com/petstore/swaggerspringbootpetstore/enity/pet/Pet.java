package com.petstore.swaggerspringbootpetstore.enity.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    private Category category;
    @NotBlank
    @NotNull
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @Valid
    private List<Tag> tags;
    @Enumerated(EnumType.STRING)
    private PetStatus status;
}