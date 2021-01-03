package com.ceiba.producto.servicio;

import com.ceiba.descuento.servicio.ServicioCalcularDescuento;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;

import java.util.List;
import java.util.stream.Collectors;

public class ServicioListarProductosConDescuento {
    private final DaoProducto daoProducto;
    private final ServicioCalcularDescuento servicioCalcularDescuento;

    public ServicioListarProductosConDescuento(DaoProducto daoProducto, ServicioCalcularDescuento servicioCalcularDescuento) {
        this.daoProducto = daoProducto;
        this.servicioCalcularDescuento = servicioCalcularDescuento;
    }

    public List<DtoProducto> ejecutar(String codigoReserva) {
        List<DtoProducto> productos = daoProducto.listar();
        return productos.stream().map(dtoProducto -> {
            Double precioConDescuento = servicioCalcularDescuento.ejecutar(codigoReserva, dtoProducto);
            return new DtoProducto(dtoProducto.getId(), dtoProducto.getNombre(), dtoProducto.getTipo(), dtoProducto.getTipoCliente(), precioConDescuento);
        }).collect(Collectors.toList());
    }
}
