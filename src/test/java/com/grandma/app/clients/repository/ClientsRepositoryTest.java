package com.grandma.app.clients.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

class ClientsRepositoryTest {
    private ClientsRepository clientsRepository = Mockito.mock(ClientsRepository.class);

    @Test
    void findByDocument() {
        Mockito.doReturn(null).when(clientsRepository).findByDocument(Mockito.anyString());

        clientsRepository.findByDocument("CC-1");

        Assertions.assertThat(clientsRepository.findByDocument("CC-1")).isNull();

        Mockito.verify(clientsRepository).findByDocument(Mockito.anyString());

    }

    @Test
    void findAll() {
        Mockito.doReturn(null).when(clientsRepository).findAll(Sort.by(Sort.Direction.ASC, "name"));

        clientsRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        Assertions.assertThat(clientsRepository.findAll()).isNull();

        Mockito.verify(clientsRepository).findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}