package com.grandma.app.products.service;

import com.grandma.app.exceptions.NotDifferentFieldException;
import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.exception.ProductAlreadyExistsException;
import com.grandma.app.products.exception.ProductNotFoundException;
import com.grandma.app.products.mapper.ProductMapper;
import com.grandma.app.products.repository.ProductsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;

    public ProductsServiceImpl(ProductsRepository productsRepository, ProductMapper productMapper) {
        this.productsRepository = productsRepository;
        this.productMapper = productMapper;
    }

    public ProductDto createProduct(ProductDto productDto) {
        if (productsRepository.existsByFantasyName(productDto.getFantasyName())) {
            throw new ProductAlreadyExistsException(
                    String.format("Producto con nombre %s ya existe", productDto.getFantasyName()));
        }

        return productMapper.productEntityToProductDto(
                productsRepository.save(productMapper.productDtoToProductEntity(productDto)));
    }

    public ProductDto getProduct(String uuid) {
        return productsRepository.findByUuid(uuid)
                .map(productMapper::productEntityToProductDto)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product con uuid %s no encontrado", uuid)));
    }

    public void updateProduct(String uuid, ProductDto productDto) {
        var existingProduct = productsRepository.findByUuid(uuid)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Producto con uuid %s no encontrado", uuid)));

        Boolean hasChanges = !existingProduct.getFantasyName().equals(productDto.getFantasyName())
                || !existingProduct.getCategory().equals(productDto.getCategory())
                || !existingProduct.getDescription().equals(productDto.getDescription())
                || !existingProduct.getPrice().equals(productDto.getPrice())
                || !existingProduct.getAvailable() == productDto.getAvailable();

        if (!hasChanges) {
            throw new NotDifferentFieldException("No hay ningún campo diferente en el Request.");
        }

        existingProduct.setFantasyName(productDto.getFantasyName());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setAvailable(productDto.getAvailable());

        productsRepository.save(existingProduct);
    }

    public void deleteProduct(String uuid) {
        productsRepository.deleteById(uuid);
    }

    // BONUS TRACK
    public List<ProductDto> findByPartialFantasyName(String partialFantasyName) {
        var entities = productsRepository.findByPartialFantasyName(partialFantasyName);
        return entities.stream().map(productMapper::productEntityToProductDto).toList();
    }
}
