package com.dssd.BackendApi.controller;

import com.dssd.BackendApi.dtos.CreateCentroRecoleccionRequest;
import com.dssd.BackendApi.dtos.MaterialRequest;
import com.dssd.BackendApi.exception.CentroRecoleccionNoEncontrado;
import com.dssd.BackendApi.exception.MaterialNoExiste;
import com.dssd.BackendApi.model.CentroRecoleccion;
import com.dssd.BackendApi.service.CentroRecoleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Iterable<CentroRecoleccion>> getAllCentrosRecoleccion() {
        return ResponseEntity.status(HttpStatus.OK).body(this.centroRecoleccionService.getAllCentrosRecoleccion());
    }

    @PutMapping("/centrosRecoleccion/{id}/material")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CentroRecoleccion> agregarMaterialAlCentroRecoleccion(@PathVariable Long id, @RequestBody List<MaterialRequest> materialRequest) {
        try {
            if (materialRequest.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            List<Long> idMateriales = materialRequest.stream().map(MaterialRequest::getId).toList();
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.asignarMaterialAlCentroRecoleccion(idMateriales, id);

            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (MaterialNoExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/centrosRecoleccion/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CentroRecoleccion> getDepositoById(@PathVariable("id") Long id) {
        try {
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.getCentroRecoleccionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (CentroRecoleccionNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/centrosRecoleccion/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateDeposito(@PathVariable("id") Long id, @RequestBody CreateCentroRecoleccionRequest centroRecoleccionRequest) {
        try {
            CentroRecoleccion centroRecoleccion = this.centroRecoleccionService.updateCentroRecoleccion(id, centroRecoleccionRequest.getNombre());
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion);
        } catch (MaterialNoExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/centrosRecoleccion/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteDeposito(@PathVariable("id") Long id) {
        try {
            this.centroRecoleccionService.deleteCentroRecoleccion(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        } catch (CentroRecoleccionNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
