package com.grandma.app.repository;

import com.grandma.app.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
    ClientModel findByDocument(String document);
    void deleteByDocument(String document);
}
