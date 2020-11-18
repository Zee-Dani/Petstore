package com.petstore.petstore.data.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table // helps u change table name
@Data

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer Id;

    @Column(nullable = true)
    private String name;

    private Integer age;

    private  String colour;

    @Column()
    private  String breed;

    @Enumerated(EnumType.STRING) // .string will make it return as male or female at database
    private Gender petSex;

    @ManyToOne(cascade = CascadeType.ALL) // many pet can be mapped to one store
    @ToString.Exclude
    private Store store;


}
