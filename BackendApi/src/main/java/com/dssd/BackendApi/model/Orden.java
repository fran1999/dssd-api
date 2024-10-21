package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ordenes")
public class Orden {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orden_id")
    private Long id;

    @Setter
    @Getter
    @Column(name="fecha_inicio", nullable = true, unique = false)
    private LocalDateTime fechaInicio;

    @Getter
    @Column(name="fecha_limite", nullable = false, unique = false)
    private LocalDateTime fechaLimite;

    @Getter
    @Column(name="fecha_entrega", nullable = true, unique = false)
    private LocalDateTime fechaEntrega;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="centro_recoleccion_id")
    @JsonManagedReference
    private CentroRecoleccion centroRecoleccion;

    @Setter
    @Getter
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MaterialOrden> materialesOrden = new ArrayList<>();

    public Orden(){}

    public Orden(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

}
