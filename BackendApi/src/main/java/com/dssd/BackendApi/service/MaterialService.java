package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Material;

public interface MaterialService {
    Material createMaterial(String tipo);
    Iterable<Material> getAllMateriales();
    Material getMaterialById(Long id) throws Exception;
    Material updateMaterial(Long id, String tipo) throws Exception;
    void deleteMaterial(Long id) throws Exception;
}
