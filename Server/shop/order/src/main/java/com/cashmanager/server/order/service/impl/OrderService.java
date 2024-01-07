package com.cashmanager.server.order.service.impl;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.order.service.IOrderService;
import com.cashmanager.server.shop_database.entity.Order;
import com.cashmanager.server.shop_database.entity.OrderedOrder;
import com.cashmanager.server.shop_database.entity.Product;
import com.cashmanager.server.shop_database.mapper.OrderMapper;
import com.cashmanager.server.shop_database.mapper.ProductMapper;
import com.cashmanager.server.shop_database.repository.OrderRepository;
import com.cashmanager.server.shop_database.repository.OrderedOrderRepository;
import com.cashmanager.server.shop_database.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderedOrderRepository orderedOrderRepository;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, OrderedOrderRepository orderedOrderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderedOrderRepository = orderedOrderRepository;
    }

    @Override
    public Optional<String> checkInventory(Map<Integer, ProductDto> orderedProducts) {
        StringBuffer sb = new StringBuffer();
        orderedProducts.forEach((quantity, productDto) -> {
            Optional<Product> optProduct = productRepository.findById(productDto.getId());
            if(optProduct.isEmpty()){
                sb.append("This product doesn't exist.\n");
            }
            else{
                int stock = optProduct.get().getStock();
                if(stock < quantity){
                    sb
                            .append("The product ")
                            .append(productDto.getName())
                            .append(" is not available for this quantity.\n");
                }
            }
        });
        if(sb.isEmpty()){
            return Optional.empty();
        }
        else{
            return Optional.of(sb.toString());
        }
    }

    @Override
    public Optional<OrderDto> createOrder(CartDto cartDto) {
         Order order = new Order();
         order.setDate(LocalDateTime.now());
         order.setStatus(OrderStatus.PAYMENT_PENDING.name());
         order = this.orderRepository.save(order);
         if(order.getId() != null){
             order.setOrderedOrders(new HashSet<>());
             //Creation of a copy of order necessary for the lambda expression
             Order finalOrder = order;
             cartDto.getListOrderedProducts().forEach((quantity, productDto) -> {
                 //create a new orderedOrder for each product ordered
                 OrderedOrder orderedOrdered = new OrderedOrder();
                 orderedOrdered.setOrder(finalOrder);
                 orderedOrdered.setProduct(ProductMapper.INSTANCE.productDtoToProduct(productDto));
                 orderedOrdered.setQuantity(quantity);
                 orderedOrderRepository.save(orderedOrdered);
                 finalOrder.getOrderedOrders().add(orderedOrdered);

                 //update inventory for each product
                 Optional<Product> optProduct = productRepository.findById(productDto.getId());
                 if(optProduct.isPresent()){
                     int currentStock = optProduct.get().getStock();
                     optProduct.get().setStock(currentStock - quantity);
                     productRepository.save(optProduct.get());
                 }
             });
             orderRepository.save(finalOrder);
             return Optional.of(OrderMapper.INSTANCE.orderToOrderDto(finalOrder));
         }
        return Optional.empty();
    }
}
