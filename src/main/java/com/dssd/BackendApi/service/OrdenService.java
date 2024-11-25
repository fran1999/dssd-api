package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Material;
import com.dssd.BackendApi.model.Orden;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrdenService {
    Orden createOrden(LocalDateTime fechaLimite,  Map<Long, Float> materiales);
    Iterable<Orden> getAllOrdenes();
    Orden getOrdenById(Long id) throws RuntimeException;
    Iterable<Orden> getOrdenesByMaterialBetweenDates(String tipoMaterial, LocalDateTime fechaComienzo, LocalDateTime finFecha) throws Exception;
    Iterable<Orden> getOrdenesByMaterialBetweenDates(Long idMaterial, LocalDateTime fechaComienzo, LocalDateTime finFecha);
    void deleteOrden(Long id) throws Exception;
    Orden tomarOrden(Long id, Long idCentro) throws RuntimeException;
    Orden terminarOrden(Long id, Long idCentro) throws RuntimeException;

}
