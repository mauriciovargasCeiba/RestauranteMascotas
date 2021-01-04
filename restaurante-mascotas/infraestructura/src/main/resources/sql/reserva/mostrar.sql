select r.id, r.numero_mesa, r.fecha_hora, r.nombre_completo, r.telefono, r.codigo_generado,
m.id as id_mascota, m.nombre as nombre_mascota, m.especie as especie_mascota, m.edad as edad_mascota
from reserva r
left join mascota m on m.id = r.id_mascota
where codigo_generado = :codigoGenerado