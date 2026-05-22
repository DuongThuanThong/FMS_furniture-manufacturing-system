package com.uth.fms.production.services;

import com.uth.fms.production.entity.ProductionTask;
import java.util.List;
import java.util.Optional;

public interface ProductionTaskService {
    List<ProductionTask> getAllProductionTasks();
    Optional<ProductionTask> getProductionTaskById(Long id);
    ProductionTask createProductionTask(ProductionTask productionTask);
    ProductionTask updateProductionTask(Long id, ProductionTask productionTaskDetails);
    void softDeleteProductionTask(Long id);
}
