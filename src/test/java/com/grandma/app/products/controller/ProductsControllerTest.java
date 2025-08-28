package com.grandma.app.products.controller;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.enums.CategoryEnum;
import com.grandma.app.products.service.ProductsService;

class ProductsControllerTest {
    @Mock
    private ProductsService productsService;
    private ProductsController productsController;

    @BeforeEach
    void setUp() {
        productsController = new ProductsController(productsService);
    }

    @Test
    void createProduct() {
        Mockito.doReturn(Optional.of(new ProductDto())).when(productsService)
                .createProduct(Mockito.any(ProductDto.class));

        ProductDto productDto = new ProductDto(
                "Producto 1",
                CategoryEnum.CHICKEN,
                "Descripcion del producto 1",
                new BigDecimal("10000.01"),
                true);

        productsController.createProduct(productDto);

        Assertions.assertThat(productsController.createProduct(productDto)).isNotNull();

        Mockito.verify(productsService).createProduct(Mockito.any(ProductDto.class));
    }

    @Test
    void getProduct() {
        Mockito.doReturn(new ProductDto()).when(productsService).getProduct(Mockito.anyString());

        productsController.getProduct(UUID.randomUUID().toString());

        Assertions.assertThat(productsController.getProduct(UUID.randomUUID().toString())).isNotNull();

        Mockito.verify(productsService).getProduct(Mockito.anyString());
    }

    @Test
    void updateProduct() {
        Mockito.doNothing().when(productsService).updateProduct(Mockito.anyString(), Mockito.any(ProductDto.class));

        ProductDto productDto = new ProductDto(
                "Producto 1",
                CategoryEnum.CHICKEN,
                "Descripcion del producto 1",
                new BigDecimal("10000.01"),
                true);

        productsController.updateProduct(UUID.randomUUID().toString(), productDto);

        Assertions.assertThat(productsController.updateProduct(UUID.randomUUID().toString(), productDto)).isNotNull();

        Mockito.verify(productsService).updateProduct(Mockito.anyString(), Mockito.any(ProductDto.class));
    }

    @Test
    void deleteProduct() {
        Mockito.doNothing().when(productsService).deleteProduct(Mockito.anyString());

        productsController.deleteProduct(UUID.randomUUID().toString());

        Assertions.assertThat(productsController.deleteProduct(UUID.randomUUID().toString())).isNotNull();

        Mockito.verify(productsService).deleteProduct(Mockito.anyString());
    }

    @Test
    void findByPartialFantasyName() {
        Mockito.doReturn(Optional.of(new ProductDto())).when(productsService)
                .findByPartialFantasyName(Mockito.anyString());

        productsController.findByPartialFantasyName("Product");

        Assertions.assertThat(productsController.findByPartialFantasyName("Product")).isNotNull();

        Mockito.verify(productsService).findByPartialFantasyName(Mockito.anyString());
    }
}