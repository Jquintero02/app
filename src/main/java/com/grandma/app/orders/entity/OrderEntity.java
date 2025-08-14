package com.grandma.app.orders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "creation_date_time", nullable = false)
    @NotNull(message = "La fecha del cliente no puede ser vacio")
    private LocalDateTime creationDateTime;

    @Column(name = "client_uuid", nullable = false, unique = true)
    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Size(max = 20, message = "El documento no puede tener más de 20 caracteres")
    @JoinColumn(name = "client_uuid", referencedColumnName = "uuid")
    private String clientUuid;

    @Column(name = "product_uuid", nullable = false, unique = true)
    @NotNull(message = "El producto no puede ser vacio")
    @JoinColumn(name = "product_uuid", referencedColumnName = "uuid")
    private String productUuid;

    @Column(nullable = false)
    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Max(value = 100, message = "La cantidad no debe ser mayor a 0")
    private Integer quantity;

    @Column(name = "extra_information", nullable = true)
    @Size(max = 511, message = "La información extra no debe ser mayor a 511 caracteres")
    private String extraInformation;

    @Column(name = "sub_total", nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El subtotal no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El subtotal debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El subtotal no puede tener más de 2 decimales")
    private BigDecimal subTotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El iva no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El iva debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El iva no puede tener más de 2 decimales")
    private BigDecimal tax;

    @Column(name = "grand_total", nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El total no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El total no puede tener más de 2 decimales")
    private BigDecimal grandTotal;

    @Column(nullable = false)
    private Boolean delivered;

    @Column(name = "delivered_date", nullable = true)
    private LocalDateTime deliveredDate;

}
