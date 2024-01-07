package com.cashmanager.server.order.service;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.common.enumeration.OrderStatus;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IOrderService {
    /**
     * Check if the inventory contains all the wanted products
     * @return Empty message if check ok, or the list of products unavailable
     */
    Optional<String> checkInventory(Map<Integer, ProductDto> orderedProducts);

    /**
     * Create a new Order from the validated cart
     * @param cartDto contains a list of ProductDto and their quantity
     * @return the new order
     */
    Optional<OrderDto> createOrder(CartDto cartDto);

    /**
     * Update the order status in the database
     * @param orderId : identification of the order
     * @param status : new status of the order
     * @return true if the update is successful
     */
    boolean updateOrderStatus(UUID orderId, OrderStatus status);
}
