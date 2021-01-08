package com.ceiba.reserva.servicio;

import com.ceiba.reserva.puerto.repositorio.RepositorioDescuento;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import static com.ceiba.reserva.constante.NumeroReferenciaDescuento.*;
import static com.ceiba.reserva.constante.NumeroReferenciaDescuento.NUM_REF_SIN_DESCUENTO;
import static com.ceiba.mascota.validador.ValidadorExistenciaMascota.validarExistenciaMascotaConId;
import static com.ceiba.reserva.constante.FechaDescuento.TRES_VECES_EN_UN_MES;
import static com.ceiba.reserva.validador.ValidadorExistenciaReserva.validarExistenciaReservaConMesaYFechaYHora;

public class ServicioReservar {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private final DaoMascota daoMascota;
    private final RepositorioDescuento repositorioDescuento;

    public ServicioReservar(RepositorioReserva repositorioReserva, DaoReserva daoReserva, DaoMascota daoMascota, RepositorioDescuento repositorioDescuento) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
        this.daoMascota = daoMascota;
        this.repositorioDescuento = repositorioDescuento;
    }

    public SortedMap<Long, String> ejecutar(Reserva reserva) {
        validarExistenciaReservaConMesaYFechaYHora(reserva, daoReserva);

        if (reserva.incluyeMascota()) {
            validarExistenciaMascotaConId(reserva.getIdMascota(), daoMascota);
            confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(reserva);
        }

        reserva.generarCodigo();
        SortedMap<Long, String> idYCodigoGenerado = this.repositorioReserva.reservar(reserva);

        Long idReserva = idYCodigoGenerado.firstKey();
        asignarDescuentos(reserva, idReserva);

        return idYCodigoGenerado;
    }

    private void confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(Reserva reserva) {
        Long numeroReservasEnMismoMesParaMismaMascota =
                daoReserva.contarConFechaYMascota(reserva.getFechaYHora(), reserva.getIdMascota());
        Boolean mascotaSiHaVenidoMasDeTresVecesEnMes =
                numeroReservasEnMismoMesParaMismaMascota == TRES_VECES_EN_UN_MES.obtenerValorNumerico();

        reserva.confirmarMascotaHaVenidoMasDeTresVecesEnUnMes(mascotaSiHaVenidoMasDeTresVecesEnMes);
    }

    private void asignarDescuentos(Reserva reserva, Long idReserva) {
        List<Long> descuentos = new ArrayList<>();
        if (reserva.incluyeMascota()) {
            if (reserva.incluyeMascotaQueHaVenidoMasDeTresVecesEnUnMes()) {
                descuentos.add(NUM_REF_DESCUENTO_CUARENTA_POR_CIENTO_COMIDA_JUGUETES_MASCOTA.obtenerValorNumerico());
            }
            if (reserva.esEntreDosPmYCuatroPmYNoEsDomingo()) {
                descuentos.add(NUM_REF_DESCUENTO_DIEZ_POR_CIENTO_COMIDA_CLIENTE.obtenerValorNumerico());
            }
            if (reserva.esDiaPrimeroODiaQuinceDelMes()) {
                descuentos.add(NUM_REF_DESCUENTO_DOS_JUGUETES_GRATIS_MASCOTA.obtenerValorNumerico());
            }
        } else {
            descuentos.add(NUM_REF_SIN_DESCUENTO.obtenerValorNumerico());
        }

        for (Long idDescuento : descuentos) {
            repositorioDescuento.asignarAReserva(idDescuento, idReserva);
        }
    }

}
