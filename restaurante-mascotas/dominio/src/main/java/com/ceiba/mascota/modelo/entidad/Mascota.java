package com.ceiba.mascota.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;
import static com.ceiba.mascota.validador.ValidadorEspecie.*;

@Getter
public class Mascota {

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_MASCOTA = "Se debe ingresar el nombre de la mascota";
    private static final String SE_DEBE_ESCOGER_LA_ESPECIE_DE_LA_MASCOTA = "Se debe escoger la especie de la mascota";
    private static final String SE_DEBE_ESPECIFICAR_LA_EDAD_DE_LA_MASCOTA = "Se debe especificar la edad de la mascota";
    private static final String SE_DEBE_INGRESAR_UN_NUMERO_POSITIVO_PARA_LA_EDAD = "Se debe ingresar un número positivo para la edad";

    private Long id;
    private String nombre;
    private String especie;
    private Integer edad;

    public Mascota(Long id, String nombre, String especie, Integer edad) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_MASCOTA);
        validarObligatorio(especie, SE_DEBE_ESCOGER_LA_ESPECIE_DE_LA_MASCOTA);
        validarEspecie(especie);
        validarObligatorio(edad, SE_DEBE_ESPECIFICAR_LA_EDAD_DE_LA_MASCOTA);
        validarPositivo(edad.doubleValue(), SE_DEBE_INGRESAR_UN_NUMERO_POSITIVO_PARA_LA_EDAD);

        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;

    }
}
