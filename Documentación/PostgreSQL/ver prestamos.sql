--------consulta para ver prestamos 
select * from prestamo_libro
select * from cliente
select * from empleado
select libro.titulo, cliente.nombre from prestamo_libro
inner join libro on prestamo_libro.isbn = libro.isbn

SELECT  id_prestamo, fecha, libro.isbn, libro.titulo, cliente.nombre, periodoprestamo FROM prestamo_libro
                     inner JOIN libro ON prestamo_libro.isbn = libro.isbn
					 inner JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente


select * from devolucion_prestamo

alter table devolucion_prestamo alter column fecha type varchar(10)

select id_devolucion, fecha, multa, descripcion_multa, id_prestamo, id_empleado from devolucion_prestamo 
SELECT  id_devolucion, devolucion_prestamo.fecha, multa, descripcion_multa, prestamo_libro.id_prestamo, libro.titulo, cliente.nombre, prestamo_libro.periodoprestamo FROM devolucion_prestamo
                     inner JOIN libro ON libro.isbn = libro.isbn
					 inner JOIN cliente ON cliente.id_cliente = cliente.id_cliente
					 inner JOIN prestamo_libro ON prestamo_libro.periodoprestamo = prestamo_libro.periodoprestamo

select devolucion_prestamo.id_devolucion, devolucion_prestamo.fecha, devolucion_prestamo.multa, devolucion_prestamo.descripcion_multa,
		prestamo_libro.isbn,
		libro.titulo as titulo_Libro,
		cliente.nombre AS nombre_Cliente,
		empleado.usuario as usuario_Empleado
from devolucion_prestamo 
inner JOIN prestamo_libro ON devolucion_prestamo.id_prestamo = prestamo_libro.id_prestamo
inner JOIN libro ON 	prestamo_libro.isbn = libro.isbn
inner Join cliente on prestamo_libro.id_cliente = cliente.id_cliente
inner JOIN empleado ON devolucion_prestamo.id_empleado = empleado.id_empleado

select * from devolucion_prestamo;
select * from prestamo_libro

SELECT devolucion_prestamo.id_devolucion, devolucion_prestamo.fecha, devolucion_prestamo.multa, devolucion_prestamo.descripcion_multa, prestamo_libro.id_prestamo, prestamo_libro.isbn,
    libro.titulo, cliente.nombre, empleado.usuario
FROM devolucion_prestamo
INNER JOIN prestamo_libro ON devolucion_prestamo.id_prestamo = prestamo_libro.id_prestamo
INNER JOIN libro ON prestamo_libro.isbn = libro.isbn
INNER JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente
INNER JOIN empleado ON devolucion_prestamo.id_empleado = empleado.id_empleado;


--------Buscar
SELECT prestamo_libro.id_prestamo, prestamo_libro.fecha, prestamo_libro.periodoprestamo, prestamo_libro.isbn, prestamo_libro.id_cliente,  cliente.nombre, libro.titulo
             FROM prestamo_libro
             INNER JOIN libro  ON prestamo_libro.isbn = libro.isbn
             INNER JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente
             WHERE prestamo_libro.id_prestamo = ?;