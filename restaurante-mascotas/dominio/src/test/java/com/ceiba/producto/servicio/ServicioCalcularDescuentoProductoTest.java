package com.ceiba.producto.servicio;

import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.producto.servicio.testdatabuilder.ProductoTestDataBuilder;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioCalcularDescuentoProductoTest {

    private DaoProducto daoProducto;
    private ServicioCalcularDescuentoProducto servicioCalcularDescuentoProducto;

    @Before
    public void inicializar() {
        daoProducto = mock(DaoProducto.class);
        servicioCalcularDescuentoProducto = new ServicioCalcularDescuentoProducto(daoProducto);
    }

    @Test
    public void ejecutar() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        List<DtoProducto> productos = new ProductoTestDataBuilder().build();
        when(daoProducto.listar()).thenReturn(productos);

        // act - assert
        Assert.assertEquals(productos,servicioCalcularDescuentoProducto.ejecutar(reserva));
    }
}
