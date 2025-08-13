package com.grandma.app.mapper;

import com.grandma.app.dto.ClientDto;
import com.grandma.app.model.ClientModel;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(ClientModel client){
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

    public ClientModel toModel(ClientDto client){
        if(client == null){
            throw new NullPointerException("El cliente no puede ser nulo");
        }

        ClientModel clientModel = new ClientModel();

        clientModel.setDocument(client.getDocument());
        clientModel.setName(client.getName());
        clientModel.setEmail(client.getEmail());
        clientModel.setPhone(client.getPhone());
        clientModel.setDeliveryAddress(client.getDeliveryAddress());

        return clientModel;
    }
}
