package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="materiales")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="material_id")
    private Long id;

    @Column(name="tipo", nullable=false, unique=true)
    private String tipo;

    @ManyToMany(mappedBy = "materiales", fetch = FetchType.LAZY, cascade = {})
    @JsonBackReference
    private List<CentroRecoleccion> centrosRecoleccion = new ArrayList<CentroRecoleccion>();

    public Material(){}

    public Material(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public List<CentroRecoleccion> getCentrosRecoleccion() {
        return centrosRecoleccion;
    }

    public void agregarCentroRecoleccion(CentroRecoleccion centro) {
        this.centrosRecoleccion.add(centro);
        centro.agregarMaterial(this);
    }

}
