package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "centro_trabaja_con",
            joinColumns = { @JoinColumn(name="centro_recoleccion_id") },
            inverseJoinColumns = { @JoinColumn(name = "material_id") }
    )
    @JsonManagedReference
    private List<Material> materiales = new ArrayList<Material>();

    @Getter
    @OneToMany(mappedBy = "centroRecoleccion", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonBackReference
    private List<Orden> ordenes;

    public CentroRecoleccion() {}

    public Long getId() {
        return id;
    }

    public void agregarMaterial(Material material) {
        this.materiales.add(material);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Material> getMateriales() {
        return this.materiales;
    }
}
