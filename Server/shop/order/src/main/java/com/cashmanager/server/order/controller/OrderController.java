package com.cashmanager.server.order.controller;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.order.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/shop/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
       this.orderService = orderService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody CartDto cartDto){
        Map<Integer, ProductDto> orderedProducts = cartDto.getListOrderedProducts();
        if(orderedProducts != null && !orderedProducts.isEmpty()){
            //check inventory
            Optional<String> optMessage = this.orderService.checkInventory(orderedProducts);
           if(optMessage.isEmpty()){
               //order creation
               Optional<OrderDto> optOrder = this.orderService.createOrder(cartDto);
               if(optOrder.isPresent()){
                   return new ResponseEntity<>(optOrder.get(), HttpStatus.CREATED);
               }
               else{
                   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
               }
           }
           else {
               return new ResponseEntity<>("This order cannot be completed because certain products are unavailable :\n"+optMessage.get(),HttpStatus.NO_CONTENT);
           }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
