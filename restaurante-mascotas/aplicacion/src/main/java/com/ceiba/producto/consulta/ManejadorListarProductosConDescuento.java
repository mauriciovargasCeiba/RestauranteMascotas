package com.ceiba.producto.consulta;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.servicio.ServicioListarProductosConDescuento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarProductosConDescuento {

    private final ServicioListarProductosConDescuento servicioListarProductosConDescuento;

    public ManejadorListarProductosConDescuento(ServicioListarProductosConDescuento servicioListarProductosConDescuento) {
        this.servicioListarProductosConDescuento = servicioListarProductosConDescuento;
    }

    public List<DtoProducto> ejecutar(String codigoReserva) {
        return servicioListarProductosConDescuento.ejecutar(codigoReserva);
    }
}
