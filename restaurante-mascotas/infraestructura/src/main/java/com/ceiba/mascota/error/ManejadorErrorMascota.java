package com.ceiba.mascota.error;

import com.ceiba.infraestructura.error.ManejadorError;
import com.ceiba.mascota.excepcion.ExcepcionMascotaInexistente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ManejadorErrorMascota extends ManejadorError {
    public ManejadorErrorMascota() {
        CODIGOS_ESTADO.put(ExcepcionMascotaInexistente.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
    }
}
