package com.grandma.app.products.mapper;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto productEntityToProductDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();

        productDto.setFantasyName(productEntity.getFantasyName().toUpperCase());
        productDto.setCategory(productEntity.getCategory());
        productDto.setDescription(productEntity.getDescription());
        productDto.setPrice(productEntity.getPrice());
        productDto.setAvailable(productEntity.getAvailable());

        return productDto;
    }

    public ProductEntity productDtoToProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setFantasyName(productDto.getFantasyName().toUpperCase());
        productEntity.setCategory(productDto.getCategory());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setAvailable(productDto.getAvailable());

        return productEntity;
    }
}
