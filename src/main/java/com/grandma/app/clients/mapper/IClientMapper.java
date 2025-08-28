package com.grandma.app.clients.mapper;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface IClientMapper {

    IClientMapper INSTANCE = Mappers.getMapper(IClientMapper.class);

    ClientEntity clientDtoToClientEntity(ClientDto clientDto);

    ClientDto clientEntityToClientDto(ClientEntity clientEntity);

    List<ClientDto> listClientEntityToListClientDto(List<ClientEntity> entityList);
}

