package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.Descuento;
import com.ceiba.producto.constante.TipoClienteProducto;
import com.ceiba.producto.constante.TipoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ceiba.descuento.constante.NumeroReferenciaDescuento.*;
import static java.util.Collections.singletonList;

public class ServicioCalcularDescuento {

    private static final double CUARENTA_POR_CIENTO = 0.6;
    private static final double DIEZ_POR_CIENTO = 0.9;
    private static final double GRATIS = 0.0;
    private static final List<Descuento> descuentos = inicializarValoresDescuento();

    public Double ejecutar(String codigoGeneradoReserva, DtoProducto producto) {
        String fraccionCodigoReserva = extraerFraccionCodigoDescuentos(codigoGeneradoReserva);
        double porcentajeDescuento = calcularPorcentajeDescuentoParaCodigoYTipoCliente(fraccionCodigoReserva, producto.getTipoCliente());
        return producto.getPrecio() * porcentajeDescuento;
    }

    private static List<Descuento> inicializarValoresDescuento() {
        List<Descuento> descuentos = new ArrayList<>();
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA,
                CUARENTA_POR_CIENTO,
                Arrays.asList(TipoProducto.COMIDA, TipoProducto.JUGUETE),
                singletonList(TipoClienteProducto.MASCOTA)
        ));
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE,
                DIEZ_POR_CIENTO,
                singletonList(TipoProducto.COMIDA),
                singletonList(TipoClienteProducto.HUMANO)
        ));
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA,
                GRATIS,
                singletonList(TipoProducto.JUGUETE),
                singletonList(TipoClienteProducto.MASCOTA))
        );
        return descuentos;
    }

    private String extraerFraccionCodigoDescuentos(String codigoGeneradoReserva) {
        return codigoGeneradoReserva.substring(0, 3);
    }

    private double calcularPorcentajeDescuentoParaCodigoYTipoCliente(String fraccionCodigoReserva, String tipoClienteReal) {
        double porcentaje = 1.0;
        for (Descuento descuento : descuentos) {
            if (descuento.aplicaParaCodigo(fraccionCodigoReserva) && descuento.aplicaParaTipoCliente(tipoClienteReal)) {
                porcentaje *= descuento.obtenerPorcentaje();
            }
        }
        return porcentaje;
    }

}
