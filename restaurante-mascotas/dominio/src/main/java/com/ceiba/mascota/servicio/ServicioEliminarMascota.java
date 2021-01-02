package com.ceiba.mascota.servicio;

import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;

import static com.ceiba.mascota.validador.ValidadorExistenciaMascota.validarExistenciaMascotaConId;

public class ServicioEliminarMascota {
    private final RepositorioMascota repositorioMascota;
    private final DaoMascota daoMascota;

    public ServicioEliminarMascota(RepositorioMascota repositorioMascota, DaoMascota daoMascota) {
        this.repositorioMascota = repositorioMascota;
        this.daoMascota = daoMascota;
    }

    public void ejecutar(Long id) {
        validarExistenciaMascotaConId(id, daoMascota);
        repositorioMascota.eliminar(id);
    }
}
