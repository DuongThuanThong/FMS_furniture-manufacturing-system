package com.uth.fms.order.repository;

import com.uth.fms.product.entity.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, Long> {
    Optional<ProductTemplate> findByIdAndDeleteFlagFalse(Long id);
}