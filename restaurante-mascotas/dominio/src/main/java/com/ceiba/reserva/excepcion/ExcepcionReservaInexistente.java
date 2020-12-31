package com.ceiba.reserva.excepcion;

public class ExcepcionReservaInexistente extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionReservaInexistente(String message) {
        super(message);
    }
}
