package com.grandma.app.orders.dto;

import java.util.UUID;

public class OrderDto {
    public OrderDto() {
    }

    public OrderDto(String clientDocument, UUID productUuid, Integer quantity, String extraInformation) {
        this.clientDocument = clientDocument;
        this.productUuid = productUuid;
        this.quantity = quantity;
        this.extraInformation = extraInformation;
    }

    private String clientDocument;
    private UUID productUuid;
    private Integer quantity;
    private String extraInformation;

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
}
