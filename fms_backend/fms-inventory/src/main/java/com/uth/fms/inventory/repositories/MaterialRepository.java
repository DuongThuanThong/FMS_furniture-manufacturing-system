package com.uth.fms.inventory.repositories;

import com.uth.fms.inventory.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAllByDeleteFlagFalse();
    Optional<Material> findByIdAndDeleteFlagFalse(Long id);
}
