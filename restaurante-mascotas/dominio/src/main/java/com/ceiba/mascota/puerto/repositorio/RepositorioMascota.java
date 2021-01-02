package com.ceiba.mascota.puerto.repositorio;

import com.ceiba.mascota.modelo.entidad.Mascota;

public interface RepositorioMascota {
    /**
     * Permite registrar una mascota
     * @param mascota
     * @return el id generado
     */
    Long registrar(Mascota mascota);

    /**
     * Permite eliminar una mascota del sistema
     * @param id
     */
    void eliminar(Long id);
}
