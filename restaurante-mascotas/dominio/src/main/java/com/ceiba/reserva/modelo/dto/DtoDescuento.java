package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoDescuento {
    private Long id;
    private String descripcion;
    private Double valor;
    private Boolean vigente;
}
