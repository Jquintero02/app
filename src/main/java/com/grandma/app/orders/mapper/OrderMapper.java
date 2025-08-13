package com.grandma.app.orders.mapper;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(OrderEntity order){
        if(order == null){
            throw new NullPointerException("NullPointerException");
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setClientDocument(order.getClientDocument());
        orderDto.setProductUuid(order.getProductUuid());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setExtraInformation(order.getExtraInformation());

        return orderDto;
    }

    public OrderEntity toModel(OrderDto order){
        if(order == null){
            throw new NullPointerException("NullPointerException");
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setClientDocument(order.getClientDocument());
        orderEntity.setProductUuid(order.getProductUuid());
        orderEntity.setQuantity(order.getQuantity());
        orderEntity.setExtraInformation(order.getExtraInformation());

        return orderEntity;
    }
}
