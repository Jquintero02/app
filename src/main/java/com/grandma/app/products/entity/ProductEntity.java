package com.grandma.app.products.entity;

import com.grandma.app.products.enums.CategoryEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {
    public ProductEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El nombre no puede ser vacio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String fantasyName;

    @Column(nullable = false)
    @NotNull(message = "La categoria no puede ser vacia")
    private CategoryEnum category;

    @Column(nullable = false)
    @NotNull(message = "La descripcion no puede ser vacia")
    @Size(max = 511, message = "El nombre no puede tener más de 511 caracteres")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "El precio no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio no puede tener más de 2 decimales")
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull(message = "El estado no puede ser vacio")
    private Boolean available;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
