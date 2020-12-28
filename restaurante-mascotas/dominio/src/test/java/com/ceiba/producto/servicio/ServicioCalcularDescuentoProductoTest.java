package com.ceiba.producto.servicio;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.producto.servicio.testdatabuilder.ProductoTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCalcularDescuentoProductoTest {

    private DaoProducto daoProducto;
    private ServicioCalcularDescuentoProducto servicioCalcularDescuentoProducto;
    private Reserva reserva;

    @Before
    public void inicializar() {
        daoProducto = mock(DaoProducto.class);
        servicioCalcularDescuentoProducto = new ServicioCalcularDescuentoProducto(daoProducto);
    }

    @Test
    public void ejecutarConDescuentoDeDosPmACuatroPmEnSemana() {
        // arrange
        LocalDateTime fecha = LocalDateTime.of(2020, 12, 28, 14, 0); // No es domingo
        LocalDateTime hora = fecha; // Después de las 2:00 p.m.
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fecha).conHora(hora).build();

        DtoProducto JugueteAnimal = new ProductoTestDataBuilder().conTipo("OTROS").conTipoCliente("ANIMAL").build();
        DtoProducto ComidaHumano = new ProductoTestDataBuilder().conTipo("COMIDA").conTipoCliente("HUMANO").build();
        List<DtoProducto> productos = Arrays.asList(JugueteAnimal, ComidaHumano);
        when(daoProducto.listar()).thenReturn(productos);

        // act
        List<DtoProducto> productosConDescuento = servicioCalcularDescuentoProducto.ejecutar(reserva);

        // assert
        Assert.assertEquals(1000.0, productosConDescuento.get(0).getPrecio().longValue(), 0.1);
        Assert.assertEquals(900.0, productosConDescuento.get(1).getPrecio().longValue(), 0.1);
    }

    @Test
    public void ejecutarDescuentoDeDiaPrimeroOQuince() {
        // arrange
        LocalDateTime fecha = LocalDateTime.of(2020, 12, 1, 14, 0); // Es primero
        Reserva reserva = new ReservaTestDataBuilder().conFecha(fecha).build();

        DtoProducto JugueteAnimal = new ProductoTestDataBuilder().conTipo("OTROS").conTipoCliente("ANIMAL").build();
        List<DtoProducto> productos = Collections.singletonList(JugueteAnimal);
        when(daoProducto.listar()).thenReturn(productos);

        // act
        List<DtoProducto> productosConDescuento = servicioCalcularDescuentoProducto.ejecutar(reserva);

        // assert
        Assert.assertEquals(0, productosConDescuento.get(0).getPrecio().longValue(), 0.1);

    }

    @Test
    public void ejecutarSinMascota() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        List<DtoProducto> productos = Collections.singletonList(new ProductoTestDataBuilder().build());
        when(daoProducto.listar()).thenReturn(productos);

        // act - assert
        Assert.assertEquals(productos,servicioCalcularDescuentoProducto.ejecutar(reserva));
    }
}
