package com.ceiba.reserva.adaptador.dao;

import com.ceiba.reserva.modelo.dto.DtoDescuento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    private final Map<Long, List<DtoDescuento>> descuentos;

    public MapeoReserva(Map<Long, List<DtoDescuento>> descuentos) {
        this.descuentos = descuentos;
    }

    @Override
    public DtoReserva mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        Integer numeroMesa = rs.getInt("numero_mesa");
        LocalDateTime fechaYHora = extraerLocalDateTime(rs, "fecha_hora");
        String nombreCompletoCliente = rs.getString("nombre_completo");
        String telefonoCliente = rs.getString("telefono");
        DtoMascota mascota = mapRowMascota(rs);
        String codigoGenerado = rs.getString("codigo_generado");
        List<DtoDescuento> dtoDescuentos = this.descuentos.get(id);

        return new DtoReserva(id, numeroMesa, fechaYHora, nombreCompletoCliente, telefonoCliente, mascota, codigoGenerado, dtoDescuentos);
    }

    private DtoMascota mapRowMascota(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id_mascota");
        String nombre = rs.getString("nombre_mascota");
        String especie =  rs.getString("especie_mascota");
        Integer edad = rs.getInt("edad_mascota");

        return new DtoMascota(id, nombre, especie, edad);
    }

}
