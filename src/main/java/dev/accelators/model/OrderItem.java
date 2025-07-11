package dev.accelators.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "pizza_size_id")
    private PizzaSize pizzaSize;
    @JoinColumn(name = "total_value")
    private BigDecimal totalValue;
}
