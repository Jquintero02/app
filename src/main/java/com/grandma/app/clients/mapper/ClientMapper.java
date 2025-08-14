package com.grandma.app.clients.mapper;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public ClientDto clientEntityToClientDto(ClientEntity clientEntity) {
        if (clientEntity == null) {
            throw new NullPointerException("El cliente no puede ser nulo");
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setDocument(clientEntity.getDocument());
        clientDto.setName(clientEntity.getName());
        clientDto.setEmail(clientEntity.getEmail());
        clientDto.setPhone(clientEntity.getPhone());
        clientDto.setDeliveryAddress(clientEntity.getDeliveryAddress());

        return clientDto;
    };

    public ClientEntity clientDtoToClientEntity(ClientDto clientDto) {
        if (clientDto == null) {
            throw new NullPointerException("El cliente no puede ser nulo");
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setDocument(clientDto.getDocument());
        clientEntity.setName(clientDto.getName());
        clientEntity.setEmail(clientDto.getEmail());
        clientEntity.setPhone(clientDto.getPhone());
        clientEntity.setDeliveryAddress(clientDto.getDeliveryAddress());

        return clientEntity;
    }

    public List<ClientDto> toListDto(List<ClientEntity> entityList) {
        List<ClientDto> listDto = new ArrayList<>();

        for (ClientEntity entity : entityList) {
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
