package com.ceiba.reserva.error;

import com.ceiba.infraestructura.error.Error;
import com.ceiba.reserva.excepcion.ExcepcionReservaConMesaYFechaYaExiste;
import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ManejadorErrorReserva extends ResponseEntityExceptionHandler {

    private static final ConcurrentHashMap<String, Integer> CODIGOS_ESTADO = new ConcurrentHashMap<>();

    public ManejadorErrorReserva() {
        CODIGOS_ESTADO.put(ExcepcionReservaInexistente.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        CODIGOS_ESTADO.put(ExcepcionReservaConMesaYFechaYaExiste.class.getSimpleName(), HttpStatus.CONFLICT.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = CODIGOS_ESTADO.get(excepcionNombre);

        Error error = new Error(excepcionNombre, mensaje);

        return new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
    }
}
