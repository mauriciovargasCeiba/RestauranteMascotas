package com.ceiba.mascota.puerto.dao;

import com.ceiba.mascota.modelo.dto.DtoMascota;

import java.util.List;

public interface DaoMascota {
    /**
     * Permite listar mascotas
     * @return las mascotas
     */
    List<DtoMascota> listar();

    /**
     * Permite validar si existe una mascota con un id
     * @param id
     * @return si existe o no
     */
    Boolean existe(Long id);
}
