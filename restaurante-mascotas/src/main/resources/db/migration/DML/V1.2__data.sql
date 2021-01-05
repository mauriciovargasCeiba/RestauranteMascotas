insert into producto (id, nombre, tipo, tipo_cliente, precio) values (1, 'Papas Fritas', 'COMIDA', 'HUMANO', 7000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (2, 'Hamburguesa', 'COMIDA', 'HUMANO', 10000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (3, 'Gaseosa', 'COMIDA', 'HUMANO', 2350);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (4, 'Helado', 'COMIDA', 'HUMANO', 2500);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (5, 'Pizza', 'COMIDA', 'HUMANO', 9000);

insert into producto (id, nombre, tipo, tipo_cliente, precio) values (6, 'Whiskas', 'COMIDA', 'MASCOTA', 3000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (7, 'Dog Chow, plato pequeño', 'COMIDA', 'MASCOTA', 5000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (8, 'Dog Chow, plato grande', 'COMIDA', 'MASCOTA', 10000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (9, 'Cat Chow, plato pequeño', 'COMIDA', 'MASCOTA', 1500);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (10, 'Cat Chow, plato grande', 'COMIDA', 'MASCOTA', 5000);

insert into producto (id, nombre, tipo, tipo_cliente, precio) values (11, 'Hueso de perro', 'JUGUETE', 'MASCOTA', 7000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (12, 'Ratón de goma', 'JUGUETE', 'MASCOTA', 2700);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (13, 'Pelota', 'JUGUETE', 'MASCOTA', 6000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (14, 'Collar decorado', 'JUGUETE', 'MASCOTA', 10000);
insert into producto (id, nombre, tipo, tipo_cliente, precio) values (15, 'Bola con plumas', 'JUGUETE', 'MASCOTA', 15500);

insert into descuento (id, descripcion, valor) values (0, 'Sin Descuento', 0);
insert into descuento (id, descripcion, valor) values (1, '40% de descuento en cualquier producto para mascotas en la próxima reserva', 40);
insert into descuento (id, descripcion, valor) values (2, '10% en la comida del cliente', 10);
insert into descuento (id, descripcion, valor) values (3, '2 juguetes gratis para mascotas', 100);

insert into tipo_producto (id, valor) values (0, 'COMIDA');
insert into tipo_producto (id, valor) values (1, 'JUGUETE');

insert into tipo_cliente (id, valor) values (0, 'HUMANO');
insert into tipo_cliente (id, valor) values (1, 'MASCOTA');

insert into descuento_para_tipo_producto_y_cliente values (0, 1, 0, 1);
insert into descuento_para_tipo_producto_y_cliente values (1, 1, 1, 1);

insert into descuento_para_tipo_producto_y_cliente values (2, 2, 0, 0);

insert into descuento_para_tipo_producto_y_cliente values (3, 3, 1, 1);