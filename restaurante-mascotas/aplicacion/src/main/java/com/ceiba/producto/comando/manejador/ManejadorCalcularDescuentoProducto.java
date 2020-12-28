package com.ceiba.producto.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.servicio.ServicioCalcularDescuentoProducto;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.comando.fabrica.FabricaReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorCalcularDescuentoProducto implements ManejadorComandoRespuesta<ComandoReserva, ComandoRespuesta<List<DtoProducto>>> {

    private final ServicioCalcularDescuentoProducto servicioCalcularDescuentoProducto;
    private final FabricaReserva fabricaReserva;

    public ManejadorCalcularDescuentoProducto(ServicioCalcularDescuentoProducto servicioCalcularDescuentoProducto, FabricaReserva fabricaReserva) {
        this.servicioCalcularDescuentoProducto = servicioCalcularDescuentoProducto;
        this.fabricaReserva = fabricaReserva;
    }

    @Override
    public ComandoRespuesta<List<DtoProducto>> ejecutar(ComandoReserva comandoReserva) {
        Reserva reserva = fabricaReserva.crear(comandoReserva);
        return new ComandoRespuesta<>(servicioCalcularDescuentoProducto.ejecutar(reserva));
    }
}
