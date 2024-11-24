package com.dssd.BackendApi.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Validacion {

    public boolean validarFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
        return fechaFin.isAfter(fechaComienzo) && !fechaComienzo.isBefore(LocalDateTime.now());
    }
}
