select * from prestamo_libro
select * from cliente 
alter table prestamo_libro add column estado varchar(10)
SELECT COUNT(*) 
FROM prestamo_libro WHERE id_cliente = 1 AND estado = 'pendiente'