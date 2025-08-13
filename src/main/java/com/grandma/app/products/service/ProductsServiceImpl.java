package com.grandma.app.products.service;

import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.exception.ProductAlreadyExistsException;
import com.grandma.app.products.exception.ProductNotFoundException;
import com.grandma.app.products.mapper.ProductMapper;
import com.grandma.app.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository repository;
    private final ProductMapper mapper;

    public ProductsServiceImpl(ProductsRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDto createProduct(ProductDto product) {
        if (repository.existsByFantasyName(product.getFantasyName())) {
            throw new ProductAlreadyExistsException(
                    String.format("Producto con nombre %s ya existe", product.getFantasyName()));
        }

        var savedProduct = repository.save(mapper.toEntity(product));

        return mapper.toDto(savedProduct);
    }

    public ProductDto getProduct(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(mapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product con uuid %s no encontrado", uuid)));
    }

    public void updateProduct(UUID uuid, ProductDto product) {
        var existingProduct = repository.findByUuid(uuid)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Producto con uuid %s no encontrado", uuid)));

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

    public void deleteProduct(UUID uuid) {
        repository.deleteById(uuid);
    }

    public boolean existsByFantasyName(String fantasyName) {
        return repository.existsByFantasyName(fantasyName);
    }

    // BONUS TRACK
    public List<ProductDto> findByPartialFantasyName(String partialFantasyName) {
        var entities = repository.findByPartialFantasyName(partialFantasyName);
        return entities.stream().map(mapper::toDto).toList();
    }
}
