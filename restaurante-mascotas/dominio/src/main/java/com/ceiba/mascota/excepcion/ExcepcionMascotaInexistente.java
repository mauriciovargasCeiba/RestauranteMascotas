package com.ceiba.mascota.excepcion;

public class ExcepcionMascotaInexistente extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionMascotaInexistente(String message) {
        super(message);
    }
}
