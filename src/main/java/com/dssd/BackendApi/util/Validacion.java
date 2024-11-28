package com.dssd.BackendApi.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Validacion {

    public boolean validarFechas(LocalDateTime fechaComienzo, LocalDateTime fechaFin) {
        // Zona horaria de Argentina
        ZoneId argentinaZone = ZoneId.of("America/Argentina/Buenos_Aires");

        // Obtener la fecha y hora actuales en Argentina
        ZonedDateTime nowInArgentina = ZonedDateTime.now(argentinaZone);
        System.out.println("Fecha y hora actuales en Argentina: " + nowInArgentina);

        // Convertir la fecha ingresada a la zona horaria de Argentina
        ZonedDateTime fechaComienzoInArgentina = fechaComienzo.atZone(argentinaZone);

        // Validar si la fecha ingresada es antes, después o igual a la actual
        if (fechaComienzoInArgentina.isAfter(nowInArgentina)) {
            System.out.println("La fecha ingresada es posterior a la fecha actual en Argentina.");
        } else if (fechaComienzoInArgentina.isBefore(nowInArgentina)) {
            System.out.println("La fecha ingresada es anterior a la fecha actual en Argentina.");
        } else {
            System.out.println("La fecha ingresada es igual a la fecha actual en Argentina.");
        }

        return fechaFin.isAfter(fechaComienzo) && fechaComienzoInArgentina.isAfter(nowInArgentina);
    }
}
