package com.grandma.app.clients.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El documento es obligatorio")
    @Size(max = 20, message = "El documento no puede tener más de 20 caracteres")
    private String document;

    @Column(nullable = false)
    @NotNull(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "El correo es obligatorio")
    @Size(max = 255, message = "El correo no puede tener más de 255 caracteres")
    @Email(message = "El formato del correo no es valido")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "El telefono es obligatorio")
    @Size(max = 10, message = "El telefono no puede tener más de 10 caracteres")
    private String phone;

    @Column(nullable = false)
    @NotNull(message = "La direccion es obligatoria")
    @Size(max = 500, message = "La direccion no puede tener más de 500 caracteres")
    private String deliveryAddress;
}
