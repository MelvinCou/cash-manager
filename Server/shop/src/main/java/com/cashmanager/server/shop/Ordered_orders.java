package com.cashmanager.server.shop;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Ordered_orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( nullable = false)
    private UUID ordered_orders_id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id" ,nullable=false)
    private Product products;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable=false)
    private Order orders;

}
