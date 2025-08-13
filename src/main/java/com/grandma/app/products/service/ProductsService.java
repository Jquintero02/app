package com.grandma.app.products.service;

import com.grandma.app.products.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto createProduct(ProductDto product);

    ProductDto getProduct(String uuid);

    void updateProduct(String uuid, ProductDto product);

    void deleteProduct(String uuid);

    boolean existsByFantasyName(String fantasyName);

    // BONUS TRACK
    List<ProductDto> findByPartialFantasyName(String partialFantasyName);
}
