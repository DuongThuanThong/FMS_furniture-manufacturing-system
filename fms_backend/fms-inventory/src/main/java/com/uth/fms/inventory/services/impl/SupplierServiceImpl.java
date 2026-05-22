package com.uth.fms.inventory.services.impl;

import com.uth.fms.inventory.entity.Supplier;
import com.uth.fms.inventory.repositories.SupplierRepository;
import com.uth.fms.inventory.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAllByDeleteFlagFalse();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findByIdAndDeleteFlagFalse(id);
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        supplier.setDeleteFlag(false);
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = supplierRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        supplier.setName(supplierDetails.getName());
        supplier.setPhone(supplierDetails.getPhone());
        supplier.setEmail(supplierDetails.getEmail());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setTaxCode(supplierDetails.getTaxCode());

        return supplierRepository.save(supplier);
    }

    @Override
    public void softDeleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        supplier.setDeleteFlag(true);
        supplierRepository.save(supplier);
    }
}
