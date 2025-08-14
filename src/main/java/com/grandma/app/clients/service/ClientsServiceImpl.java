package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.mapper.ClientMapper;
import com.grandma.app.clients.repository.ClientsRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {
    private final ClientsRepository clientsPepository;
    private final ClientMapper clientMapper;

    public ClientsServiceImpl(ClientsRepository clientsPepository, ClientMapper clientMapper) {
        this.clientsPepository = clientsPepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto createClient(ClientDto clientDto) {
        return clientMapper
                .clientEntityToClientDto(clientsPepository.save(clientMapper.clientDtoToClientEntity(clientDto)));
    }

    public ClientDto getClient(String document) {
        return clientsPepository.findByDocument(document)
                .map(clientMapper::clientEntityToClientDto)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));
    }

    public void updateClient(String document, ClientDto client) {
        var existingClient = clientsPepository.findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        if (!client.getDocument().equals(document)) {
            throw new IllegalArgumentException(String.format(
                    "No se puede modificar el documento de identidad %s", document));
        }

        Boolean hasChanges = !existingClient.getName().equals(client.getName())
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

        clientsPepository.save(existingClient);
    }

    public void deleteClient(String document) {
        var clientToDelete = clientsPepository
                .findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        clientsPepository.delete(clientToDelete);
    }

    public Boolean existsClient(String document) {
        return clientsPepository.existsByDocument(document);
    }

    // BONUS TRACK
    public List<ClientDto> getOrderClients(String orderBy, String direction) {
        String orderByField;
        switch (orderBy == null ? "DOCUMENT" : orderBy.toUpperCase()) {
            case "NAME":
                orderByField = "name";
                break;
            case "ADDRESS":
                orderByField = "deliveryAddress";
                break;
            case "DOCUMENT":
            default:
                orderByField = "document";
                break;
        }
        Sort.Direction sortDirection = (direction == null || direction.equalsIgnoreCase("ASC")) ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sort = Sort.by(sortDirection, orderByField);
        return clientMapper.toListDto(clientsPepository.findAll(sort));
    }
}
