package com.petstore.swaggerspringbootpetstore.enity.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;
    @Enumerated(EnumType.STRING)
    private PetStatus status;
}