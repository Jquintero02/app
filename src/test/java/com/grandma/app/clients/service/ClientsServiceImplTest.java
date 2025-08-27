package com.grandma.app.clients.service;

import com.grandma.app.clients.dto.ClientDto;
import com.grandma.app.clients.entity.ClientEntity;
import com.grandma.app.clients.exception.ClientNotFoundException;
import com.grandma.app.clients.repository.ClientsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    // @Test
    // void updateClient() {
    // }
    //

    @Test
    void deleteClient() {
        Mockito.doNothing().when(clientsRepository).delete(Mockito.any(ClientEntity.class));

        clientsService.deleteClient("CC-1");

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            Mockito.verify(clientsRepository).findByDocument(Mockito.anyString());
        });
    }

    //
    // @Test
    // void getOrderClients() {
    // Mockito.when(clientsService.getOrderClients("NAME",
    // "ASC")).thenReturn(List.of(clientDto));
    // }
}