package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDateTime;

public class DtoReservaTestDataBuilder {
    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private Long idMascota;
    private String codigoGenerado;

    public DtoReservaTestDataBuilder() {
        id = 1L;
        numeroMesa = 1;
        fechaYHora = LocalDateTime.now().plusDays(1L).withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        idMascota = 1234L;
        codigoGenerado = "123_1234";
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

    public DtoReservaTestDataBuilder conIdMascota(Long idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public DtoReservaTestDataBuilder conCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
        return this;
    }

    public DtoReserva build() {
        return new DtoReserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, idMascota, codigoGenerado);
    }
}
