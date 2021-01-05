package com.ceiba.descuento.servicio;

import com.ceiba.descuento.modelo.objetovalor.Descuento;
import com.ceiba.producto.constante.TipoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.reserva.puerto.dao.DaoReserva;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ceiba.reserva.constante.NumeroReferenciaDescuento.*;
import static com.ceiba.dominio.ValidadorArgumento.validarRegex;
import static com.ceiba.producto.constante.TipoClienteProducto.HUMANO;
import static com.ceiba.producto.constante.TipoClienteProducto.MASCOTA;
import static com.ceiba.producto.constante.TipoProducto.COMIDA;
import static com.ceiba.producto.constante.TipoProducto.JUGUETE;
import static com.ceiba.reserva.validador.ValidadorExistenciaReserva.validarExistenciaReservaConCodigo;
import static java.util.Collections.singletonList;

public class ServicioCalcularDescuento {

    private static final String FORMATO_VALIDO_CODIGO_RESERVA = "^\\d{14}_\\d{1,15}$";
    private static final String EL_CODIGO_INGRESADO_TIENE_UN_FORMATO_INVALIDO = "El código ingresado tiene un formato inválido";

    private static final double CUARENTA_POR_CIENTO = 0.6;
    private static final double DIEZ_POR_CIENTO = 0.9;
    private static final double GRATIS = 0.0;

    private static final List<TipoProducto> CUALQUIER_PRODUCTO = Arrays.asList(COMIDA, JUGUETE);

    private static final List<Descuento> descuentos = inicializarValoresDescuento();

    private final DaoReserva daoReserva;

    public ServicioCalcularDescuento(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    private static List<Descuento> inicializarValoresDescuento() {
        List<Descuento> descuentos = new ArrayList<>();
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA,
                CUARENTA_POR_CIENTO,
                CUALQUIER_PRODUCTO,
                singletonList(MASCOTA)
        ));
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE,
                DIEZ_POR_CIENTO,
                singletonList(COMIDA),
                singletonList(HUMANO)
        ));
        descuentos.add(new Descuento(
                NUM_REF_DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA,
                GRATIS,
                singletonList(JUGUETE),
                singletonList(MASCOTA))
        );
        return descuentos;
    }

    public Double ejecutar(String codigoGeneradoReserva, DtoProducto producto) {
        validarRegex(codigoGeneradoReserva, FORMATO_VALIDO_CODIGO_RESERVA, EL_CODIGO_INGRESADO_TIENE_UN_FORMATO_INVALIDO);
        validarExistenciaReservaConCodigo(codigoGeneradoReserva, daoReserva);

        String fraccionCodigoReserva = extraerFraccionCodigoDescuentos(codigoGeneradoReserva);
        double porcentajeDescuento = calcularPorcentajeDescuentoParaCodigoYTipoCliente(fraccionCodigoReserva, producto.getTipoCliente());
        return producto.getPrecio() * porcentajeDescuento;
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
