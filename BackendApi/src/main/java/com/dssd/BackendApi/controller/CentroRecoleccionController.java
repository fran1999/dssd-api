package com.dssd.BackendApi.controller;

import com.dssd.BackendApi.dtos.CreateCentroRecoleccionRequest;
import com.dssd.BackendApi.dtos.MaterialRequest;
import com.dssd.BackendApi.model.CentroRecoleccion;
import com.dssd.BackendApi.service.CentroRecoleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class CentroRecoleccionController {

    @Autowired
    private CentroRecoleccionService centroRecoleccionService;

    @PostMapping("/centrosRecoleccion")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CentroRecoleccion> createCentroRecoleccion(@RequestBody CreateCentroRecoleccionRequest centroRecoleccionRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.centroRecoleccionService.createCentroRecoleccion(centroRecoleccionRequest.getNombre()));
    }

    @GetMapping("/centrosRecoleccion")
    public ResponseEntity<Iterable<CentroRecoleccion>> getAllCentrosRecoleccion() {
        return ResponseEntity.status(HttpStatus.OK).body(this.centroRecoleccionService.getAllCentrosRecoleccion());
    }

    @PutMapping("/centrosRecoleccion/{id}/material")
    public ResponseEntity<CentroRecoleccion> agregarMaterialAlCentroRecoleccion(@PathVariable Long id, @RequestBody MaterialRequest materialRequest) {
        try {
            if (materialRequest.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.agregarMaterialAlCentroRecoleccion(materialRequest.getId(), id);
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    ;

    @GetMapping("/centrosRecoleccion/{id}")
    public ResponseEntity<CentroRecoleccion> getDepositoById(@PathVariable("id") Long id) {
        try {
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.getCentroRecoleccionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/centrosRecoleccion/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity updateDeposito(@PathVariable("id") Long id, @RequestBody CreateCentroRecoleccionRequest centroRecoleccionRequest) {
        try {
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.updateCentroRecoleccion(id, centroRecoleccionRequest.getNombre());
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/centrosRecoleccion/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteDeposito(@PathVariable("id") Long id) {
        try {
            this.centroRecoleccionService.deleteCentroRecoleccion(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
