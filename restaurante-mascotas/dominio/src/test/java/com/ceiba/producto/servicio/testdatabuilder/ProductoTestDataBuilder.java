package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.producto.modelo.dto.DtoProducto;

public class ProductoTestDataBuilder {

    private Long id;
    private String nombre;
    private String tipo;
    private String tipoCliente;
    private Double precio;

    public ProductoTestDataBuilder() {
        this.id = 1L;
        this.nombre = "Producto test";
        this.tipo = "OTROS";
        this.tipoCliente = "HUMANO";
        this.precio = 1000.0;
    }

    public ProductoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoTestDataBuilder conTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public ProductoTestDataBuilder conTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
        return this;
    }

    public ProductoTestDataBuilder conPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public DtoProducto build() {
        return new DtoProducto(id, nombre, tipo, tipoCliente, precio);
    }
    
}
