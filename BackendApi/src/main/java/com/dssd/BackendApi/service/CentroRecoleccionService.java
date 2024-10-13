package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.CentroRecoleccion;

import java.util.Optional;

public interface CentroRecoleccionService {
    CentroRecoleccion createCentroRecoleccion(String nombre);
    Iterable<CentroRecoleccion> getAllCentrosRecoleccion();
    Optional<CentroRecoleccion> getCentroRecoleccionById(Long id);
    CentroRecoleccion updateCentroRecoleccion(Long id, String nombre);
    void deleteCentroRecoleccion(Long id);
}
