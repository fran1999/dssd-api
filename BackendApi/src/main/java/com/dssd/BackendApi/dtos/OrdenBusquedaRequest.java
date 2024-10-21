package com.dssd.BackendApi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.time.LocalDateTime;

public class OrdenBusquedaRequest {

    @Nullable
    @JsonProperty("id_material")
    private Long idMaterial;
    @Nullable
    @JsonProperty("tipo_material")
    private String tipoMaterial;
    @JsonProperty("fecha_comienzo")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaComienzo;
    @JsonProperty("fecha_fin")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaFin;

    public Long getIdMaterial() {
        return idMaterial;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public LocalDateTime getFechaComienzo() {
        return fechaComienzo;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
}
