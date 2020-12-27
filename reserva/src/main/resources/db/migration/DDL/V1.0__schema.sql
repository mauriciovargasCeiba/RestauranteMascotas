create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table reserva (
 id varchar(9) not null,
 numero_mesa int not null,
 fecha datetime not null,
 hora datetime not null,
 nombre_completo varchar(100) not null,
 telefono varchar(10) not null,
 id_mascota varchar(4),
 primary key (id)
);
