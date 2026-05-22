package com.uth.fms.production.services;

import com.uth.fms.production.entity.ProductionOrder;
import java.util.List;
import java.util.Optional;

public interface ProductionOrderService {
    List<ProductionOrder> getAllProductionOrders();
    Optional<ProductionOrder> getProductionOrderById(Long id);
    ProductionOrder createProductionOrder(ProductionOrder productionOrder);
    ProductionOrder updateProductionOrder(Long id, ProductionOrder productionOrderDetails);
    void softDeleteProductionOrder(Long id);
}
