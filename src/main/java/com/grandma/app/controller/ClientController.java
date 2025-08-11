package com.grandma.app.controller;

import com.grandma.app.dto.ClientDto;
import com.grandma.app.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto client){
        if(client == null){
            return null;
        }

        var createdClient = service.createClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @GetMapping("/{document}")
    public ResponseEntity<ClientDto> getClient(@PathVariable("document") String document){
        if(document == null){
            return null;
        }

        var foundClient = service.getClient(document);

        return ResponseEntity.status(HttpStatus.FOUND).body(foundClient);
    }

    @PutMapping("/{document}")
    public ResponseEntity<?> updateClient(@PathVariable("document") String document, @RequestBody ClientDto client){
        if(document == null || client == null){
            return null;
        }

        service.updateClient(document, client);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<?> deleteClient(@PathVariable("document") String document){
        if(document == null){
            return null;
        }

        service.deleteClient(document);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
