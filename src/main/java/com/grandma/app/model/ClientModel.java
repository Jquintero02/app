package com.grandma.app.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientModel {
    public ClientModel() {
    }

    public ClientModel(UUID uuid, String document, String name, String phone, String deliveryAddress) {
        this.uuid = uuid;
        this.document = document;
        this.name = name;
        this.phone = phone;
        this.deliveryAddress = deliveryAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String deliveryAddress;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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
