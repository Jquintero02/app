package com.grandma.app.products.service;

import com.grandma.app.products.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(String uuid);

    void updateProduct(String uuid, ProductDto productDto);

    void deleteProduct(String uuid);

    // BONUS TRACK
    List<ProductDto> findByPartialFantasyName(String partialFantasyName);
}
