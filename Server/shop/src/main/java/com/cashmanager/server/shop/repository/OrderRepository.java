package com.cashmanager.server.shop.repository;

import com.cashmanager.server.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}