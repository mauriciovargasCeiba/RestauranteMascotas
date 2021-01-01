package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {

    private Long id;
    private Integer numeroMesa;
    private LocalDateTime fechaYHora;
    private String nombreCompletoCliente;
    private String telefonoCliente;
    private Long idMascota;
    private Boolean mascotaHaVenidoMasDeTresVecesEnUnMes;

    public ReservaTestDataBuilder() {
        id = 1L;
        numeroMesa = 1;
        fechaYHora = LocalDateTime.now().plusDays(1L).withHour(12);
        nombreCompletoCliente = "Cliente Test";
        telefonoCliente = "1234567890";
        idMascota = 1234L;
        mascotaHaVenidoMasDeTresVecesEnUnMes = false;

    }

    public ReservaTestDataBuilder conNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
        return this;
    }

    public ReservaTestDataBuilder conFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
        return this;
    }

    public ReservaTestDataBuilder conNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public ReservaTestDataBuilder conTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
        return this;
    }

    public ReservaTestDataBuilder conIdMascota(Long idMascota) {
        this.idMascota = idMascota;
        return this;
    }

    public ReservaTestDataBuilder confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(Boolean mascotaHaVenidoMasDeTresVecesEnUnMes) {
        this.mascotaHaVenidoMasDeTresVecesEnUnMes = mascotaHaVenidoMasDeTresVecesEnUnMes;
        return this;
    }

    public Reserva build() {
        Reserva reserva = new Reserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, idMascota);
        reserva.confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(mascotaHaVenidoMasDeTresVecesEnUnMes);
        return reserva;
    }
}
