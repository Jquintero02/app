package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.exception.ClientAlreadyExistsException;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.mapper.ClientMapper;
import com.grandma.app.clients.repository.ClientsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {
    private final ClientsRepository repository;
    private final ClientMapper mapper;

    public ClientsService(ClientsRepository repository, ClientMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClientDto createClient(ClientDto client) {
        if (repository.existsByDocument(client.getDocument())) {
            throw new ClientAlreadyExistsException(
                    String.format("Cliente con documento %s ya existe", client.getDocument()));
        }

        var savedClient = repository.save(mapper.toModel(client));

        return mapper.toDto(savedClient);
    }

    public ClientDto getClient(String document) {
        return repository.findByDocument(document)
                .map(mapper::toDto)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));
    }

    public void updateClient(String document, ClientDto client) {
        var existingClient = repository.findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        if (!client.getDocument().equals(document)) {
            throw new IllegalArgumentException(String.format(
                    "No se puede modificar el documento de identidad %s", document));
        }

        boolean hasChanges = !existingClient.getName().equals(client.getName())
                || !existingClient.getEmail().equals(client.getEmail())
                || !existingClient.getPhone().equals(client.getPhone())
                || !existingClient.getDeliveryAddress().equals(client.getDeliveryAddress());

        if (!hasChanges) {
            throw new IllegalArgumentException("No hay ningún campo diferente en el Request.");
        }

        existingClient.setDocument(client.getDocument());
        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());
        existingClient.setDeliveryAddress(client.getDeliveryAddress());

        repository.save(existingClient);
    }

    public void deleteClient(String document) {
        var clientToDelete = repository
                .findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        repository.delete(clientToDelete);
    }
}
