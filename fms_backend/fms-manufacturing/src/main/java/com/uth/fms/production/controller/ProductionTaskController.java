package com.uth.fms.production.controller;

import com.uth.fms.production.entity.ProductionTask;
import com.uth.fms.production.services.ProductionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-tasks")
public class ProductionTaskController {

    @Autowired
    private ProductionTaskService productionTaskService;

    @GetMapping
    public ResponseEntity<List<ProductionTask>> getAllProductionTasks() {
        return ResponseEntity.ok(productionTaskService.getAllProductionTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionTask> getProductionTaskById(@PathVariable Long id) {
        return productionTaskService.getProductionTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductionTask> createProductionTask(@RequestBody ProductionTask productionTask) {
        return ResponseEntity.ok(productionTaskService.createProductionTask(productionTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionTask> updateProductionTask(@PathVariable Long id, @RequestBody ProductionTask productionTaskDetails) {
        try {
            return ResponseEntity.ok(productionTaskService.updateProductionTask(id, productionTaskDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductionTask(@PathVariable Long id) {
        try {
            productionTaskService.softDeleteProductionTask(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
