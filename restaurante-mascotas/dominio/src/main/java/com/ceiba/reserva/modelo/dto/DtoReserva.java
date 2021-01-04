package com.ceiba.reserva.modelo.dto;

import com.ceiba.mascota.modelo.dto.DtoMascota;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private DtoMascota mascota;
    private String codigoGenerado;
}
