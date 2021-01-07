package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private Long idMascota;
    private String codigoGenerado;

    public ComandoReservaTestDataBuilder() {
        numeroMesa = 1;
        fechaYHora = LocalDateTime.now().plusDays(1L).withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        idMascota = 1234L;
        codigoGenerado = "0002120366100001_1234";
    }

    public ComandoReservaTestDataBuilder conNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaYhora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public ComandoReservaTestDataBuilder conNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conIdMascota(Long idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public ComandoReservaTestDataBuilder conCodigoGenerado(String codigoGenerado) {
        this.codigoGenerado = codigoGenerado;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, idMascota, codigoGenerado);
    }
}
