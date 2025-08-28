package com.grandma.app.orders.mapper;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.dto.OrderDtoResponse;
import com.grandma.app.orders.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);

    @Mappings({
            @Mapping(target = "clientDocument", source = "client.document"),
            @Mapping(target = "productUuid", source = "product.uuid")
    })
    OrderDto orderEntityToOrderDto(OrderEntity orderEntity);

    @Mappings({
            @Mapping(target = "uuid", source = "uuid"),
            @Mapping(target = "creationDateTime", source = "creationDateTime"),
            @Mapping(target = "clientDocument", source = "client.document"),
            @Mapping(target = "productUuid", source = "product.uuid"),
            @Mapping(target = "quantity", source = "quantity"),
            @Mapping(target = "extraInformation", source = "extraInformation"),
            @Mapping(target = "subTotal", source = "subTotal"),
            @Mapping(target = "tax", source = "tax"),
            @Mapping(target = "grandTotal", source = "grandTotal"),
            @Mapping(target = "delivered", source = "delivered"),
            @Mapping(target = "deliveredDate", source = "deliveredDate")
    })
    OrderDtoResponse orderEntityToOrderDtoResponse(OrderEntity orderEntity);
}
