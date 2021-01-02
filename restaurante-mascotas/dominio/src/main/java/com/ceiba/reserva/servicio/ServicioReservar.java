package com.ceiba.reserva.servicio;

import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import static com.ceiba.mascota.validador.ValidadorExistenciaMascota.validarExistenciaMascotaConId;
import static com.ceiba.reserva.constante.CondicionFechaDescuentoReserva.TRES_VECES_EN_UN_MES;
import static com.ceiba.reserva.validador.ValidadorExistenciaReserva.validarExistenciaReservaConMesaYFechaYHora;

public class ServicioReservar {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private final DaoMascota daoMascota;

    public ServicioReservar(RepositorioReserva repositorioReserva, DaoReserva daoReserva, DaoMascota daoMascota) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
        this.daoMascota = daoMascota;
    }

    public Long ejecutar(Reserva reserva) {
        validarExistenciaReservaConMesaYFechaYHora(reserva, daoReserva);
        if (reserva.incluyeMascota()) {
            validarExistenciaMascotaConId(reserva.getIdMascota(), daoMascota);

            Long numeroReservasEnMismoMesParaMismaMascota =
                    daoReserva.contarConFechaYMascota(reserva.getFechaYHora(), reserva.getIdMascota());
            Boolean mascotaSiHaVenidoMasDeTresVecesEnMes =
                    numeroReservasEnMismoMesParaMismaMascota == TRES_VECES_EN_UN_MES.obtenerValorNumerico();

            reserva.confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(mascotaSiHaVenidoMasDeTresVecesEnMes);
        }
        reserva.generarCodigo();
        return this.repositorioReserva.reservar(reserva);
    }

}
