package com.ceiba.reserva.excepcion;

public class ExcepcionReservaConMesaYFechaYaExiste extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionReservaConMesaYFechaYaExiste(String message) {
        super(message);
    }
}
