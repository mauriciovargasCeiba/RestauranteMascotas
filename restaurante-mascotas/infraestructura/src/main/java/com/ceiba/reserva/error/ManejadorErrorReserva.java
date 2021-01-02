package com.ceiba.reserva.error;

import com.ceiba.infraestructura.error.ManejadorError;
import com.ceiba.reserva.excepcion.ExcepcionReservaConMesaYFechaYaExiste;
import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ManejadorErrorReserva extends ManejadorError {

    public ManejadorErrorReserva() {
        CODIGOS_ESTADO.put(ExcepcionReservaInexistente.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        CODIGOS_ESTADO.put(ExcepcionReservaConMesaYFechaYaExiste.class.getSimpleName(), HttpStatus.CONFLICT.value());
    }
}
