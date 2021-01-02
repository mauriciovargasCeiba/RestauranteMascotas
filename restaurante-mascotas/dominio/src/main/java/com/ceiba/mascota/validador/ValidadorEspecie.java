package com.ceiba.mascota.validador;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.constante.Especie;

import java.util.Arrays;
import java.util.List;

public final class ValidadorEspecie {

    private static final String NO_ES_UNA_ESPECIE_VALIDA = "%s no es una especie válida. Las especies válidas son: %s";

    private ValidadorEspecie() {
    }

    public static void validarEspecie(String especie) {
        try {
            Especie.valueOf(especie);
        } catch (IllegalArgumentException e){
            List<Especie> especiesValidas = Arrays.asList(Especie.values());
            throw new ExcepcionValorInvalido(String.format(NO_ES_UNA_ESPECIE_VALIDA, especie, especiesValidas));
        }
    }
}
