package dev.accelators.model;

import dev.accelators.converter.ValidStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String note;
    @Column(name = "total_value")
    private BigDecimal totalValue;
    @Enumerated(EnumType.STRING)
    @Convert(converter = ValidStatusConverter.class)
    private ValidStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
