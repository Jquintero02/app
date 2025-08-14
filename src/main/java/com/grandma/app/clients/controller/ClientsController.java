package com.grandma.app.clients.controller;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.exception.ClientAlreadyExistsException;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.service.ClientsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientService;

    public ClientsController(ClientsService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto client) {
        if (clientService.existsClient(client.getDocument())) {
            throw new ClientAlreadyExistsException(
                    String.format("Cliente con documento %s ya existe", client.getDocument()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(client));
    }

    @GetMapping("/{document}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("document") String document) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Formato de documento inválido: %s", document));
        }

        var client = clientService.getClient(document);

        if (client == null) {
            throw new ClientNotFoundException(String.format("Cliente con documento %s no encontrado", document));
        }

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PutMapping("/{document}")
    public ResponseEntity<?> updateClient(@PathVariable("document") String document,
            @Valid @RequestBody ClientDto client) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Formato de documento inválido: %s", document));
        }

        if (client.getName() == null
                || client.getName().isEmpty() || client.getEmail() == null || client.getEmail().isEmpty()
                || client.getPhone() == null || client.getPhone().isEmpty() || client.getDeliveryAddress() == null
                || client.getDeliveryAddress().isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Datos del cliente inválidos o incompletos: %s", client));
        }

        clientService.updateClient(document, client);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<?> deleteClient(@PathVariable("document") String document) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                    "Formato de documento inválido: %s", document));
        }

        clientService.deleteClient(document);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // BONUS TRACK
    @GetMapping
    public ResponseEntity<?> getOrderClients(@RequestParam(name = "orderBy", required = false) String orderBy,
            @RequestParam(name = "direction", required = false) String direction) {
        if (orderBy == null) {
            orderBy = "DOCUMENT";
        }
        if (direction == null) {
            direction = "ASC";
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getOrderClients(orderBy, direction));
    }
}
