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
@Table(name = "ordered_orders")
public class OrderedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column( name = "id",nullable = false)
    private UUID orderedOrdersId;
    @Column( nullable = false)
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
        OrderedOrder that = (OrderedOrder) o;
        return getOrderedOrdersId() != null && Objects.equals(getOrderedOrdersId(), that.getOrderedOrdersId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
