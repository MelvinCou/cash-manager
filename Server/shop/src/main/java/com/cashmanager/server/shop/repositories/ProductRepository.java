package com.cashmanager.server.shop.repositories;

import com.cashmanager.server.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
