package com.dssd.BackendApi.exception;

public class MaterialNoExiste extends RuntimeException {
    public MaterialNoExiste(String message) {
        super(message);
    }
}
