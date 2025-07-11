package dev.accelators.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
