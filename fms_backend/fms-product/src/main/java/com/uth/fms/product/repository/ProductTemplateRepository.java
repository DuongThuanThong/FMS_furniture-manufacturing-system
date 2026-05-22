package com.uth.fms.product.repository;

import com.uth.fms.product.entity.ProductTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, Long> {

    Optional<ProductTemplate> findByCodeAndDeleteFlagFalse(String code);

    Optional<ProductTemplate> findByIdAndDeleteFlagFalse(Long id);

    boolean existsByCodeAndDeleteFlagFalse(String code);

    @Query("SELECT p FROM ProductTemplate p WHERE p.deleteFlag = false " +
            "AND (:keyword IS NULL OR LOWER(p.code) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<ProductTemplate> search(@Param("keyword") String keyword, Pageable pageable);
}