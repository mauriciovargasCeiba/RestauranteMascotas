package com.ceiba.mascota.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoMascota {
    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;
}
