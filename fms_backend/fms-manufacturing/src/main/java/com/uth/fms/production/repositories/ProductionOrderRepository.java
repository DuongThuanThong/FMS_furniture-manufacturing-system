package com.uth.fms.production.repositories;

import com.uth.fms.production.entity.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findAllByDeleteFlagFalse();
    Optional<ProductionOrder> findByIdAndDeleteFlagFalse(Long id);
}
