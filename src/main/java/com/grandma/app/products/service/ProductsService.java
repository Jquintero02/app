package com.grandma.app.products.service;

import com.grandma.app.products.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductsService {
    ProductDto createProduct(ProductDto product);

    ProductDto getProduct(UUID uuid);

    void updateProduct(UUID uuid, ProductDto product);

    void deleteProduct(UUID uuid);

    boolean existsByFantasyName(String fantasyName);

    // BONUS TRACK
    List<ProductDto> findByPartialFantasyName(String partialFantasyName);
}
