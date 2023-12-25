package com.cashmanager.server.shop_database.repositories;

import com.cashmanager.server.shop_database.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
