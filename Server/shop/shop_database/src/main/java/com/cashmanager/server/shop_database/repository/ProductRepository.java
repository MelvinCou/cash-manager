package com.cashmanager.server.shop_database.repository;

import com.cashmanager.server.shop_database.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
