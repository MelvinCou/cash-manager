package com.cashmanager.server.shop_database.mapper;

import com.cashmanager.server.common.dto.CartDto;
import com.cashmanager.server.common.dto.OrderDto;
import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.common.enumeration.OrderStatus;
import com.cashmanager.server.shop_database.entity.Order;
import com.cashmanager.server.shop_database.entity.OrderedOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Function that convert the entity Order to the OrderDto
     * @param order : the order that will be converted
     * @return orderDto that result from the conversion
     */
    @Mapping(source="orderedOrders", target = "cart")
    @Mapping(source = "status",target = "orderStatus")
    OrderDto orderToOrderDto(Order order);

    /**
     * Converter for the orderStatus
     * @param status of the order
     * @return the appropriate orderStatus
     */
    default OrderStatus statusToOrderStatus(String status){
        if(status.equalsIgnoreCase(OrderStatus.PAYMENT_PENDING.toString())){
            return OrderStatus.PAYMENT_PENDING;
        }
        else if(status.equalsIgnoreCase(OrderStatus.PAYMENT_ACCEPTED.toString())){
            return OrderStatus.PAYMENT_ACCEPTED;
        }
        else if(status.equalsIgnoreCase(OrderStatus.PAYMENT_REJECTED.toString())){
            return OrderStatus.PAYMENT_REJECTED;
        }
        else if(status.equalsIgnoreCase(OrderStatus.CANCELED.toString())){
            return OrderStatus.CANCELED;
        }
        return OrderStatus.PAYMENT_PENDING;
    }

    /**
     * COnverter for the cart of OrderDto
     * @param orderedOrders list from the entity Order
     * @return the appropriate CartDto
     */
    default CartDto orderedOrdersToCart(Set<OrderedOrder> orderedOrders){
        Map<Integer, ProductDto> listOfOrderedProducts = new HashMap<>();
        orderedOrders.forEach(orderedOrder -> {
            listOfOrderedProducts.put(orderedOrder.getQuantity(),ProductMapper.INSTANCE.productToProductDto(orderedOrder.getProduct()));
        });
            CartDto cartDto = new CartDto();
            cartDto.setListOrderedProducts(listOfOrderedProducts);
        return cartDto;
    }

    /**
     * Function that convert the dto OrderDto to the entity Order
     * @param orderDto : the dto that will be converted
     * @return : order that result from the conversion
     */
    @Mapping(source="orderStatus",target = "status")
    @Mapping(source = "cart", target = "orderedOrders")
    Order orderDtoToOrder(OrderDto orderDto);

    /**
     * Converter for the status of the entity Order
     * @param orderStatus from the OrderDto
     * @return a string representing the order status
     */
    default String orderStatustoStatus(OrderStatus orderStatus){
        return orderStatus.toString();
    }

    /**
     * Converter for the list of orderedOrders
     * @param cart from the OrderDto
     * @return the appropriate list of orderedOrders
     */
    default Set<OrderedOrder> cartToOrderedOrders(CartDto cart){
        Set<OrderedOrder> orderedOrders = new HashSet<>();
        cart.getListOrderedProducts().forEach((quantity, productDto) -> {
            OrderedOrder orderedOrder = new OrderedOrder();
            orderedOrder.setProduct(ProductMapper.INSTANCE.productDtoToProduct(productDto));
            orderedOrder.setQuantity(quantity);
        });
        return orderedOrders;
    }
}
