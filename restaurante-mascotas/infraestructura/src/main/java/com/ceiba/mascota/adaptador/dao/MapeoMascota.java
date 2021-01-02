package com.ceiba.mascota.adaptador.dao;

import com.ceiba.mascota.modelo.dto.DtoMascota;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoMascota implements RowMapper<DtoMascota> {

    @Override
    public DtoMascota mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        String especie =  rs.getString("especie");
        Integer edad = rs.getInt("edad");

        return new DtoMascota(id, nombre, especie, edad);
    }

}
