package com.grandma.app.orders.entity;

import com.grandma.app.clients.entity.ClientEntity;
import com.grandma.app.products.entity.ProductEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {
    public OrderEntity() {
    }

    public OrderEntity(UUID uuid, LocalDateTime creationDateTime, String clientDocument, UUID productUuid,
            Integer quantity, String extraInformation, BigDecimal subTotal, BigDecimal tax, BigDecimal grandTotal,
            Boolean delivered, LocalDateTime deliveredDate, ClientEntity client, ProductEntity product) {
        this.uuid = uuid;
        this.creationDateTime = creationDateTime;
        this.clientDocument = clientDocument;
        this.productUuid = productUuid;
        this.quantity = quantity;
        this.extraInformation = extraInformation;
        this.subTotal = subTotal;
        this.tax = tax;
        this.grandTotal = grandTotal;
        this.delivered = delivered;
        this.deliveredDate = deliveredDate;
        this.client = client;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    @NotNull(message = "La fecha del cliente no puede ser vacio")
    private LocalDateTime creationDateTime;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Size(max = 20, message = "El documento no puede tener más de 20 caracteres")
    private String clientDocument;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El producto no puede ser vacio")
    private UUID productUuid;

    @Column(nullable = false)
    @NotNull(message = "El documento del cliente no puede ser vacio")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Max(value = 100, message = "La cantidad no debe ser mayor a 0")
    private Integer quantity;

    @Column(nullable = true)
    @Size(max = 511, message = "La información extra no debe ser mayor a 511 caracteres")
    private String extraInformation;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El subtotal no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El subtotal debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El subtotal no puede tener más de 2 decimales")
    private BigDecimal subTotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El iva no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El iva debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El iva no puede tener más de 2 decimales")
    private BigDecimal tax;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    @NotNull(message = "El total no puede ser vacio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El total no puede tener más de 2 decimales")
    private BigDecimal grandTotal;

    @Column(nullable = false)
    private Boolean delivered;

    @Column(nullable = true)
    private LocalDateTime deliveredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientDocument", referencedColumnName = "document", unique = true, insertable = false, updatable = false)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productUuid", referencedColumnName = "uuid", unique = true, insertable = false, updatable = false)
    private ProductEntity product;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public String getClientDocument() {
        return clientDocument;
    }

    public void setClientDocument(String clientDocument) {
        this.clientDocument = clientDocument;
    }

    public UUID getProductUuid() {
        return productUuid;
    }

    public void setProductUuid(UUID productUuid) {
        this.productUuid = productUuid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
