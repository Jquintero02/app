package com.grandma.app.clients.mapper;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public ClientDto toDto(ClientEntity client){
        if(client == null){
            throw new NullPointerException("El cliente no puede ser nulo");
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setDocument(client.getDocument());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPhone(client.getPhone());
        clientDto.setDeliveryAddress(client.getDeliveryAddress());

        return clientDto;
    };

    public ClientEntity toModel(ClientDto client){
        if(client == null){
            throw new NullPointerException("El cliente no puede ser nulo");
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setDocument(client.getDocument());
        clientEntity.setName(client.getName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setDeliveryAddress(client.getDeliveryAddress());

        return clientEntity;
    }

    public List<ClientDto> toListDto(List<ClientEntity> entityList){
        List<ClientDto> listDto = new ArrayList<>();

        for (ClientEntity entity : entityList){
            ClientDto clientDto = new ClientDto();
            clientDto.setDocument(entity.getDocument());
            clientDto.setName(entity.getName());
            clientDto.setEmail(entity.getEmail());
            clientDto.setPhone(entity.getPhone());
            clientDto.setDeliveryAddress(entity.getDeliveryAddress());
            listDto.add(clientDto);
        }

        return listDto;
    }
}
