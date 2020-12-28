package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorExisteReserva {

    private final DaoReserva daoReserva;

    public ManejadorExisteReserva(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva ejecutar(String idReserva) {
        return daoReserva.existe(idReserva);
    }
}
