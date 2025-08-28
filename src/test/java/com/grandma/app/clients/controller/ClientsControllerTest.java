package com.grandma.app.clients.controller;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.service.ClientsService;

class ClientsControllerTest {
    @Mock
    private ClientsService clientsService;
    private ClientsController clientsController;

    @BeforeEach
    void setUp() {
        clientsController = new ClientsController(clientsService);
    }

    @Test
    void createClient() {
        Mockito.doReturn(Optional.of(new ClientDto())).when(clientsService).createClient(Mockito.any(ClientDto.class));

        ClientDto clientDto = new ClientDto(
                "CC-1",
                "Juan",
                "juan@gmail.com",
                "3128283889",
                "cl 9a");

        clientsController.createClient(clientDto);

        Assertions.assertThat(clientsController.createClient(clientDto)).isNotNull();

        Mockito.verify(clientsService).createClient(Mockito.any(ClientDto.class));
    }

    @Test
    void getClient() {
        Mockito.doReturn(new ClientDto()).when(clientsService).getClient(Mockito.anyString());

        clientsController.getClient("CC-1");

        Assertions.assertThat(clientsController.getClient("CC-1")).isNotNull();

        Mockito.verify(clientsService).getClient(Mockito.anyString());
    }

    @Test
    void updateClient() {
        Mockito.doNothing().when(clientsService).updateClient(Mockito.anyString(), Mockito.any(ClientDto.class));

        ClientDto clientDto = new ClientDto(
                "CC-1",
                "Felipe",
                "felipe@gmail.com",
                "3128283889",
                "cl 9a");

        clientsController.updateClient("CC-1", clientDto);

        Assertions.assertThat(clientsController.updateClient("CC-1", clientDto)).isNotNull();

        Mockito.verify(clientsService).updateClient(Mockito.anyString(), Mockito.any(ClientDto.class));
    }

    @Test
    void deleteClient() {
        Mockito.doNothing().when(clientsService).deleteClient(Mockito.anyString());

        clientsController.deleteClient("CC-1");

        Assertions.assertThat(clientsController.deleteClient("CC-1")).isNotNull();

        Mockito.verify(clientsService).deleteClient(Mockito.anyString());
    }

    @Test
    void getOrderClients() {
        Mockito.doReturn(Optional.of(new ClientDto())).when(clientsService).getOrderClients(Mockito.anyString(),
                Mockito.anyString());

        clientsController.getOrderClients("name", "ASC");

        Assertions.assertThat(clientsController.getOrderClients("name", "ASC")).isNotNull();

        Mockito.verify(clientsService).getOrderClients(Mockito.anyString(), Mockito.anyString());
    }
}