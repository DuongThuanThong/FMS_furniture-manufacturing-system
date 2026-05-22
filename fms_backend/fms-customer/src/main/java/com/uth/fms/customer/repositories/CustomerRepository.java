package com.uth.fms.customer.repositories;

import com.uth.fms.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDeleteFlagFalse();
    Optional<Customer> findByIdAndDeleteFlagFalse(Long id);
}
