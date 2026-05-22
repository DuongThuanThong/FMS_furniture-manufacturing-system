package com.uth.fms.production.services.impl;

import com.uth.fms.production.entity.ProductionOrder;
import com.uth.fms.production.repositories.ProductionOrderRepository;
import com.uth.fms.production.services.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionOrderServiceImpl implements ProductionOrderService {

    @Autowired
    private ProductionOrderRepository productionOrderRepository;

    @Override
    public List<ProductionOrder> getAllProductionOrders() {
        return productionOrderRepository.findAllByDeleteFlagFalse();
    }

    @Override
    public Optional<ProductionOrder> getProductionOrderById(Long id) {
        return productionOrderRepository.findByIdAndDeleteFlagFalse(id);
    }

    @Override
    public ProductionOrder createProductionOrder(ProductionOrder productionOrder) {
        productionOrder.setDeleteFlag(false);
        return productionOrderRepository.save(productionOrder);
    }

    @Override
    public ProductionOrder updateProductionOrder(Long id, ProductionOrder productionOrderDetails) {
        ProductionOrder productionOrder = productionOrderRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("ProductionOrder not found with id: " + id));

        productionOrder.setCode(productionOrderDetails.getCode());
        productionOrder.setOrderId(productionOrderDetails.getOrderId());
        productionOrder.setBomType(productionOrderDetails.getBomType());
        productionOrder.setStatus(productionOrderDetails.getStatus());
        productionOrder.setPlannedStartDate(productionOrderDetails.getPlannedStartDate());
        productionOrder.setPlannedEndDate(productionOrderDetails.getPlannedEndDate());
        productionOrder.setActualMaterialCost(productionOrderDetails.getActualMaterialCost());
        productionOrder.setActualLaborCost(productionOrderDetails.getActualLaborCost());
        productionOrder.setActualWasteCost(productionOrderDetails.getActualWasteCost());

        return productionOrderRepository.save(productionOrder);
    }

    @Override
    public void softDeleteProductionOrder(Long id) {
        ProductionOrder productionOrder = productionOrderRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("ProductionOrder not found with id: " + id));
        productionOrder.setDeleteFlag(true);
        productionOrderRepository.save(productionOrder);
    }
}
