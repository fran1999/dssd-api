package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.CentroRecoleccion;

public interface CentroRecoleccionService {
    CentroRecoleccion createCentroRecoleccion(String nombre);
    Iterable<CentroRecoleccion> getAllCentrosRecoleccion();
    CentroRecoleccion getCentroRecoleccionById(Long id) throws RuntimeException;
    CentroRecoleccion updateCentroRecoleccion(Long id, String nombre) throws Exception;
    void deleteCentroRecoleccion(Long id) throws Exception;
    CentroRecoleccion asignarMaterialAlCentroRecoleccion(Long materialId, Long centroId) throws Exception;
}
