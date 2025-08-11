package com.grandma.app.mapper;

import com.grandma.app.dto.ClientDto;
import com.grandma.app.model.ClientModel;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDto toDto(ClientModel client){
        if(client == null){
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setDocument(client.getDocument());
        clientDto.setName(client.getName());
        clientDto.setPhone(client.getPhone());
        clientDto.setDeliveryAddress(client.getDeliveryAddress());

        return clientDto;
    };

    public ClientModel toModel(ClientDto client){
        if(client == null){
            return null;
        }

        ClientModel clientModel = new ClientModel();

        clientModel.setDocument(client.getDocument());
        clientModel.setName(client.getName());
        clientModel.setPhone(client.getPhone());
        clientModel.setDeliveryAddress(client.getDeliveryAddress());

        return clientModel;
    }
}
