package com.cashmanager.server.shop;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( nullable = false)
    // GenerationType instructs that a UUID for the entity should be generated automatically for us by the persistence provider.
    private UUID order_id;
    private String status;
    private Date date;

    @OneToMany(mappedBy = "orders")
    private Set<Ordered_orders> ordered_orders;


}
