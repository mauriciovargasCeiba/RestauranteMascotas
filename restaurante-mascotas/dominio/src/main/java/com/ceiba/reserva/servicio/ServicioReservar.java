package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import static com.ceiba.reserva.CondicionFechaDescuentoReserva.*;

public class ServicioReservar {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;

    public ServicioReservar(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public Long ejecutar(Reserva reserva) {
        Long numeroReservasEnMismoMesParaMismaMascota =
                daoReserva.contarConFechaYMascota(reserva.getFechaYHora(), reserva.getIdMascota());
        Boolean mascotaSiHaVenidoMasDeTresVecesEnMes =
                numeroReservasEnMismoMesParaMismaMascota == TRES_VECES_EN_UN_MES.obtenerValorNumerico();

        reserva.confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(mascotaSiHaVenidoMasDeTresVecesEnMes);
        reserva.generarCodigo();
        return this.repositorioReserva.reservar(reserva);
    }

}
