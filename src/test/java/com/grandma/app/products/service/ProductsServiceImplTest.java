package com.grandma.app.products.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.grandma.app.products.entity.ProductEntity;
import com.grandma.app.products.enums.CategoryEnum;
import com.grandma.app.products.repository.ProductsRepository;

class ProductsServiceImplTest {
    @Mock
    private ProductsRepository productsRepository;
    private ProductsServiceImpl productsService;

    @BeforeEach
    void setUp() {
        productsService = new ProductsServiceImpl(productsRepository);
    }

    @Test
    void createProduct() {
        Mockito.doAnswer(invocationOnMock -> {
            ProductEntity productEntity = invocationOnMock.getArgument(0);
            productEntity.setUuid(UUID.randomUUID().toString());
            return productEntity;
        }).when(productsRepository).save(Mockito.any(ProductEntity.class));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setFantasyName("Producto 1");
        productEntity.setCategory(CategoryEnum.CHICKEN);
        productEntity.setDescription("Descripcion del producto 1");
        productEntity.setPrice(new BigDecimal("10000.00"));
        productEntity.setAvailable(true);

        ProductEntity result = productsRepository.save(productEntity);

        Assertions.assertThat(result).isNotNull();
        Mockito.verify(productsRepository).save(Mockito.any(ProductEntity.class));
    }

    @Test
    void getProduct() {
        Mockito.doReturn(Optional.of(new ProductEntity())).when(productsRepository).findByUuid(Mockito.anyString());

        ProductEntity result = productsRepository.findByUuid(UUID.randomUUID().toString()).orElse(null);

        Assertions.assertThat(result).isNotNull();
        Mockito.verify(productsRepository).findByUuid(Mockito.anyString());
    }

    @Test
    void updateProduct() {
        ProductEntity existingProduct = new ProductEntity();
        existingProduct.setUuid(UUID.randomUUID().toString());
        existingProduct.setFantasyName("Producto 1");
        existingProduct.setCategory(CategoryEnum.CHICKEN);
        existingProduct.setDescription("Descripcion del producto 1");
        existingProduct.setPrice(new BigDecimal("10000.00"));
        existingProduct.setAvailable(true);

        Mockito.doReturn(Optional.of(existingProduct)).when(productsRepository).findByUuid(Mockito.anyString());

        ProductEntity productToUpdate = new ProductEntity();
        productToUpdate.setFantasyName("Producto 1 actualizado");
        productToUpdate.setCategory(CategoryEnum.DESSERTS);
        productToUpdate.setDescription("Descripcion del producto 1 actualizado");
        productToUpdate.setPrice(new BigDecimal("12000.00"));
        productToUpdate.setAvailable(false);
        productsService.updateProduct(existingProduct.getUuid(), null);

        Assertions.assertThat(existingProduct.getFantasyName()).isEqualTo("Producto 1 actualizado");
        Assertions.assertThat(existingProduct.getCategory()).isEqualTo(CategoryEnum.DESSERTS);
        Assertions.assertThat(existingProduct.getDescription()).isEqualTo("Descripcion del producto 1 actualizado");
        Assertions.assertThat(existingProduct.getPrice()).isEqualTo(new BigDecimal("12000.00"));
        Assertions.assertThat(existingProduct.getAvailable()).isEqualTo(false);

        Mockito.verify(productsRepository).save(Mockito.any(ProductEntity.class));
    }

    @Test
    void deleteProduct() {
        productsService.deleteProduct(UUID.randomUUID().toString());
        Mockito.verify(productsRepository).deleteById(Mockito.anyString());
    }

    @Test
    void findByPartialFantasyName() {
        Mockito.doReturn(new ArrayList<>()).when(productsRepository).findByPartialFantasyName(Mockito.anyString());

        var result = productsService.findByPartialFantasyName("Prod");

        Assertions.assertThat(result).isNotNull();
        Mockito.verify(productsRepository).findByPartialFantasyName(Mockito.anyString());
    }
}