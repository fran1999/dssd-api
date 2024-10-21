package com.dssd.BackendApi.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Validacion {

    public boolean validarFechas(LocalDateTime fechaComienzo, LocalDateTime fechaInicio) {
        if (fechaComienzo.isAfter(fechaInicio) || fechaComienzo.isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }
}
