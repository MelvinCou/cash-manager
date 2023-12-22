package com.cashmanager.server.shop.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
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
    @Column( name = "id",nullable = false)
    private UUID id;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false)
    private BigDecimal price;
    @Column( nullable = false)
    private String productUrl;
    @Column( nullable = false)
    private int stock;

    @OneToMany(mappedBy="product")
    @ToString.Exclude
    private Set<OrderedOrder> orderedOrders;


    public Product(String name, BigDecimal price, String productUrl, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.productUrl = productUrl;
        this.orderedOrders = new HashSet<>();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
