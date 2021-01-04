package com.ceiba.mascota.servicio.testdatabuilder;

import com.ceiba.mascota.modelo.dto.DtoMascota;

import static com.ceiba.mascota.constante.Especie.PERRO;

public class DtoMascotaTestDataBuilder {
    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;

    public DtoMascotaTestDataBuilder() {
        id = 1L;
        nombre = "Mascota Test";
        especie = PERRO.toString();
        edad = 6;
    }

    public DtoMascotaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoMascotaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public DtoMascotaTestDataBuilder conEspecie(String especie) {
        this.especie = especie;
        return this;
    }

    public DtoMascotaTestDataBuilder conEdad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public DtoMascota build() {
        return new DtoMascota(id, nombre, especie, edad);
    }

}
