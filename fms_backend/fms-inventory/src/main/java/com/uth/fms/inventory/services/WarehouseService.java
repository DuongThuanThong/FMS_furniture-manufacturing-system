package com.uth.fms.inventory.services;

import com.uth.fms.inventory.entity.Warehouse;
import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    List<Warehouse> getAllWarehouses();
    Optional<Warehouse> getWarehouseById(Long id);
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(Long id, Warehouse warehouseDetails);
    void softDeleteWarehouse(Long id);
}
