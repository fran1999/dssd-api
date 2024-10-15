package com.dssd.BackendApi.service;

import com.dssd.BackendApi.model.Orden;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrdenServiceImpl implements OrdenService{
    @Override
    public Orden createOrden(LocalDate fecha_limite) {
        return null;
    }

    @Override
    public Iterable<Orden> getAllOrdenes() {
        return null;
    }

    @Override
    public Orden getOrdenById(Long id) throws Exception {
        return null;
    }

    @Override
    public void deleteOrden(Long id) throws Exception {

    }
}
