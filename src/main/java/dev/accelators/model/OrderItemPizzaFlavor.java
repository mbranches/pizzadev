package dev.accelators.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "order_items_pizza_flavors")
public class OrderItemPizzaFlavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;
    private Integer quantity;
    private BigDecimal totalValue;
}
