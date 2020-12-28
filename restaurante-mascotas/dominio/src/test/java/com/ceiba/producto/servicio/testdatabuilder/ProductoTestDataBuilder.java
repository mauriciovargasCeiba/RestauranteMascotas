package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.producto.modelo.dto.DtoProducto;

import java.util.Collections;
import java.util.List;

public class ProductoTestDataBuilder {

    private List<DtoProducto> productos;

    public ProductoTestDataBuilder() {
        DtoProducto dtoProducto = new DtoProducto(1L, "Producto test", "OTROS", "HUMANO", 1000.0);
        productos = Collections.singletonList(dtoProducto);
    }

    public ProductoTestDataBuilder conProductos(List<DtoProducto> productos) {
        this.productos = productos;
        return this;
    }

    public List<DtoProducto> build() {
        return productos;
    }

}
