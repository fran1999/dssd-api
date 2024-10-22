package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Orden;

import java.time.LocalDate;

public interface OrdenService {
    Orden createOrden(LocalDate fecha_limite);
    Iterable<Orden> getAllOrdenes();
    Orden getOrdenById(Long id) throws Exception;
    void deleteOrden(Long id) throws Exception;
}
