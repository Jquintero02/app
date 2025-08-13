package com.grandma.app.clients.repository;

import com.grandma.app.clients.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    boolean existsByDocument(String document);

    Optional<ClientModel> findByDocument(String document);

    void deleteByDocument(String document);
}
