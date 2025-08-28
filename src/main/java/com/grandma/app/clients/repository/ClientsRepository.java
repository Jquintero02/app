package com.grandma.app.clients.repository;

import com.grandma.app.clients.entity.ClientEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByDocument(String document);

    // BONUS TRACK: Ordenación dinámica usando Sort (query named)
    List<ClientEntity> findAll(Sort sort);
}
