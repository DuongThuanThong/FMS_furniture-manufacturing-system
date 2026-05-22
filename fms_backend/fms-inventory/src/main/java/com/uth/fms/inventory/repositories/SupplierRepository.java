package com.uth.fms.inventory.repositories;

import com.uth.fms.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findAllByDeleteFlagFalse();
    Optional<Supplier> findByIdAndDeleteFlagFalse(Long id);
}
