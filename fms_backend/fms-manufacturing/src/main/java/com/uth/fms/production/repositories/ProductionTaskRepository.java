package com.uth.fms.production.repositories;

import com.uth.fms.production.entity.ProductionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionTaskRepository extends JpaRepository<ProductionTask, Long> {
    List<ProductionTask> findAllByDeleteFlagFalse();
    Optional<ProductionTask> findByIdAndDeleteFlagFalse(Long id);
}
