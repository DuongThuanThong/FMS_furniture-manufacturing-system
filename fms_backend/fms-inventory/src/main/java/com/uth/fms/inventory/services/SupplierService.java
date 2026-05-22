package com.uth.fms.inventory.services;

import com.uth.fms.inventory.entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Optional<Supplier> getSupplierById(Long id);
    Supplier createSupplier(Supplier supplier);
    Supplier updateSupplier(Long id, Supplier supplierDetails);
    void softDeleteSupplier(Long id);
}
