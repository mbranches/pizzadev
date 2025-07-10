package dev.accelators.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity(name = "pizza_flavors")
public class PizzaFlavor {
    private Long id;
    private String name;
    private String description;
    @Column(name = "base_price")
    private BigDecimal basePrice;
    private Boolean available;
}
