package com.dssd.BackendApi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="centros_de_recoleccion")
public class CentroRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="centro_recoleccion_id")
    private Long id;

    @Setter
    @Getter
    @Column(name="nombre", nullable = false)
    private String nombre;

    public CentroRecoleccion() {}

    public Long getId() {
        return id;
    }


}
