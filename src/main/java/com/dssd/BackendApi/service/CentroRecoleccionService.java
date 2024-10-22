package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.CentroRecoleccion;

import java.util.Optional;

public interface CentroRecoleccionService {
    CentroRecoleccion createCentroRecoleccion(String nombre);
    Iterable<CentroRecoleccion> getAllCentrosRecoleccion();
    CentroRecoleccion getCentroRecoleccionById(Long id) throws Exception;
    CentroRecoleccion updateCentroRecoleccion(Long id, String nombre) throws Exception;
    void deleteCentroRecoleccion(Long id) throws Exception;
}
