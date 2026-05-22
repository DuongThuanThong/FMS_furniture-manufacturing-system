package com.uth.fms.production.controller;

import com.uth.fms.production.entity.ProductionOrder;
import com.uth.fms.production.services.ProductionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-orders")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;

    @GetMapping
    public ResponseEntity<List<ProductionOrder>> getAllProductionOrders() {
        return ResponseEntity.ok(productionOrderService.getAllProductionOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionOrder> getProductionOrderById(@PathVariable Long id) {
        return productionOrderService.getProductionOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductionOrder> createProductionOrder(@RequestBody ProductionOrder productionOrder) {
        return ResponseEntity.ok(productionOrderService.createProductionOrder(productionOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionOrder> updateProductionOrder(@PathVariable Long id, @RequestBody ProductionOrder productionOrderDetails) {
        try {
            return ResponseEntity.ok(productionOrderService.updateProductionOrder(id, productionOrderDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductionOrder(@PathVariable Long id) {
        try {
            productionOrderService.softDeleteProductionOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
