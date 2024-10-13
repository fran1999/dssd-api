package com.dssd.BackendApi.controller;

import com.dssd.BackendApi.dtos.CreateCentroRecoleccionRequest;
import com.dssd.BackendApi.model.CentroRecoleccion;
import com.dssd.BackendApi.service.CentroRecoleccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path="/api")
public class CentroRecoleccionController {

    @Autowired
    private CentroRecoleccionService centroRecoleccionService;

    @PostMapping("/centrosRecoleccion")
    public ResponseEntity<CentroRecoleccion> createCentroRecoleccion(@RequestBody CreateCentroRecoleccionRequest centroRecoleccionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.centroRecoleccionService.createCentroRecoleccion(centroRecoleccionRequest.getNombre()));
    }

    @GetMapping("/centrosRecoleccion")
    public ResponseEntity<Iterable<CentroRecoleccion>> getAllCentrosRecoleccion(){
        return ResponseEntity.status(HttpStatus.OK).body(this.centroRecoleccionService.getAllCentrosRecoleccion());
    }

    @GetMapping("/centrosRecoleccion/{id}")
    public ResponseEntity<CentroRecoleccion> getDepositoById(@PathVariable("id") Long id){
        Optional<CentroRecoleccion> centroRecoleccion = this.centroRecoleccionService.getCentroRecoleccionById(id);
        if(centroRecoleccion.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(centroRecoleccion.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/centrosRecoleccion/{id}")
    public ResponseEntity<CentroRecoleccion> updateDeposito(@PathVariable("id") Long id, @RequestBody CreateCentroRecoleccionRequest centroRecoleccionRequest){
        CentroRecoleccion updatedCentro = this.centroRecoleccionService.updateCentroRecoleccion(id, centroRecoleccionRequest.getNombre());
        if(updatedCentro != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedCentro);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/centrosRecoleccion/{id}")
    public ResponseEntity<HttpStatus> deleteDeposito(@PathVariable("id") Long id){
        try{
            this.centroRecoleccionService.deleteCentroRecoleccion(id);
            return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
