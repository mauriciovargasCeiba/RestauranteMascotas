create table mascota (
    id int not null auto_increment,
    nombre varchar(100) not null,
    especie enum('PERRO', 'GATO') not null,
    edad int not null,
    primary key (id)
);

create table reserva (
 id int not null auto_increment,
 numero_mesa int not null,
 fecha_hora datetime not null,
 nombre_completo varchar(100) not null,
 telefono varchar(10) not null,
 id_mascota int,
 codigo_generado varchar(30),
 primary key (id),
 foreign key (id_mascota) references mascota(id) on delete cascade
);

create table descuento (
    id int not null auto_increment,
    descripcion varchar(255) not null,
    valor decimal(5,2) not null,
    primary key (id)
);

create table reserva_con_descuento (
    id_reserva varchar(30) not null,
    id_descuento int not null,
    vigente boolean not null,
    primary key (id_reserva, id_descuento),
    foreign key (id_reserva) references reserva(id) on delete cascade,
    foreign key (id_descuento) references descuento(id)
);
