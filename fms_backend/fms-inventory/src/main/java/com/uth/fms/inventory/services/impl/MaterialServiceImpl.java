package com.uth.fms.inventory.services.impl;

import com.uth.fms.inventory.entity.Material;
import com.uth.fms.inventory.repositories.MaterialRepository;
import com.uth.fms.inventory.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAllByDeleteFlagFalse();
    }

    @Override
    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findByIdAndDeleteFlagFalse(id);
    }

    @Override
    public Material createMaterial(Material material) {
        material.setDeleteFlag(false);
        return materialRepository.save(material);
    }

    @Override
    public Material updateMaterial(Long id, Material materialDetails) {
        Material material = materialRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));

        material.setMaterialCode(materialDetails.getMaterialCode());
        material.setName(materialDetails.getName());
        material.setUnit(materialDetails.getUnit());
        material.setCurrentStock(materialDetails.getCurrentStock());
        material.setReservedStock(materialDetails.getReservedStock());
        material.setMinStock(materialDetails.getMinStock());
        material.setUnitPrice(materialDetails.getUnitPrice());
        material.setStatus(materialDetails.getStatus());
        material.setCategory(materialDetails.getCategory());

        return materialRepository.save(material);
    }

    @Override
    public void softDeleteMaterial(Long id) {
        Material material = materialRepository.findByIdAndDeleteFlagFalse(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));
        material.setDeleteFlag(true);
        materialRepository.save(material);
    }
}
