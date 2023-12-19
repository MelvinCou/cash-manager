package com.cashmanager.server.shop.repository;

import com.cashmanager.server.shop.entity.OrderedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderedOrderRepository extends JpaRepository<OrderedOrder, UUID> {
}