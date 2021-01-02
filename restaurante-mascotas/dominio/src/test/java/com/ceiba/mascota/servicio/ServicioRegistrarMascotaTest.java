package com.ceiba.mascota.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mascota.constante.Especie;
import com.ceiba.mascota.modelo.entidad.Mascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.testdatabuilder.MascotaTestDataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioRegistrarMascotaTest {
    private ServicioRegistrarMascota servicioRegistrarMascota;
    private RepositorioMascota repositorioMascota;

    @Before
    public void inicializar() {
        repositorioMascota = mock(RepositorioMascota.class);
        servicioRegistrarMascota = new ServicioRegistrarMascota(repositorioMascota);
        when(repositorioMascota.registrar(any())).thenReturn(1L);
    }

    @Test
    public void ejecutar() {
        // arrange
        Mascota mascota = new MascotaTestDataBuilder().build();

        // act - assert
        Assert.assertEquals(1L, servicioRegistrarMascota.ejecutar(mascota).longValue());
    }

    @Test
    public void ejecutarSinNombre() {
        // arrange
        MascotaTestDataBuilder mascotaSinNombre = new MascotaTestDataBuilder().conNombre(null);

        // act - assert
        BasePrueba.assertThrows(
                mascotaSinNombre::build,
                ExcepcionValorObligatorio.class,
                "Se debe ingresar el nombre de la mascota"
        );
    }

    @Test
    public void ejecutarSinEspecie() {
        // arrange
        MascotaTestDataBuilder mascotaSinEspecie = new MascotaTestDataBuilder().conEspecie(null);

        // act - assert
        BasePrueba.assertThrows(
                mascotaSinEspecie::build,
                ExcepcionValorObligatorio.class,
                "Se debe escoger la especie de la mascota"
        );
    }

    @Test
    public void ejecutarConEspecieInvalida() {
        // arrange
        MascotaTestDataBuilder mascotaConEspecieInvalida = new MascotaTestDataBuilder().conEspecie("ELEFANTE");
        List<Especie> especies = Arrays.asList(Especie.values());

        // act - assert
        BasePrueba.assertThrows(
                mascotaConEspecieInvalida::build,
                ExcepcionValorInvalido.class,
                String.format("ELEFANTE no es una especie válida. Las especies válidas son: %s", especies)
        );
    }

    @Test
    public void ejecutarSinEdad() {
        // arrange
        MascotaTestDataBuilder mascotaSinEdad = new MascotaTestDataBuilder().conEdad(null);

        // act - assert
        BasePrueba.assertThrows(
                mascotaSinEdad::build,
                ExcepcionValorObligatorio.class,
                "Se debe especificar la edad de la mascota"
        );
    }

    @Test
    public void ejecutarConEdadMenorACero() {
        // arrange
        MascotaTestDataBuilder mascotaConEdadMenorACero = new MascotaTestDataBuilder().conEdad(-1);

        // act - assert
        BasePrueba.assertThrows(
                mascotaConEdadMenorACero::build,
                ExcepcionValorInvalido.class,
                "Se debe ingresar un número positivo para la edad"
        );
    }
}
