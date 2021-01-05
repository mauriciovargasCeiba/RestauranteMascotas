package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.dto.DtoDescuento;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.servicio.testdatabuilder.DtoMascotaTestDataBuilder;
import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class DtoReservaTestDataBuilder {
    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private DtoMascota dtoMascota;
    private String codigoGenerado;
    private List<DtoDescuento> descuentos;

    public DtoReservaTestDataBuilder() {
        id = 1L;
        numeroMesa = 1;
        fechaYHora = LocalDateTime.now().plusDays(1L).withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        dtoMascota = new DtoMascotaTestDataBuilder().conId(1234L).build();
        codigoGenerado = "123_1234";
        descuentos = singletonList(new DtoDescuento(0L, "Sin Descuento", 100.0,true));
    }

    public DtoReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoReservaTestDataBuilder conNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
        return this;
    }

    public DtoReservaTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public DtoReservaTestDataBuilder conNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public DtoReservaTestDataBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public DtoReservaTestDataBuilder conMascota(DtoMascota dtoMascota) {
        this.dtoMascota = dtoMascota;
        return this;
    }

    public DtoReservaTestDataBuilder conCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
        return this;
    }

    public DtoReservaTestDataBuilder conDescuentos(List<DtoDescuento> descuentos) {
        this.descuentos = descuentos;
        return this;
    }

    public DtoReserva build() {
        return new DtoReserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, dtoMascota, codigoGenerado, descuentos);
    }
}
