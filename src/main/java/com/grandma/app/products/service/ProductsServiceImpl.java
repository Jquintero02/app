package com.grandma.app.products.service;

import com.grandma.app.clients.exception.ClientAlreadyExistsException;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.mapper.ProductMapper;
import com.grandma.app.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductsService {
    private final ProductsRepository repository;
    private final ProductMapper mapper;

    public ProductsService(ProductsRepository repository, ProductMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDto createProduct(ProductDto product){
        if (repository.existsByUuid(product.getUuid())) {
            throw new ClientAlreadyExistsException(
                    String.format("Producto con uuid %s ya existe", product.getUuid()));
        }

        var savedProduct = repository.save(mapper.toModel(product));

        return mapper.toDto(savedProduct);
    }

    public ProductDto getProduct(UUID uuid){
        return repository.findByUuid(uuid)
                .map(mapper::toDto)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Product con uuid %s no encontrado", uuid)));
    }

    public void updateProduct(UUID uuid, ProductDto product){
        var existingProduct = repository.findByUuid(uuid)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Producto con uuid %s no encontrado", uuid)));

        if (!product.getUuid().equals(uuid)) {
            throw new IllegalArgumentException(String.format(
                    "No se puede modificar el uuid %s", uuid));
        }

        boolean hasChanges = !existingProduct.getFantasyName().equals(product.getFantasyName())
                || !existingProduct.getCategory().equals(product.getCategory())
                || !existingProduct.getDescription().equals(product.getDescription())
                || !existingProduct.getPrice().equals(product.getPrice())
                || !existingProduct.getAvailable().equals(product.isAvailable());

        if (!hasChanges) {
            throw new IllegalArgumentException("No hay ningún campo diferente en el Request.");
        }

        existingProduct.setFantasyName(product.getFantasyName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setAvailable(product.isAvailable());

        repository.save(existingProduct);
    }

    public void deleteProduct(UUID uuid){
        var productToDelete = repository
                .findByUuid(uuid)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Producto con uuid %s no encontrado", uuid)));

        repository.delete(productToDelete);
    }
}
