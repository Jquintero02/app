package com.grandma.app.products.service;

import com.grandma.app.products.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductsServiceImplTest {
    @Mock
    private ProductsService productsService;

    private ProductDto productDto;

    String partialFantasyName = "CHEESE BURGUER";

    @BeforeEach
    public void setup(){
        productDto = new ProductDto(
                "CHEESE BURGUER BIG COMBO",
                "HAMBURGERS_AND_HOTDOGS",
                "Burguer double 8 Oz meet, french fries and Big soda",
                new BigDecimal("21008.41"),
                true
        );
    }

    @DisplayName("JUnit test for createProduct method")
    @Test
    void createProduct() {
        Mockito.when(productsService.createProduct(productDto)).thenReturn(productDto);
        ProductDto savedProduct = productsService.createProduct(productDto);
    }

    @DisplayName("JUnit test for findByPartialFantasyName")
    @Test
    void findByPartialFantasyName() {
        Mockito.when(productsService.findByPartialFantasyName(partialFantasyName)).thenReturn(List.of(productDto));
        Assertions.assertEquals(List.of(productDto), productsService.findByPartialFantasyName(partialFantasyName));
    }
}