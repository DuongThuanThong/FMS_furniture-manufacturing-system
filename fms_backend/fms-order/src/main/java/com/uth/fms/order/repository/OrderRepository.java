package com.uth.fms.order.repository;

import com.uth.fms.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndDeleteFlagFalse(Long id);

    @Query("SELECT o FROM Order o WHERE o.deleteFlag = false " +
           "AND (:keyword IS NULL OR LOWER(o.orderCode) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Order> search(@Param("keyword") String keyword, Pageable pageable);
}