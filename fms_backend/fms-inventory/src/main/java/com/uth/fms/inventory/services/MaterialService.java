package com.uth.fms.inventory.services;

import com.uth.fms.inventory.entity.Material;
import java.util.List;
import java.util.Optional;

public interface MaterialService {
    List<Material> getAllMaterials();
    Optional<Material> getMaterialById(Long id);
    Material createMaterial(Material material);
    Material updateMaterial(Long id, Material materialDetails);
    void softDeleteMaterial(Long id);
}
