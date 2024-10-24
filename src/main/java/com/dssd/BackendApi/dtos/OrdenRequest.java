package com.dssd.BackendApi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Map;

public class OrdenRequest {

    @JsonProperty("fecha_limite")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaLimite;
    private Map<Long, Float> materiales;

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public Map<Long, Float> getMateriales() {
        return materiales;
    }
}
