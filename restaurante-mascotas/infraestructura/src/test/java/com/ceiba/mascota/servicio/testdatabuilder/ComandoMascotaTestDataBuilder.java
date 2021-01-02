package com.ceiba.mascota.servicio.testdatabuilder;

import com.ceiba.mascota.comando.ComandoMascota;

import static com.ceiba.mascota.constante.Especie.PERRO;

public class ComandoMascotaTestDataBuilder {

    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;

    public ComandoMascotaTestDataBuilder() {
        id = 1L;
        nombre = "Mascota Test";
        especie = PERRO.toString();
        edad = 6;
    }

    public ComandoMascotaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoMascotaTestDataBuilder conEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public ComandoMascotaTestDataBuilder conEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public ComandoMascota build() {
        return new ComandoMascota(id, nombre, especie, edad);
    }
}
