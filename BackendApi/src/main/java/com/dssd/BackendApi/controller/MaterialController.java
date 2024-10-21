package com.dssd.BackendApi.controller;


import com.dssd.BackendApi.dtos.MaterialRequest;
import com.dssd.BackendApi.model.Material;
import com.dssd.BackendApi.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/material")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Material> agregarMaterial(@RequestBody MaterialRequest material) {
        Material materialCreado = this.materialService.createMaterial(material.getTipo());
        if (materialCreado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(materialCreado);
    }

    @GetMapping("/material")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Iterable<Material>> getMateriales() {
        Iterable<Material> materiales = this.materialService.getAllMateriales();

        return ResponseEntity.status(HttpStatus.OK).body(materiales);
    }

    @GetMapping("/material{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Material> getMaterial(@PathVariable Long id) {
        Optional<Material> material = this.materialService.getMaterialById(id);

        return material.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
