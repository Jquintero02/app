package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.mapper.IClientMapper;
import com.grandma.app.clients.repository.ClientsRepository;
import com.grandma.app.exceptions.NotDifferentFieldException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsServiceImpl implements ClientsService {
    private  ClientsRepository clientsRepository;
    private final IClientMapper clientMapper;

    public ClientsServiceImpl(ClientsRepository clientsRepository){
        this.clientsRepository = clientsRepository;
        this.clientMapper = IClientMapper.INSTANCE;
    }

    public ClientDto createClient(ClientDto clientDto) {
        ClientEntity entity = clientMapper.clientDtoToClientEntity(clientDto);
        ClientEntity save = clientsRepository.save(entity);

        return clientMapper
                .clientEntityToClientDto(save);
    }

    public ClientDto getClient(String document) {
        return clientsRepository.findByDocument(document)
                .map(clientMapper::clientEntityToClientDto)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));
    }

    public void updateClient(String document, ClientDto clientDto) {
        var existingClient = clientsRepository.findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        if (!clientDto.getDocument().equals(document)) {
            throw new IllegalArgumentException(String.format(
                    "No se puede modificar el documento de identidad %s", document));
        }

        Boolean hasChanges = !existingClient.getName().equals(clientDto.getName())
                || !existingClient.getEmail().equals(clientDto.getEmail())
                || !existingClient.getPhone().equals(clientDto.getPhone())
                || !existingClient.getDeliveryAddress().equals(clientDto.getDeliveryAddress());

        if (!hasChanges) {
            throw new NotDifferentFieldException("No hay ningún campo diferente en el Request.");
        }

        existingClient.setDocument(clientDto.getDocument());
        existingClient.setName(clientDto.getName());
        existingClient.setEmail(clientDto.getEmail());
        existingClient.setPhone(clientDto.getPhone());
        existingClient.setDeliveryAddress(clientDto.getDeliveryAddress());

        clientsRepository.save(existingClient);
    }

    public void deleteClient(String document) {
        var clientToDelete = clientsRepository
                .findByDocument(document)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cliente con documento %s no encontrado", document)));

        clientsRepository.delete(clientToDelete);
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
        return clientMapper.listClientEntityToListClientDto(clientsRepository.findAll(sort));
    }
}
