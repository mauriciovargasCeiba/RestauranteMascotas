package com.ceiba.descuento.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.servicio.testdatabuilder.DtoProductoTestDataBuilder;
import com.ceiba.reserva.excepcion.ExcepcionReservaInexistente;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.ceiba.producto.constante.TipoClienteProducto.HUMANO;
import static com.ceiba.producto.constante.TipoClienteProducto.MASCOTA;
import static com.ceiba.producto.constante.TipoProducto.COMIDA;
import static com.ceiba.producto.constante.TipoProducto.JUGUETE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCalcularDescuentoTest {
    private ServicioCalcularDescuento servicioCalcularDescuento;
    private DaoReserva daoReserva;

    @Before
    public void inicializar() {
        daoReserva = mock(DaoReserva.class);
        servicioCalcularDescuento = new ServicioCalcularDescuento(daoReserva);
        when(daoReserva.existe(anyString())).thenReturn(true);
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
        String codigoInvalido = "0x";
        DtoProducto productoSinDescuento = new DtoProductoTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioCalcularDescuento.ejecutar(codigoInvalido, productoSinDescuento),
                ExcepcionValorInvalido.class,
                "El código ingresado tiene un formato inválido"
        );
    }

    @Test
    public void ejecutarConReservaInexistente() {
        // arrange
        String codigoInexistente = "00021203660000_0000";
        when(daoReserva.existe(codigoInexistente)).thenReturn(false);
        DtoProducto producto = new DtoProductoTestDataBuilder().build();

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioCalcularDescuento.ejecutar(codigoInexistente, producto),
                ExcepcionReservaInexistente.class,
                "La reserva con código 00021203660000_0000 no existe en el sistema"
        );

    }

}
