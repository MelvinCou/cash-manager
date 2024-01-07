package com.cashmanager.server.shop_database.repository;


import com.cashmanager.server.shop_database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
