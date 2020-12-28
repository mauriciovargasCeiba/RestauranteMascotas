package com.ceiba.producto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.producto.comando.manejador.ManejadorCalcularDescuentoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.reserva.comando.ComandoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Api(tags = {"Comando controlador producto"})
public class ComandoControladorProducto {

    private final ManejadorCalcularDescuentoProducto manejadorCalcularDescuentoProducto;
    private final RestTemplate restTemplate;

    @Autowired
    public ComandoControladorProducto(ManejadorCalcularDescuentoProducto manejadorCalcularDescuentoProducto, RestTemplate restTemplate) {
        this.manejadorCalcularDescuentoProducto = manejadorCalcularDescuentoProducto;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    @ApiOperation("Calcular descuento de productos según la reserva")
    public ComandoRespuesta<List<DtoProducto>> calcularDescuento(@RequestParam(name = "id_reserva") String idReserva) {

        ComandoReserva comandoReserva = restTemplate.getForObject(
                String.format("http://localhost:8083/restaurante-mascotas/reservas/%s", idReserva), ComandoReserva.class
        );

        if (comandoReserva == null) {
            throw new ExcepcionTecnica(String.format("No fue posible calcular los descuentos para la reserva con id %s. Esta reserva no existe", idReserva));
        }

        return manejadorCalcularDescuentoProducto.ejecutar(comandoReserva);
    }
}
