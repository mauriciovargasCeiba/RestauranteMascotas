package com.ceiba.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoProducto {
    private Long id;
    private String nombre;
    private String tipo;
    private String tipoCliente;
    private Double precio;
}
