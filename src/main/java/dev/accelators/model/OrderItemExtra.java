package dev.accelators.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_items_extras")
public class OrderItemExtra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;
    @ManyToOne
    @JoinColumn(name = "extra_id")
    private Extra extra;
    private Integer quantity;
    @Column(name = "total_value")
    private BigDecimal totalValue;
}
