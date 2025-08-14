package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;

import java.util.List;

public interface ClientsService {
    ClientDto createClient(ClientDto client);

    ClientDto getClient(String document);

    void updateClient(String document, ClientDto client);

    void deleteClient(String document);

    Boolean existsClient(String document);

    // BONUS TRACK
    List<ClientDto> getOrderClients(String orderBy, String direction);
}
