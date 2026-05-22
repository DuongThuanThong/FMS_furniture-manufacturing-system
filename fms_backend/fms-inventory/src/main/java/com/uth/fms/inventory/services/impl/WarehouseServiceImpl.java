package com.uth.fms.inventory.services.impl;

import com.uth.fms.inventory.entity.Warehouse;
import com.uth.fms.inventory.repositories.WarehouseRepository;
import com.uth.fms.inventory.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        warehouse.setActive(true);
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouseDetails) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found or inactive with id: " + id));

        warehouse.setName(warehouseDetails.getName());
        warehouse.setLocation(warehouseDetails.getLocation());
        warehouse.setActive(warehouseDetails.getActive());

        return warehouseRepository.save(warehouse);
    }

    @Override
    public void softDeleteWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found or inactive with id: " + id));
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
    }
}
