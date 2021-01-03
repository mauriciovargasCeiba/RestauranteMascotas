package com.ceiba.descuento.modelo;

import com.ceiba.descuento.constante.NumeroReferenciaDescuento;
import com.ceiba.producto.constante.TipoClienteProducto;
import com.ceiba.producto.constante.TipoProducto;
import lombok.Getter;

import java.util.List;

@Getter
public class Descuento {
    private final NumeroReferenciaDescuento numeroReferenciaDescuento;
    private final Double porcentajeDescuento;
    private final List<TipoProducto> tiposProducto;
    private final List<TipoClienteProducto> tiposClienteProducto;

    public Descuento(NumeroReferenciaDescuento numeroReferenciaDescuento, Double porcentajeDescuento, List<TipoProducto> tiposProducto, List<TipoClienteProducto> tiposClienteProducto) {
        this.numeroReferenciaDescuento = numeroReferenciaDescuento;
        this.porcentajeDescuento = porcentajeDescuento;
        this.tiposProducto = tiposProducto;
        this.tiposClienteProducto = tiposClienteProducto;
    }

    public Double obtenerPorcentaje() {
        return porcentajeDescuento;
    }

    public boolean aplicaParaCodigo(String codigoReserva) {
        return codigoReserva.contains(obtenerCodigo());
    }

    private String obtenerCodigo() {
        return String.valueOf(numeroReferenciaDescuento.obtenerValorNumerico());
    }

    public boolean aplicaParaTipoCliente(String tipoClienteReal) {
        return tiposClienteProducto.stream().anyMatch(tipoCliente -> tipoCliente.obtenerValor().equals(tipoClienteReal));
    }
}
