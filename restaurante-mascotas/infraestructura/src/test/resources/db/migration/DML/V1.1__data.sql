insert into mascota (id, nombre, especie, edad) values (1234, 'Mascota test', 'PERRO', 5);

insert into reserva (id, numero_mesa, fecha_hora, nombre_completo, telefono, id_mascota, codigo_generado) values (1, 1, '2120-12-31 00:00:00', 'Cliente Test', '1234567890', 1234, '0002120366000001_1234');
insert into reserva (numero_mesa, fecha_hora, nombre_completo, telefono, id_mascota, codigo_generado) values (2, '2120-12-15 00:00:00', 'Cliente Test 2', '1234567890', 1234, '0032120351000002_1234');
insert into reserva (numero_mesa, fecha_hora, nombre_completo, telefono, id_mascota, codigo_generado) values (3, '2120-12-15 10:10:00', 'Cliente Test 3', '1234567890', 1234, '0032120351101003_1234');

insert into descuento (id, descripcion, valor) values (999, 'Descuento Test', 999);
insert into reserva_con_descuento (id_reserva, id_descuento, vigente) values (1, 999, true);
