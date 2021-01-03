package com.ceiba.producto.controlador;

import com.ceiba.producto.consulta.ManejadorListarProductos;
import com.ceiba.producto.consulta.ManejadorListarProductosConDescuento;
import com.ceiba.producto.modelo.dto.DtoProducto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Api(tags = {"Consulta controlador producto"})
public class ConsultaControladorProducto {
    private final ManejadorListarProductos manejadorListarProductos;
    private final ManejadorListarProductosConDescuento manejadorListarProductosConDescuento;

    public ConsultaControladorProducto(ManejadorListarProductos manejadorListarProductos, ManejadorListarProductosConDescuento manejadorListarProductosConDescuento) {
        this.manejadorListarProductos = manejadorListarProductos;
        this.manejadorListarProductosConDescuento = manejadorListarProductosConDescuento;
    }

    @GetMapping
    @ApiOperation("Listar productos. Si se pasa un código de reserva como parámetro de consulta se listan los productos con los descuentos que apliquen")
    public List<DtoProducto> listar(@RequestParam(name = "codigo_reserva", required = false) String codigoReserva) {
        return codigoReserva == null ? manejadorListarProductos.ejecutar() : manejadorListarProductosConDescuento.ejecutar(codigoReserva);
    }


}
