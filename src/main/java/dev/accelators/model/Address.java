package dev.accelators.model;

import jakarta.persistence.*;

@Entity(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cep;
    private String neighborhood;
    private String street;
    @Column(name = "number_house")
    private String numberHouse;
    private String complement;
}
