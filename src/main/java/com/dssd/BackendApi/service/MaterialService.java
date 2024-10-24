package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Material;

import java.util.Optional;

public interface MaterialService {
    Material createMaterial(String tipo);
    Iterable<Material> getAllMateriales();
    Optional<Material> getMaterialById(Long id);
    Optional<Material> getMaterialByTipo(String tipo);
    Material updateMaterial(Long id, String tipo) throws Exception;
    void deleteMaterial(Long id) throws Exception;
}
