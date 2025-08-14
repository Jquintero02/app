package com.grandma.app.orders.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDto {
    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Size(max = 20, message = "El documento no puede tener más de 20 caracteres")
    private String clientDocument;

    @NotNull(message = "El producto no puede ser vacio")
    private String productUuid;

    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Max(value = 100, message = "La cantidad no debe ser mayor a 0")
    private Integer quantity;

    @Size(max = 511, message = "La información extra no debe ser mayor a 511 caracteres")
    private String extraInformation;
}
