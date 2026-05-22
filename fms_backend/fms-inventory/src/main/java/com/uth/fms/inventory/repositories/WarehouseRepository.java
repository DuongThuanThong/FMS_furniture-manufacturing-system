package com.uth.fms.inventory.repositories;

import com.uth.fms.inventory.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findAllByActiveTrue();
    Optional<Warehouse> findByIdAndActiveTrue(Long id);
}
