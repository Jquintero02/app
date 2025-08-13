package com.grandma.app.products.controller;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.exception.ProductAlreadyExistsException;
import com.grandma.app.products.exception.ProductNotFoundException;
import com.grandma.app.products.service.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsServiceImpl service;

    public ProductsController(ProductsServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto product) {
        if (product.getFantasyName() == null || product.getFantasyName().isEmpty()
                || product.getCategory() == null
                || product.getDescription() == null || product.getDescription().isEmpty()
                || product.getPrice() == null || product.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Datos del producto inválidos o incompletos: %s", product));
        }

        if (service.existsByFantasyName(product.getFantasyName())) {
            throw new ProductAlreadyExistsException(
                    String.format("Producto con nombre %s ya existe", product.getFantasyName()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(product));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("uuid") String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException(String.format(
                    "Formato de uuid inválido: %s", uuid));
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.getProduct(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateproduct(@PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductDto product) {
        if (uuid == null) {
            throw new IllegalArgumentException(String.format(
                    "Formato de uuido no válido: %s", uuid));
        }

        if (product.getFantasyName() == null || product.getFantasyName().isEmpty()
                || product.getCategory() == null
                || product.getDescription() == null || product.getDescription().isEmpty()
                || product.getPrice() == null || product.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Datos del product no válidos o incompletos: %s", product));
        }

        var existsProduct = service.getProduct(uuid);

        if (existsProduct == null) {
            throw new ProductNotFoundException(
                    String.format("Product con uuid %s no encontrado", uuid));
        }

        service.updateProduct(uuid, product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteproduct(@PathVariable("uuid") String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException(String.format(
                    "Formato de uuid no válido: %s", uuid));
        }

        var existsProduct = service.getProduct(uuid);

        if (existsProduct == null) {
            throw new ProductNotFoundException(
                    String.format("Producto con uuid %s no encontrado", uuid));
        }

        service.deleteProduct(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // BONUS TRACK
    @GetMapping("/search")
    public ResponseEntity<?> findByPartialFantasyName(@RequestParam(name = "q") String q) {
        if (q == null || q.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro de búsqueda no puede estar vacío");
        }
        var result = service.findByPartialFantasyName(q);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
