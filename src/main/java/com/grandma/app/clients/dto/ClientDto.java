package com.grandma.app.clients.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto {
    @NotNull(message = "El documento es obligatorio")
    @Size(max = 20, message = "El documento no puede tener más de 20 caracteres")
    private String document;

    @NotNull(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    private String name;

    @NotNull(message = "El correo es obligatorio")
    @Size(max = 255, message = "El correo no puede tener más de 255 caracteres")
    @Email(message = "El formato del correo no es valido")
    private String email;

    @NotNull(message = "El telefono es obligatorio")
    @Size(max = 10, message = "El telefono no puede tener más de 10 caracteres")
    private String phone;

    @NotNull(message = "La direccion es obligatoria")
    @Size(max = 500, message = "La direccion no puede tener más de 500 caracteres")
    private String deliveryAddress;
}
