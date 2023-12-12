package com.cashmanager.server.shop;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( nullable = false)
    private UUID product_id;
    private String name;
    private Integer price;
    private String productUrl;
    private Integer stock;

    @OneToMany(mappedBy="products")
    private Set<Ordered_orders> ordered_orders;

}
