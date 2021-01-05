select r.id as id_reserva,
d.id as id_descuento, d.descripcion as descripcion_descuento, d.valor as valor_descuento, rd.vigente as vigente_descuento
from reserva r
inner join reserva_con_descuento rd on rd.id_reserva = r.id
inner join descuento d on d.id = rd.id_descuento

