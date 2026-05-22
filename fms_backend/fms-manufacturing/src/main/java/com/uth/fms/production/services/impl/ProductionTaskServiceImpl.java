package com.uth.fms.production.services.impl;

import com.uth.fms.production.entity.ProductionTask;
import com.uth.fms.production.repositories.ProductionTaskRepository;
import com.uth.fms.production.services.ProductionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionTaskServiceImpl implements ProductionTaskService {

    @Autowired
    private ProductionTaskRepository productionTaskRepository;

    @Override
    public List<ProductionTask> getAllProductionTasks() {
        return productionTaskRepository.findAllByDeleteFlagFalse();
    }

    @Override
    public Optional<ProductionTask> getProductionTaskById(Long id) {
        return productionTaskRepository.findByIdAndDeleteFlagFalse(id);
    }

    @Override
    public ProductionTask createProductionTask(ProductionTask productionTask) {
        productionTask.setDeleteFlag(false);
        return productionTaskRepository.save(productionTask);
    }

    @Override
    public ProductionTask updateProductionTask(Long id, ProductionTask productionTaskDetails) {
        ProductionTask productionTask = productionTaskRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("ProductionTask not found with id: " + id));

        productionTask.setProductionOrder(productionTaskDetails.getProductionOrder());
        productionTask.setTaskType(productionTaskDetails.getTaskType());
        productionTask.setSortOrder(productionTaskDetails.getSortOrder());
        productionTask.setStatus(productionTaskDetails.getStatus());
        productionTask.setAssignedTo(productionTaskDetails.getAssignedTo());
        productionTask.setEstimatedHours(productionTaskDetails.getEstimatedHours());
        productionTask.setActualHours(productionTaskDetails.getActualHours());
        productionTask.setLaborCost(productionTaskDetails.getLaborCost());
        productionTask.setDeadline(productionTaskDetails.getDeadline());
        productionTask.setStartedAt(productionTaskDetails.getStartedAt());
        productionTask.setCompletedAt(productionTaskDetails.getCompletedAt());

        return productionTaskRepository.save(productionTask);
    }

    @Override
    public void softDeleteProductionTask(Long id) {
        ProductionTask productionTask = productionTaskRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("ProductionTask not found with id: " + id));
        productionTask.setDeleteFlag(true);
        productionTaskRepository.save(productionTask);
    }
}
