package com.grandma.app.clients.controller;

import com.grandma.app.clients.dto.ClientDto;
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
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDto));
    }

    @GetMapping("/{document}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("document") String document) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClient(document));
    }

    @PutMapping("/{document}")
    public ResponseEntity<?> updateClient(@PathVariable("document") String document,
            @Valid @RequestBody ClientDto clientDto) {

        clientService.updateClient(document, clientDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<?> deleteClient(@PathVariable("document") String document) {

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
