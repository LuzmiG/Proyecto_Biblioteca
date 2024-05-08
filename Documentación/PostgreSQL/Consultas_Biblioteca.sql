--CONSULTAS 
--"LOGIN"
SELECT * FROM empleado;
SELECT * FROM cliente

alter table empleado rename column nombre to usuario
--en comun:
--cliente: usuario, pass, privilegio
--empleado: usuario, pass, privilegio

SELECT cliente.usuario, cliente.pass, cliente.privilegio
from cliente 
inner join empleado
on cliente.usuario = empleado.usuario

-------------Creo una tabla usuarios
Create table usuario ();
------------No seria logico


SELECT usuario, pass, privilegio FROM empleado 
UNION
-- union all
SELECT usuario, pass, privilegio FROM cliente

SELECT privilegio from empleado where usuario = 'Luzmi' and pass = '123'
union 
select usuario, pass, privilegio from cliente