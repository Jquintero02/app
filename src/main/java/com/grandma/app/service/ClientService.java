package com.grandma.app.service;

import com.grandma.app.dto.ClientDto;
import com.grandma.app.mapper.ClientMapper;
import com.grandma.app.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientService(ClientRepository repository, ClientMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClientDto createClient(ClientDto client){
        if(client ==null){
            return null;
        }

        var savedClient = repository.save(mapper.toModel(client));

        return mapper.toDto(savedClient);
    }

    public ClientDto getClient(String document){
        if(document == null){
            return null;
        }

        var foundClient = repository.findByDocument(document);

        return mapper.toDto(foundClient);
    }

    public void updateClient(String document, ClientDto client){
        if(document == null || client == null){
            return;
        }

        var existClient = repository.findByDocument(document);

        if(existClient == null
                || (existClient.getName().equals(client.getName())
                && existClient.getPhone().equals(client.getPhone())
                && existClient.getDeliveryAddress().equals(client.getDeliveryAddress())
                )
        ){
            return;
        }

        existClient.setName(client.getName());
        existClient.setPhone(client.getPhone());
        existClient.setDeliveryAddress(client.getDeliveryAddress());

        repository.save(existClient);
    }

    public void deleteClient(String document){
        if(document == null){
            return;
        }

        var foundClient = repository.findByDocument(document);

        if(foundClient == null){
            return;
        }

        repository.deleteByDocument(document);
    }
}
