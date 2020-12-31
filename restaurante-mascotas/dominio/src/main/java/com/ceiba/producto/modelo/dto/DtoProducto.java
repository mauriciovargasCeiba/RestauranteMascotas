package com.ceiba.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class DtoProducto {
    private Long id;
    private String nombre;
    private String tipo;
    private String tipoCliente;
    private Double precio;
}
