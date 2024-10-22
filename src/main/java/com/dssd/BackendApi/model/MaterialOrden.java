package com.dssd.BackendApi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="orden_compuesta_por")
public class MaterialOrden {

    @Embeddable
    public static class MaterialOrdenId implements Serializable {
        protected Long materialId;
        protected Long ordenId;
        public MaterialOrdenId() {
        }
        public MaterialOrdenId(Long materialId, Long ordenId) {
            this.materialId = materialId;
            this.ordenId = ordenId;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((materialId == null) ? 0 : materialId.hashCode());
            result = prime * result
                    + ((ordenId == null) ? 0 : ordenId.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            MaterialOrdenId other = (MaterialOrdenId) obj;
            if (materialId == null) {
                if (other.materialId != null)
                    return false;
            } else if (!materialId.equals(other.materialId))
                return false;
            if (ordenId == null) {
                if (other.ordenId != null)
                    return false;
            } else if (!ordenId.equals(other.ordenId))
                return false;
            return true;
        }
    }

    @EmbeddedId
    private MaterialOrdenId id = new MaterialOrdenId();

    @ManyToOne
    @JoinColumn(name = "materialId")
    @MapsId("materialId")
    @JsonBackReference
    private Material material;

    @ManyToOne
    @JoinColumn(name = "ordenId")
    @MapsId("ordenId")
    @JsonBackReference
    private Orden orden;

    private Float cantidad;

    public MaterialOrdenId getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden recoleccion) {
        this.orden = recoleccion;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }
}