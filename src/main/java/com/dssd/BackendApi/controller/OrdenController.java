package com.dssd.BackendApi.controller;


import com.dssd.BackendApi.dtos.OrdenBusquedaRequest;
import com.dssd.BackendApi.dtos.OrdenRequest;
import com.dssd.BackendApi.dtos.OrdenResponse;
import com.dssd.BackendApi.exception.*;
import com.dssd.BackendApi.model.Orden;
import com.dssd.BackendApi.service.OrdenService;
import com.dssd.BackendApi.util.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class OrdenController {

    private final OrdenService ordenService;
    private final Validacion validacion;

    @Autowired
    public OrdenController(OrdenService ordenService, Validacion validacion) {
        this.ordenService = ordenService;
        this.validacion = validacion;
    }

    @PostMapping("/orden")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Orden> crearOrden(@RequestBody OrdenRequest ordenRequest) {
        try {
            Orden orden = this.ordenService.createOrden(ordenRequest.getFechaLimite(), ordenRequest.getMateriales());
            if (orden == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(orden);
        } catch (MaterialNoExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ordenes")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Iterable<Orden>> getOrdenes() {
        System.out.println("ENTRO");
        Iterable<Orden> ordenes = this.ordenService.getAllOrdenes();

        return ResponseEntity.status(HttpStatus.OK).body(ordenes);
    }

    @PostMapping("/ordenes/busqueda")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Iterable<?>> getOrdenesByMaterialBetweenDates(@RequestBody OrdenBusquedaRequest ordenBusquedaRequest) {
        LocalDateTime fechaComienzo = ordenBusquedaRequest.getFechaComienzo();
        LocalDateTime fechaFin = ordenBusquedaRequest.getFechaFin();

        System.out.println("Obtener ordenes del " + fechaComienzo + " hasta el " + fechaFin);

        if (validacion.validarFechas(fechaComienzo, fechaFin)) {
            try {
                List<OrdenResponse> ordenes = (List<OrdenResponse>) this.ordenService.getOrdenesByMaterialBetweenDates(ordenBusquedaRequest.getTipoMaterial(), fechaComienzo, fechaFin);
                if (!ordenes.isEmpty()) {
                    System.out.println("Se encontraron " + ordenes.size() + " ordenes");
                    return ResponseEntity.status(HttpStatus.OK).body(ordenes);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("No se encontraron ordenes para esas fechas ni para ese material"));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("No se encontraron ordenes para esas fechas ni para ese material"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of("Las fechas ingresadas son invalidas"));
        }
    }

    @PutMapping("orden/{id}/asignar/{idCentro}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> marcarOrdenComoTomada(@PathVariable Long id, @PathVariable Long idCentro) {
        try {
            System.out.println("Orden id: " + id + " Centro id:" + idCentro);
            Orden orden = this.ordenService.tomarOrden(id, idCentro);
            return ResponseEntity.status(HttpStatus.OK).body(orden);
        } catch (OrdenNoEncontrada | CentroRecoleccionNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (NoTrabajaConMaterial e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMateriales());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("orden/{id}/entregada/{idCentro}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Orden> terminarOrden(@PathVariable Long id, @PathVariable Long idCentro) {
        try {
            System.out.println("Orden id: " + id + " Centro id:" + idCentro);
            Orden orden = this.ordenService.terminarOrden(id, idCentro);
            return ResponseEntity.status(HttpStatus.OK).body(orden);
        } catch (OrdenNoEncontrada | CentroRecoleccionNoEncontrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (FechaEntregaIncorrecta e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ordenes/{idCentro}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Iterable<OrdenResponse>> getOrdenesCentro(@PathVariable Long idCentro) {
        Iterable<OrdenResponse> ordenes;
        try{
            ordenes = this.ordenService.getOrdenesByCentroId(idCentro);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ordenes);
    }

}
