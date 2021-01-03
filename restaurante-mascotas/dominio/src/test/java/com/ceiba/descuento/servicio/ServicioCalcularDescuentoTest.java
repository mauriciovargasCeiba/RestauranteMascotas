package com.ceiba.descuento.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.servicio.testdatabuilder.DtoProductoTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.ceiba.producto.constante.TipoClienteProducto.*;
import static com.ceiba.producto.constante.TipoProducto.*;

public class ServicioCalcularDescuentoTest {
    private ServicioCalcularDescuento servicioCalcularDescuento;

    @Before
    public void inicializar() {
        servicioCalcularDescuento = new ServicioCalcularDescuento();
    }

    @Test
    public void ejecutarConMascotaQueHaVenidoMasDeTresVecesElMismoMes() {
        // arrange
        String codigoReserva = "00121203660000_1234";
        DtoProducto productoMascota = new DtoProductoTestDataBuilder().conTipoCliente(MASCOTA.obtenerValor()).build();

        // act - assert
        Assert.assertEquals(600.0, servicioCalcularDescuento.ejecutar(codigoReserva, productoMascota).longValue(), 0.01);
    }

    @Test
    public void ejecutarConReservaEntreDosPmYCuatroPmYNoEsDomingo() {
        // arrange
        String codigoReserva = "00221203661500_1234";
        DtoProducto comidaHumano = new DtoProductoTestDataBuilder().conTipo(COMIDA.obtenerValor()).conTipoCliente(HUMANO.obtenerValor()).build();

        // act - assert
        Assert.assertEquals(900.0, servicioCalcularDescuento.ejecutar(codigoReserva, comidaHumano).longValue(), 0.01);
    }

    @Test
    public void ejecutarConReservaDiaPrimeroDelMes() {
        // arrange
        String codigoReserva = "00321203360000_1234";
        DtoProducto jugueteMascota = new DtoProductoTestDataBuilder().conTipo(JUGUETE.obtenerValor()).conTipoCliente(MASCOTA.obtenerValor()).build();

        // act - assert
        Assert.assertEquals(0.0, servicioCalcularDescuento.ejecutar(codigoReserva, jugueteMascota).longValue(), 0.01);
    }

    @Test
    public void ejecutarConDescuentosMascotaParaProductoHumano() {
        // arrange
        String codigoReserva = "01321203360000_1234";
        DtoProducto productoHumano = new DtoProductoTestDataBuilder().conTipo(COMIDA.obtenerValor()).conTipoCliente(HUMANO.obtenerValor()).build();

        // act - assert
        Assert.assertEquals(1000.0, servicioCalcularDescuento.ejecutar(codigoReserva, productoHumano).longValue(), 0.01);
    }

    @Test
    public void ejecutarConDescuentosHumanoParaProductoMascota() {
        // arrange
        String codigoReserva = "00221203661500_1234";
        DtoProducto productoMascota = new DtoProductoTestDataBuilder().conTipo(JUGUETE.obtenerValor()).conTipoCliente(MASCOTA.obtenerValor()).build();

        // act - assert
        Assert.assertEquals(1000.0, servicioCalcularDescuento.ejecutar(codigoReserva, productoMascota).longValue(), 0.01);
    }

    @Test
    public void ejecutarSinDescuento() {
        // arrange
        String codigoReserva = "00021203660000_0000";
        DtoProducto productoSinDescuento = new DtoProductoTestDataBuilder().build();

        // act - assert
        Assert.assertEquals(1000.0, servicioCalcularDescuento.ejecutar(codigoReserva, productoSinDescuento).longValue(), 0.01);
    }

    @Test
    public void ejecutarConCodigoInvalido() {
        // arrange
        String codigoInvalido = "0";
        DtoProducto productoSinDescuento = new DtoProductoTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioCalcularDescuento.ejecutar(codigoInvalido, productoSinDescuento),
                ExcepcionLongitudValor.class,
                "El código de reserva debe ser de mínimo 10 caracteres"
        );
    }

}
