package com.ceiba.reserva.excepcion;

public class ExcepcionFechaYHoraInvalida extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionFechaYHoraInvalida(String message) {
        super(message);
    }
}
