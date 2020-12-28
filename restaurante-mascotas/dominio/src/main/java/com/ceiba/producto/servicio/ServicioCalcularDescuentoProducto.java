package com.ceiba.producto.servicio;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.reserva.modelo.entidad.Reserva;

import java.util.List;

public class ServicioCalcularDescuentoProducto {

    private final DaoProducto daoProducto;

    public ServicioCalcularDescuentoProducto(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public List<DtoProducto> ejecutar(Reserva reserva) {
       return daoProducto.listar();
    }

}
