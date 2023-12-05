package com.cashmanager.server.shop;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Ordered_orders that = (Ordered_orders) o;
        return getOrdered_orders_id() != null && Objects.equals(getOrdered_orders_id(), that.getOrdered_orders_id());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
