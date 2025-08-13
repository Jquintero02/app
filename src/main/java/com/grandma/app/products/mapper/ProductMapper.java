package com.grandma.app.products.mapper;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(ProductEntity product) {
        if (product == null) {
            throw new NullPointerException("El producto no puede ser nulo");
        }
        ProductDto productDto = new ProductDto();

        productDto.setFantasyName(product.getFantasyName().toUpperCase());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setAvailable(product.getAvailable());

        return productDto;
    }

    public ProductEntity toEntity(ProductDto product) {
        if (product == null) {
            throw new NullPointerException("El producto no puede ser nulo");
        }
        ProductEntity productEntity = new ProductEntity();

        productEntity.setFantasyName(product.getFantasyName().toUpperCase());
        productEntity.setCategory(product.getCategory());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setAvailable(product.isAvailable());

        return productEntity;
    }
}
