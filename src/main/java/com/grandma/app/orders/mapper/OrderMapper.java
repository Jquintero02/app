package com.grandma.app.orders.mapper;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(OrderEntity orderEntity) {
        if (orderEntity == null) {
            throw new NullPointerException("NullPointerException");
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setProductUuid(orderEntity.getProductUuid());
        orderDto.setQuantity(orderEntity.getQuantity());
        orderDto.setExtraInformation(orderEntity.getExtraInformation());

        return orderDto;
    }

    public OrderEntity toModel(OrderDto orderDto) {
        if (orderDto == null) {
            throw new NullPointerException("NullPointerException");
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setProductUuid(orderDto.getProductUuid());
        orderEntity.setQuantity(orderDto.getQuantity());
        orderEntity.setExtraInformation(orderDto.getExtraInformation());

        return orderEntity;
    }
}
