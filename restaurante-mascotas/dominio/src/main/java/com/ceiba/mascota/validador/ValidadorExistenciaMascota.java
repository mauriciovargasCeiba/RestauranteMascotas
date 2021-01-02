package com.ceiba.mascota.validador;

import com.ceiba.mascota.excepcion.ExcepcionMascotaInexistente;
import com.ceiba.mascota.puerto.dao.DaoMascota;

public final class ValidadorExistenciaMascota {

    private static final String LA_MASCOTA_CON_ID_NO_EXISTE_EN_EL_SISTEMA = "La mascota con id %s no existe en el sistema";

    private ValidadorExistenciaMascota() {
    }

    public static void validarExistenciaMascotaConId(Long id, DaoMascota daoMascota) {
        if (Boolean.FALSE.equals(daoMascota.existe(id))) {
            throw new ExcepcionMascotaInexistente(String.format(LA_MASCOTA_CON_ID_NO_EXISTE_EN_EL_SISTEMA, id));
        }
    }
}
