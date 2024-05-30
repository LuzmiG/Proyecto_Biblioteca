/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.StageStyle;

/**
 *
 * @author Luzmi
 */
public class Cst_Cliente {
    
    //variable mpara establecer conexion
    private Conexion fabricaConexion;
   
    
    //Constructor
    public Cst_Cliente(){
        this.fabricaConexion = new Conexion();
    }
    
    
 //---------Mostrar los datos del libro en la tabla
    public void mostrarLibros(ArrayList<Libro> listaLibros){
         try {
            //Establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Defino mi consulta
            String sql = "SELECT * FROM libro";
            //Pre-consulta
            //Preparo al consulta, como decri okis es valido tu parametros de //consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //obtengo los resultados
            ResultSet datos = sentencia.executeQuery();
       
            //Mi while me ayuda a recorre las FILAS // de lo que datos leidos en resultSet //
            while(datos.next()){
                //next avanza al siguiente registro
                
                //instancio mi objeto
                Libro lb = new Libro();
                
                 //con mis Set coloco lo que esta capturado en mi objeto DEPUES LE DIGO (okey pero lo que esta en esa columno solo puede ser presentado por un INT/String, y ya depsue se le asigna el numero o indice de columna
               //Es deci con mis SetID coloco lo que esta capturado en mi objeto alumn y en numero de indice q que es mi columna 
               
                             // se coloca en columna 1
               lb.setIsbn(datos.getInt(1));
                             // se coloca en columna 2
                lb.setTitulo(datos.getString(2));
                            // se coloca en columna 3 y asi sucesivamente
                lb.setAutor(datos.getString(3));
                lb.setAnioPublicacion(datos.getInt(4));
                lb.setEditorial(datos.getString(5));
                lb.setDescripcion(datos.getString(6));
                lb.setCantidadDisponible(datos.getInt(7));
                lb.setCategoria(datos.getString(8));
                
                listaLibros.add(lb);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Libros" + e.toString());
        }
    }
    
    public void registrarPrestamo(Prestamo_libros CPrestan){
        try {
            //Establezco conexxio
            Connection conexion = fabricaConexion.getConexion();
            
            //Verifico si tiene pendientes 
            
            
            String sqlVpendiente = "SELECT COUNT(*) AS pendientes FROM prestamo_libro WHERE id_cliente = ? AND estado = 'pendiente'";
            PreparedStatement sentenciaVerificarPrestamosPendientes = conexion.prepareStatement(sqlVpendiente);
            sentenciaVerificarPrestamosPendientes.setInt(1, CPrestan.getId_cliente());
            ResultSet resultadoPendientes = sentenciaVerificarPrestamosPendientes.executeQuery();
            
            if (resultadoPendientes.next() && resultadoPendientes.getInt("pendientes") > 0) {
                // El usuario tiene préstamos pendientes
                fabricaConexion.alertaNegativa("El usuario tiene préstamos pendientes y no puede realizar un nuevo préstamo hasta devolverlos.");
                resultadoPendientes.close();
                sentenciaVerificarPrestamosPendientes.close();
                return;
            }
            
            //Verifico si hay libros
            String sqlVerificarCantidad = "SELECT cantidadDisponible FROM libro WHERE isbn = ?";
            PreparedStatement sentenciaVerificarCantidad = conexion.prepareStatement(sqlVerificarCantidad);
            sentenciaVerificarCantidad.setInt(1, CPrestan.getIsbn());
            ResultSet resultado = sentenciaVerificarCantidad.executeQuery();


            if (resultado.next()) {
                 int cantidadDisponible = resultado.getInt("cantidadDisponible");
                 if(cantidadDisponible > 0){
       
            String sql = "INSERT INTO prestamo_libro (fecha, periodoPrestamo, isbn, id_cliente, estado)"             
                    + "VALUES (?, ?, ?, ?, ?)";
            //Ejecutar la consulta       
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, CPrestan.getFecha_prestamo());
            sentencia.setString(2, CPrestan.getPeriodo_Prestamo());
            sentencia.setInt(3, CPrestan.getIsbn());
            sentencia.setInt(4, CPrestan.getId_cliente()); 
                                        //probando algo diferente para no crear el get y set a ver que tal//
            sentencia.setString(5, "pendiente");
             //Prosesa la consulta
            sentencia.executeUpdate();
            //fabricaConexion.alertaAfrimativa("Se registro correctamente El prestamo");
            sentencia.close();
            //insert into prestamo_libro (fecha, periodoPrestamo, isbn, id_cliente) 
					//values('06/05/2024', '3 Dias', 1, 1)/
                                        
                                        
            //----------------------------------
            //consulta para actulizar cantidad libros
            
            String sqlActulizar = "UPDATE libro SET cantidadDisponible = cantidadDisponible -1 WHERE isbn = ?";
            PreparedStatement sentenciaActualizarInventario = conexion.prepareStatement(sqlActulizar);
            sentenciaActualizarInventario.setInt(1, CPrestan.getIsbn());
            sentenciaActualizarInventario.executeUpdate();
            sentenciaActualizarInventario.close();  
            
            
                  fabricaConexion.alertaAfrimativa("Se registró correctamente el préstamo");
                 }
                 
                 else{
                fabricaConexion.alertaNegativa("Por el momento no contamos con mas ejemplares\n lo sentimos no podemos hacer el prestamo");
            }   
                 
            }
         
            else{
                  fabricaConexion.alertaNegativa("El libro con el ISBN especificado no existe");
                    }
            
            
               sentenciaVerificarCantidad.close();
               resultado.close(); 
                                    
                                        
        } catch (SQLException e) {
        fabricaConexion.alertaNegativa("Hubo un error al Registrar su Prestamo" + e);
        }

        
    }
    
      public void mostrarHistorial(ArrayList<Prestamo_libros> listaPrestamo, int idCliente){
      try {
            //Establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Defino mi consulta
          String sql = "SELECT prestamo_libro.id_prestamo, libro.isbn, libro.titulo, prestamo_libro.fecha, prestamo_libro.estado, "
                  + "cliente.nombre "
                  + "FROM prestamo_libro "
                  + "JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente "
                  + "LEFT JOIN devolucion_prestamo ON devolucion_prestamo.id_prestamo = prestamo_libro.id_prestamo "
                  + "JOIN libro ON libro.isbn = prestamo_libro.isbn "
                  + "WHERE cliente.id_cliente = ?";
          //Pre-consulta
            //Preparo al consulta, como decri okis es valido tu parametros de //consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idCliente);

            //obtengo los resultados
            ResultSet datos = sentencia.executeQuery();
       
            //Mi while me ayuda a recorre las FILAS // de lo que datos leidos en resultSet //
            while(datos.next()){
                //next avanza al siguiente registro
                
                //instancio mi objeto
                Prestamo_libros pl = new Prestamo_libros();
                
                 //con mis Set coloco lo que esta capturado en mi objeto DEPUES LE DIGO (okey pero lo que esta en esa columno solo puede ser presentado por un INT/String, y ya depsue se le asigna el numero o indice de columna
               //Es deci con mis SetID coloco lo que esta capturado en mi objeto alumn y en numero de indice q que es mi columna 
               
                             // se coloca en columna 1
               pl.setId_prestamo(datos.getInt(1));
                             // se coloca en columna 2
                pl.setIsbn(datos.getInt(2));
                pl.setTitulo(datos.getString(3));
                            // se coloca en columna 3 y asi sucesivamente
                pl.setFecha_prestamo(datos.getString(4));
                pl.setEstado(datos.getString(5));
               // pl.setNombreCliente(datos.getString(6));
                pl.setNombreCliente(datos.getString(6));
                            
                
                listaPrestamo.add(pl);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Libros" + e.toString());
        }
          
      
   
      }
      
      
      //retorna el ciente para mostrarlo en editar 
      public Cliente retornarCliente(int idCliente) {
        Cliente cliente = null;
        try {
            Connection conexion = this.fabricaConexion.getConexion();
            String sql = "SELECT usuario, pass, telefono, correo, nombre FROM cliente WHERE id_cliente = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idCliente);
            
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setUsuario(resultado.getString("usuario"));
                cliente.setPass(resultado.getString("pass"));
                cliente.setTelefono(resultado.getInt("telefono"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setId(idCliente);
            }
            resultado.close();
            sentencia.close();
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Error al obtener cliente: " + e);
        }
        return cliente;
      }

      
          
      
      
      public void editarCliente(Cliente CEdita){
        try {
            //Establezcon Conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Consulta
            String sql = "UPDATE cliente SET usuario=?, pass=?, telefono=?, correo=?, nombre=? "+
                    "WHERE id_cliente=?";
            //Pre-consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //Pas los valores a mis ?
            sentencia.setString(1, CEdita.getUsuario());
            sentencia.setString(2, CEdita.getPass());
            sentencia.setInt(3, CEdita.getTelefono());
            sentencia.setString(4, CEdita.getCorreo());
            sentencia.setString(5, CEdita.getNombre());
            
            
            //isbn
            sentencia.setInt(6,CEdita.getId());
            
            
            //Prosesa la consulta
            sentencia.executeUpdate();
            //Cierro
            sentencia.close();
            //afirmacion
            fabricaConexion.alertaAfrimativa("Se edito Correctamente");
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un erro al momento de editar " + e);
        }
    }
      
      
}
