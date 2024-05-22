package com.devkings.inventaryservice.repository;

import com.devkings.inventaryservice.model.Inventary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventary,Long> {
    List<Inventary> findBySkuCodeIn(List<String> skuCode);
}
