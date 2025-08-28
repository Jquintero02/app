package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import com.grandma.app.clients.repository.ClientsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ClientsServiceImplTest {
    @Mock
    private ClientsRepository clientsRepository;
    private ClientsServiceImpl clientsService;

    @BeforeEach
    void setUp() {
        clientsService = new ClientsServiceImpl(clientsRepository);
    }

    @Test
    void createClient() {
        Mockito.doAnswer(invocationOnMock -> {
            ClientEntity clientEntity = invocationOnMock.getArgument(0);
            clientEntity.setUuid(UUID.randomUUID().toString());
            return clientEntity;
        }).when(clientsRepository).save(Mockito.any(ClientEntity.class));

        ClientDto clientDto = new ClientDto(
                "CC-1",
                "Juan",
                "juan@gmail.com",
                "3128283889",
                "cl 9a");

        ClientDto result = clientsService.createClient(clientDto);

        Assertions.assertNotNull(result);
        Mockito.verify(clientsRepository).save(Mockito.any(ClientEntity.class));
    }

    @Test
    void getClient() {
        Mockito.doReturn(Optional.of(new ClientEntity())).when(clientsRepository).findByDocument(Mockito.anyString());
        ClientDto result = clientsService.getClient("CC-1");
        Assertions.assertNotNull(result);
        Mockito.verify(clientsRepository).findByDocument(Mockito.anyString());
    }

    @Test
    void updateClient() {
        ClientEntity existingClient = new ClientEntity();

        existingClient.setDocument("CC-1");
        existingClient.setName("Juan");
        existingClient.setEmail("juan@gmai  l.com");
        existingClient.setPhone("3128283889");
        existingClient.setDeliveryAddress("cl 9a");

        Mockito.doReturn(Optional.of(existingClient)).when(clientsRepository).findByDocument("CC-1");

        ClientDto updatedClientDto = new ClientDto(
                "CC-1",
                "Juan Perez",
                "juanp@gmail.com",
                "3128283890",
                "cl 10a");

        clientsService.updateClient("CC-1", updatedClientDto);

        Mockito.verify(clientsRepository).save(Mockito.any(ClientEntity.class));

        Mockito.verify(clientsRepository).findByDocument("CC-1");
    }

    @Test
    void deleteClient() {
        Mockito.doReturn(Optional.of(new ClientEntity())).when(clientsRepository).findByDocument(Mockito.anyString());

        clientsService.deleteClient("CC-1");

        Mockito.verify(clientsRepository).delete(Mockito.any(ClientEntity.class));

        Mockito.verify(clientsRepository).findByDocument(Mockito.anyString());
    }

    @Test
    void getOrderClients() {
        List<ClientEntity> clientEntities = new ArrayList<>();
        clientEntities.add(new ClientEntity());

        Mockito.doReturn(clientEntities).when(clientsRepository).findAll();

        List<ClientDto> result = clientsService.getOrderClients("name", "asc");

        Assertions.assertNotNull(result);
        Mockito.verify(clientsRepository).findAll();
    }
}