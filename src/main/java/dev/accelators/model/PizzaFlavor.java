package dev.accelators.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "pizza_flavors")
public class PizzaFlavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    private Boolean available;
}
