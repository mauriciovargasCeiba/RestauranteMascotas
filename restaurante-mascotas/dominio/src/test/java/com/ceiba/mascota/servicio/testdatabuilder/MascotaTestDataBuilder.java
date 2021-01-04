package com.ceiba.mascota.servicio.testdatabuilder;

import com.ceiba.mascota.modelo.entidad.Mascota;

import static com.ceiba.mascota.constante.Especie.*;

public class MascotaTestDataBuilder {
    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;

    public MascotaTestDataBuilder() {
        id = 1L;
        nombre = "Mascota Test";
        especie = PERRO.toString();
        edad = 6;
    }

    public MascotaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public MascotaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MascotaTestDataBuilder conEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public MascotaTestDataBuilder conEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public Mascota build() {
        return new Mascota(id, nombre, especie, edad);
    }

}
