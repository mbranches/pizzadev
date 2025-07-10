package dev.accelators.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "pizza_sizes")
public class PizzaSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal coefficient;
    @Column(name = "flavor_quantity")
    private Integer flavorQuantity;
}
