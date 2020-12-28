package com.ceiba.producto.servicio;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class ServicioCalcularDescuentoProducto {

    private final DaoProducto daoProducto;

    public ServicioCalcularDescuentoProducto(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public List<DtoProducto> ejecutar(Reserva reserva) {
        List<DtoProducto> productos = daoProducto.listar();

        boolean reservaConMascota = !reserva.getIdMascota().equals("0000");
        if (reservaConMascota) {

            boolean reservaEntreDosPmYCuatroPm = reserva.getHora().getHour() >= 14 && reserva.getHora().getHour() <= 16;
            boolean reservaNoEsDomingo = reserva.getFecha().getDayOfWeek().getValue() != 7;
            if (reservaEntreDosPmYCuatroPm && reservaNoEsDomingo) {
                productos = aplicarDescuentoDeDosPmACuatroPmEnSemana(productos);
            }

            boolean reservaEsDiaPrimeroODiaQuince = reserva.getFecha().getDayOfMonth() == 1 || reserva.getFecha().getDayOfMonth() == 15;
            if (reservaEsDiaPrimeroODiaQuince) {
                productos = aplicarDescuentoDeDiaPrimeroOQuince(productos);
            }
        }

       return productos;
    }

    private List<DtoProducto> modificarProductosConDescuento(List<DtoProducto> productos, ToDoubleFunction<DtoProducto> descuento) {
        return productos.stream().map(producto -> {
            Double precioConDescuento = descuento.applyAsDouble(producto);
            return new DtoProducto(producto.getId(), producto.getNombre(), producto.getTipo(), producto.getTipoCliente(), precioConDescuento);
        }).collect(Collectors.toList());
    }

    private List<DtoProducto> aplicarDescuentoDeDosPmACuatroPmEnSemana(List<DtoProducto> productos) {
        return modificarProductosConDescuento(productos, producto -> {
            boolean productoEsComidaParaHumano = producto.getTipo().equals("COMIDA") && producto.getTipoCliente().equals("HUMANO");
            return productoEsComidaParaHumano ? producto.getPrecio() * 0.9 : producto.getPrecio();
        });
    }

    private List<DtoProducto> aplicarDescuentoDeDiaPrimeroOQuince(List<DtoProducto> productos) {
       return modificarProductosConDescuento(productos, producto -> producto.getTipo().equals("OTROS")  && producto.getTipoCliente().equals("ANIMAL") ? 0.0 : producto.getPrecio());
    }

}
