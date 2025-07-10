package dev.accelators.model;

import jakarta.persistence.*;

@Entity(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_number")
    private String number;
}
