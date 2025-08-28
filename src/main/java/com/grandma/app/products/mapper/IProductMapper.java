package com.grandma.app.products.mapper;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    ProductEntity productDtoToProductEntity(ProductDto productDto);

    ProductDto productEntityToProductDto(ProductEntity productEntity);
}
