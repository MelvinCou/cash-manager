package com.cashmanager.server.order.controller;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.order.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/shop/orders")
@Tag(name = "API Orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
       this.orderService = orderService;
    }

    @PostMapping("")
    @Operation(summary = "After the inventory check, create a new order ", description = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "One or several products are unavailable, or no order received"),
            @ApiResponse(responseCode = "503", description = "Error accessing the database, please retry later")
    })
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
                   return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
               }
           }
           else {
               return new ResponseEntity<>("This order cannot be completed because certain products are unavailable :\n"+optMessage.get(),HttpStatus.BAD_REQUEST);
           }
        }
        else {
            return new ResponseEntity<>("No order received",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{orderId}")
    @Operation(summary = "Update a pre-existing status order ", description = "Update an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "The order with this id was not found"),
    })
    public ResponseEntity<?> updateOrderStatus(@PathVariable UUID orderId, @RequestBody OrderStatus status ){
        boolean isUpdated = this.orderService.updateOrderStatus(orderId,status);
        if(isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
