package com.devkings.inventaryservice.repository;

import com.devkings.inventaryservice.model.Inventary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventary,Long> {
    Optional<Inventary> findBySkuCode(String skuCode);
}
