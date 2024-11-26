package com.dssd.BackendApi.dtos;

import com.dssd.BackendApi.model.CentroRecoleccion;

import java.time.LocalDateTime;
import java.util.Map;

public class OrdenResponse {

    private Long ordenId;
    private LocalDateTime fechaLimite;
    private Map<String, Float> materialCantidad;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEntrega;
    private CentroRecoleccion centroRecoleccion;

    public OrdenResponse(Long ordenId, LocalDateTime fechaLimite, LocalDateTime fechaInicio, LocalDateTime fechaEntrega, CentroRecoleccion centroRecoleccion) {
        this.ordenId = ordenId;
        this.fechaLimite = fechaLimite;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.centroRecoleccion = centroRecoleccion;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Map<String, Float> getMaterialCantidad() {
        return materialCantidad;
    }

    public void setMaterialCantidad(Map<String, Float> materialCantidad) {
        this.materialCantidad = materialCantidad;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public CentroRecoleccion getCentroRecoleccion() {
        return centroRecoleccion;
    }

    public void setCentroRecoleccion(CentroRecoleccion centroRecoleccion) {
        this.centroRecoleccion = centroRecoleccion;
    }
}
