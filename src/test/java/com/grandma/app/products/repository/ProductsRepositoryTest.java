package com.grandma.app.products.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.grandma.app.products.entity.ProductEntity;

class ProductsRepositoryTest {
    private ProductsRepository productsRepository;

    @Test
    void existsByFantasyName() {
        Mockito.doReturn(false).when(productsRepository).existsByFantasyName(Mockito.anyString());

        productsRepository.existsByFantasyName("Fantasy-1");

        Assertions.assertThat(productsRepository.existsByFantasyName("Fantasy-1")).isFalse();

        Mockito.verify(productsRepository).existsByFantasyName(Mockito.anyString());
    }

    @Test
    void findByUuid() {
        Mockito.doReturn(Optional.of(new ProductEntity())).when(productsRepository).findByUuid(Mockito.anyString());

        productsRepository.findByUuid("UUID-1");

        Assertions.assertThat(productsRepository.findByUuid("UUID-1")).isNotNull();

        Mockito.verify(productsRepository).findByUuid(Mockito.anyString());
    }

    @Test
    void findByPartialFantasyName() {
        Mockito.doReturn(Optional.of(new ProductEntity())).when(productsRepository)
                .findByPartialFantasyName(Mockito.anyString());
        productsRepository.findByPartialFantasyName("Fan");

        Assertions.assertThat(productsRepository.findByPartialFantasyName("Fan")).isNotNull();

        Mockito.verify(productsRepository).findByPartialFantasyName(Mockito.anyString());
    }
}