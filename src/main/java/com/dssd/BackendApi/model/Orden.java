package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
    private LocalTime fecha_inicio;

    @Getter
    @Column(name="fecha_limite", nullable = false, unique = false)
    private LocalTime fecha_limite;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="centro_recoleccion_id")
    @JsonManagedReference
    private CentroRecoleccion centroRecoleccion;

    public Orden(){}

    public Orden(LocalTime fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

}
