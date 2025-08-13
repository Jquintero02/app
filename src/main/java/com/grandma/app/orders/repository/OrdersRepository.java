package com.grandma.app.orders.repository;

import com.grandma.app.orders.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, UUID> {
    boolean existsByUuid(UUID uuid);
}
