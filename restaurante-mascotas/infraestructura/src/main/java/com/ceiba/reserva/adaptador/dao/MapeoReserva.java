package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Integer numeroMesa = rs.getInt("numero_mesa");
        LocalDateTime fechaYHora = extraerLocalDateTime(rs, "fecha_hora");
        String nombreCompletoCliente = rs.getString("nombre_completo");
        String telefonoCliente = rs.getString("telefono");
        Long idMascota = rs.getLong("id_mascota");
        String codigoGenerado = rs.getString("codigo_generado");

        return new DtoReserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, idMascota, codigoGenerado);
    }

}
