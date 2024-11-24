package com.dssd.BackendApi.exception;

import com.dssd.BackendApi.model.MaterialOrden;

import java.util.List;

public class NoTrabajaConMaterial extends RuntimeException {

    List<MaterialOrden> materiales;

    public NoTrabajaConMaterial(String message, List<MaterialOrden> materiales) {
        super(message);
        this.materiales = materiales;
    }

    public List<MaterialOrden> getMateriales() {
        return this.materiales;
    }
}
