--------consulta para ver prestamos 
select * from prestamo_libro
select * from cliente
select libro.titulo, cliente.nombre from prestamo_libro
inner join libro on prestamo_libro.isbn = libro.isbn

SELECT  id_prestamo, fecha, libro.isbn, libro.titulo, cliente.nombre, periodoprestamo FROM prestamo_libro
                     inner JOIN libro ON prestamo_libro.isbn = libro.isbn
					 inner JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente

select cliente.nombre,  