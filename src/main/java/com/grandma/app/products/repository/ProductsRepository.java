package com.grandma.app.products.repository;

import com.grandma.app.products.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByFantasyName(String fantasyName);

    Optional<ProductEntity> findByUuid(UUID uuid);

    // BONUS TRACK
    @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.fantasyName) LIKE LOWER(CONCAT('%', :partialFantasyName, '%')) ORDER BY p.fantasyName ASC")
    List<ProductEntity> findByPartialFantasyName(@Param("partialFantasyName") String partialFantasyName);
}
