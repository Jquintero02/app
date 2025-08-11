package com.grandma.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClientDto {
    public ClientDto() {
    }

    public ClientDto(String document, String name, String phone, String deliveryAddress) {
        this.document = document;
        this.name = name;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
    }

    @NotNull(message = "El documento es obligatorio")
    @Size(min = 6, max = 10, message = "El documento debe ser minimo de 3 caracteres y maximo 10 caracteres")
    private String document;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 3, max = 255, message = "")
    private String name;

    @NotNull(message = "El telefono es obligatorio")
    @Size(max = 10, message = "")
    private String phone;

    @NotNull(message = "La direccion es obligatoria")
    @Size(max = 511, message = "")
    private String deliveryAddress;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
