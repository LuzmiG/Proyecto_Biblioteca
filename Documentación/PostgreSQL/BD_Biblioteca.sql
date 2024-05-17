Create table cliente (
	id_cliente serial primary key not null,
	usuario varchar (50),
	pass varchar (50),
	privilegio varchar (20),
	telefono int,
	correo varchar (50)
);
insert into cliente (usuario, pass, privilegio, telefono, correo) 
			values ('Luzmi', '123', 'cliente', 41569459, 'luzgil@gmail.com');
select * from cliente

------CREAR TABLA EMPLEADO
create table empleado (
	id_empleado serial primary key not null,
	nombre varchar (50),
	contraseña varchar (50),
	privilegio varchar (20),
	sueldo decimal,
	horario varchar (50)
);
 alter table empleado rename column contraseña to pass
 select * from empleado
insert into empleado (nombre, pass, sueldo, horario) 
			   values('Sandra', '456', 3550.60, 'Lun-Vie')
			   
------------CREAR TABLA LIBRO
create table libro (
	isbn serial primary key not null,
	titulo varchar (255),
	autor varchar (255),
	anioPublicacion int,
	editorial varchar (50),
	descripcion varchar(255),
	cantidadDisponible int,
	categoria varchar (20)
);
drop table libro

insert into libro (titulo, autor, anioPublicacion, editorial, descripcion, cantidadDisponible, categoria)
			values ('Don Quijote De La Mancha', 'Miguel De Cervantes Saavedra', 2018, 'MESTAS', 'La historia de un viejo que confunde la ?cción con la realidad y que se inventa pasiones para afrontar sus días de madurez, nos hace re?exionar a todos sobre qué es realmente la cordura y a admirar los momentos.',
				   8, 'clásica ')

insert into libro (titulo, autor, anioPublicacion, editorial, descripcion, cantidadDisponible, categoria)
			values ('Migrante', 'Marcos Antil', 2019, 'Publicacion Independiente', 'Yo soy Marcos Antil, guatemalteco, maya qanjobal, migrante, padre de familia, empresario tecnológico, fundador de la compañía XumaK en Estados Unidos, Colombia y Guatemala. Con clientes en más de 25 países entre los cuales figuran empresas del Fortune 500',
				   12, 'Humanidades')

select * from libro


----------------------CHAN CHAN CHAAAAAAAAN
---------lAS TABLAS CON FOREING KEY

create table prestamo_Libro(
	id_prestamo serial primary key not null,
	fecha date,
	periodoPrestamo varchar (50),
	isbn int,
	id_cliente int ,
	foreign key (isbn) references libro (isbn),
	foreign key (id_cliente) references cliente (id_cliente)
);

insert into prestamo_libro (fecha, periodoPrestamo, isbn, id_cliente) 
					values('06/05/2024', '3 Dias', 1, 1)

select * from prestamo_libro


create table devolucion_prestamo (
	id_devolucion serial primary key not null,
	fecha date,
	multa int,
	descripcion_multa varchar (100),
	id_prestamo int,
	
	foreign key (id_prestamo) references prestamo_libro (id_prestamo)	
);

alter table devolucion_prestamo add id_empleado int
alter table devolucion_prestamo add constraint fk_empleado
foreign key (id_empleado) 
references empleado (id_empleado)

insert into devolucion_prestamo (fecha, multa, descripcion_multa, id_prestamo, id_empleado)
						values ('09/05/2024', 50, 'Dos dias de atraso', 1, 1
								
select * from devolucion_prestamo
select *  from empleado