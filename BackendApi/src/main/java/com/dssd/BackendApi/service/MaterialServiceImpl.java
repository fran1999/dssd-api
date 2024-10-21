package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Material;
import com.dssd.BackendApi.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material createMaterial(String tipo) {
        Material material = new Material(tipo);
        try {
            return this.materialRepository.save(material);
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }

    @Override
    public Iterable<Material> getAllMateriales() {
        return this.materialRepository.findAll();
    }

    @Override
    public Optional<Material> getMaterialById(Long id) {
        try {
            return this.materialRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Material> getMaterialByTipo(String tipo) {
        return this.materialRepository.findByTipo(tipo);
    }

    @Override
    public Material updateMaterial(Long id, String tipo) throws Exception {
        return null;
    }

    @Override
    public void deleteMaterial(Long id) throws Exception {

    }
}
