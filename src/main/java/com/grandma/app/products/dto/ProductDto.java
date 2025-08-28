package com.grandma.app.products.dto;

import com.grandma.app.products.enums.CategoryEnum;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotBlank(message = "El nombre no puede ser vacio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String fantasyName;

    @NotNull(message = "La categoria no puede ser vacia")
    private CategoryEnum category;

    @NotBlank(message = "La descripcion no puede ser vacia")
    @Size(max = 511, message = "El nombre no puede tener más de 511 caracteres")
    private String description;

    @NotNull(message = "El precio no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio no puede tener más de 2 decimales")
    private BigDecimal price;

    @NotNull(message = "El estado no puede ser vacio")
    private Boolean available;
}
