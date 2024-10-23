package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orden_id")
    private Long id;

    @Column(name="fecha_inicio", nullable = true, unique = false)
    private LocalDateTime fechaInicio;

    @Column(name="fecha_limite", nullable = false, unique = false)
    private LocalDateTime fechaLimite;

    @Column(name="fecha_entrega", nullable = true, unique = false)
    private LocalDateTime fechaEntrega;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="centro_recoleccion_id")
    @JsonManagedReference
    private CentroRecoleccion centroRecoleccion;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MaterialOrden> materialesOrden = new ArrayList<>();

    public Orden(){}

    public Orden(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public Long getId() {
        return this.id;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
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

    public List<MaterialOrden> getMaterialesOrden() {
        return materialesOrden;
    }

    public void setMaterialesOrden(List<MaterialOrden> materialesOrden) {
        this.materialesOrden = materialesOrden;
    }
}
