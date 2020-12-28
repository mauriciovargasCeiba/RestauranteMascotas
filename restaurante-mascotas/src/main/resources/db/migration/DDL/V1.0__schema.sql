create table reserva (
 id int not null auto_increment,
 numero_mesa int not null,
 fecha datetime not null,
 hora datetime not null,
 nombre_completo varchar(100) not null,
 telefono varchar(10) not null,
 id_mascota varchar(4),
 primary key (id)
);

create table producto (
    id int not null auto_increment,
    nombre varchar(100) not null,
    tipo varchar(50) not null,
    tipo_cliente varchar(50) not null,
    precio decimal(7,2) not null,
    primary key (id)
);