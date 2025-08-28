package com.grandma.app.products.entity;

import com.grandma.app.products.enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "fantasy_name", length = 255, nullable = false, unique = true)
    private String fantasyName;

    @Column(nullable = false)
    private CategoryEnum category;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean available;
}
