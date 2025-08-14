package com.grandma.app.products.dto;

import com.grandma.app.products.enums.CategoryEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    @NotNull(message = "El nombre no puede ser vacio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String fantasyName;

    @NotNull(message = "La categoria no puede ser vacia")
    private CategoryEnum category;

    @NotNull(message = "La descripcion no puede ser vacia")
    @Size(max = 511, message = "El nombre no puede tener más de 511 caracteres")
    private String description;

    @NotNull(message = "El precio no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio no puede tener más de 2 decimales")
    private BigDecimal price;

    @NotNull(message = "El estado no puede ser vacio")
    private Boolean available;
}
