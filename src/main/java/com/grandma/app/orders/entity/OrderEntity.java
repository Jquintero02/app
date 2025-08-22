package com.grandma.app.orders.entity;

import com.grandma.app.clients.entity.ClientEntity;
import com.grandma.app.products.entity.ProductEntity;
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
    private LocalDateTime creationDateTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_document", nullable = false)
    private ClientEntity client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_uuid", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "extra_information", nullable = true)
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
