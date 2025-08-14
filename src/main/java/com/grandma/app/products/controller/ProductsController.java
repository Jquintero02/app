package com.grandma.app.products.controller;

import com.grandma.app.products.dto.ProductDto;
import com.grandma.app.products.service.ProductsService;
import com.grandma.app.products.service.ProductsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsServiceImpl productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.createProduct(productDto));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getProduct(uuid));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateproduct(@PathVariable("uuid") String uuid,
            @Valid @RequestBody ProductDto product) {
        productsService.updateProduct(uuid, product);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteproduct(@PathVariable("uuid") String uuid) {
        productsService.deleteProduct(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // BONUS TRACK
    @GetMapping("/search")
    public ResponseEntity<?> findByPartialFantasyName(@RequestParam(name = "q") String q) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findByPartialFantasyName(q));
    }
}
