package com.ceiba.mascota.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.mascota.excepcion.ExcepcionMascotaInexistente;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ServicioEliminarMascotaTest {

    private ServicioEliminarMascota servicioEliminarMascota;
    private RepositorioMascota repositorioMascota;
    private DaoMascota daoMascota;

    @Before
    public void incializar() {
        repositorioMascota = mock(RepositorioMascota.class);
        daoMascota = mock(DaoMascota.class);
        servicioEliminarMascota = new ServicioEliminarMascota(repositorioMascota, daoMascota);
    }

    @Test
    public void ejecutar() {
        // arrange
        Long id = 1L;
        when(daoMascota.existe(id)).thenReturn(true);

        // act
        servicioEliminarMascota.ejecutar(id);

        // assert
        verify(repositorioMascota, times(1)).eliminar(id);
    }

    @Test
    public void ejecutarConMascotaInexistente() {
        // arrange
        Long id = 1L;
        when(daoMascota.existe(id)).thenReturn(false);

        // act - assert
        BasePrueba.assertThrows(
                () -> servicioEliminarMascota.ejecutar(id),
                ExcepcionMascotaInexistente.class,
                "La mascota con id 1 no existe en el sistema"
        );
    }
}
