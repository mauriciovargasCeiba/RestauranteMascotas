package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.producto.modelo.dto.DtoProducto;

import static com.ceiba.producto.constante.TipoClienteProducto.HUMANO;
import static com.ceiba.producto.constante.TipoProducto.COMIDA;

public class DtoProductoTestDataBuilder {
    private Long id;
    private String nombre;
    private String tipo;
    private String tipoCliente;
    private Double precio;

    public DtoProductoTestDataBuilder() {
        id = 1L;
        nombre = "Papas Frítas";
        tipo = COMIDA.obtenerValor();
        tipoCliente = HUMANO.obtenerValor();
        precio = 1000.0;
    }

    public DtoProductoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoProductoTestDataBuilder conTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public DtoProductoTestDataBuilder conTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
        return this;
    }

    public DtoProductoTestDataBuilder conPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public DtoProducto build() {
        return new DtoProducto(id, nombre, tipo, tipoCliente, precio);
    }

}
