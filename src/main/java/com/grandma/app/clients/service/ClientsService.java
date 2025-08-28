package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;

import java.util.List;

public interface ClientsService {
    ClientDto createClient(ClientDto clientDto);

    ClientDto getClient(String document);

    void updateClient(String document, ClientDto clientDto);

    void deleteClient(String document);

    // BONUS TRACK
    List<ClientDto> getOrderClients(String orderBy, String direction);
}
