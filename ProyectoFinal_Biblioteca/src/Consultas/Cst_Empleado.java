/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Conexion;
import Modelo.Devolucion_prestamo;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Luzmi
 */
public class Cst_Empleado {
    //variable mpara establecer conexion
    private Conexion fabricaConexion;
   
    
    //Constructor
    public Cst_Empleado(){
        this.fabricaConexion = new Conexion();
    }
    
    //---------Mostrar los datos del libro en la tabla
    public void mostrarPrestamos(ArrayList<Prestamo_libros> listaPrestamos){
         try {
            //Establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Defino mi consulta
            String sql = "SELECT id_prestamo, fecha, libro.isbn, libro.titulo, cliente.id_cliente, cliente.nombre, periodoprestamo, estado FROM prestamo_libro "
        + "INNER JOIN libro ON prestamo_libro.isbn = libro.isbn "
        + "INNER JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente";
   
            //Pre-consulta
            //Preparo al consulta, como decri okis es valido tu parametros de //consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //obtengo los resultados
            ResultSet datos = sentencia.executeQuery();
       
            //Mi while me ayuda a recorre las FILAS 
            while(datos.next()){
                //next avanza al siguiente registro
                
                Prestamo_libros pl = new Prestamo_libros();
                
                
                 //con mis Set coloco lo que esta capturado en mi objeto DEPUES LE DIGO  
               
                             // se coloca en columna 1
               pl.setId_prestamo(datos.getInt(1));
                             // se coloca en columna 2 y asi sucesivamente
                pl.setFecha_prestamo(datos.getString(2));
                pl.setIsbn(datos.getInt(3));
                pl.setTitulo(datos.getString(4));
                pl.setId_cliente(datos.getInt(5));
                pl.setNombreCliente(datos.getString(6));
                pl.setPeriodo_Prestamo(datos.getString(7));
                pl.setEstado(datos.getString(8));
        
                
                listaPrestamos.add(pl);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Prestamos" + e.toString());
        }
    }
    
     public Prestamo_libros obtenerPrestamoPorId(int id) {
        
         String sql = "SELECT prestamo_libro.id_prestamo, prestamo_libro.fecha, prestamo_libro.periodoprestamo, prestamo_libro.isbn, prestamo_libro.id_cliente,  cliente.nombre, libro.titulo "
                 + "FROM prestamo_libro "
                 + "INNER JOIN libro  ON prestamo_libro.isbn = libro.isbn "
                 + "INNER JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente "
                 + "WHERE prestamo_libro.id_prestamo = ?";
        Prestamo_libros prestamo = null;
        try (
                 Connection conexion = this.fabricaConexion.getConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            // Sólo debería haber un resultado
            if (rs.next()) {
                   prestamo = new Prestamo_libros();
                prestamo.setId_prestamo(rs.getInt("id_prestamo"));
                prestamo.setFecha_prestamo(rs.getString("fecha"));
                prestamo.setPeriodo_Prestamo(rs.getString("periodoprestamo"));
                prestamo.setIsbn(rs.getInt("isbn"));
                prestamo.setTitulo(rs.getString("titulo"));
                prestamo.setId_cliente(rs.getInt("id_cliente"));
                prestamo.setNombreCliente(rs.getString("nombre"));
            
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error" + e);
        }
        return prestamo;
        
        
        
        
        
        /*
        ----------------DEVOLUCIONES
        */
        
        
    }
    
     public void mostrarDevoluciones(ArrayList<Devolucion_prestamo> listaDevolucion){
         try {
           
            Connection conexion = this.fabricaConexion.getConexion();
            
             String sql = "SELECT devolucion_prestamo.id_devolucion, devolucion_prestamo.fecha, devolucion_prestamo.multa, devolucion_prestamo.descripcion_multa, prestamo_libro.id_prestamo, prestamo_libro.isbn,"
                     + " libro.titulo, cliente.nombre, empleado.usuario "
                     + "FROM devolucion_prestamo "
                     + "INNER JOIN prestamo_libro ON devolucion_prestamo.id_prestamo = prestamo_libro.id_prestamo "
                     + "INNER JOIN libro ON prestamo_libro.isbn = libro.isbn "
                     + "INNER JOIN cliente ON prestamo_libro.id_cliente = cliente.id_cliente "
                     + "INNER JOIN empleado ON devolucion_prestamo.id_empleado = empleado.id_empleado";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //obtengo los resultados
            ResultSet datos = sentencia.executeQuery();

            while(datos.next()){
                
                Devolucion_prestamo dv = new Devolucion_prestamo();
                             // se coloca en columna 1
               dv.setId_devolucion(datos.getInt(1));
                             // se coloca en columna 2 y asi sucesivamente
                dv.setFecha(datos.getString(2));
                dv.setMulta(datos.getInt(3));
                dv.setDescripcion_multa(datos.getString(4));
                dv.setId_prestamo(datos.getInt(5));
                dv.setIsbn(datos.getInt(6));
                dv.setTitulo(datos.getString(7));
                dv.setNombre_cl(datos.getString(8));
                dv.setNombre_emp(datos.getString(9));
                
        
                
                listaDevolucion.add(dv);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Devoluciones" + e.toString());
        }
    }
     
     public void registrarDevolucion(Devolucion_prestamo CDevuelve){
         try {
             Connection conexion = this.fabricaConexion.getConexion();
             String sql = "INSERT INTO devolucion_prestamo (fecha, multa, descripcion_multa, id_prestamo, id_empleado)"
                     + "VALUES (?, ?, ?, ?, ?)";
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             sentencia.setString(1, CDevuelve.getFecha());
             sentencia.setInt(2, CDevuelve.getMulta());
             sentencia.setString(3, CDevuelve.getDescripcion_multa());
             sentencia.setInt(4, CDevuelve.getId_prestamo());
             sentencia.setInt(5, CDevuelve.getId_empleado());
             sentencia.executeUpdate();
             sentencia.close();
             
             // Actualizar el estado del préstamo a 'devuelto'
             String sqlActualizarEstado = "UPDATE prestamo_libro SET estado = 'devuelto' WHERE id_prestamo = ?";
             PreparedStatement sentenciaActualizarEstado = conexion.prepareStatement(sqlActualizarEstado);
             sentenciaActualizarEstado.setInt(1, CDevuelve.getId_prestamo());
             sentenciaActualizarEstado.executeUpdate();
             sentenciaActualizarEstado.close();
             
             
            ////---------Actualizar
            String sqlActulizar = "UPDATE libro SET cantidadDisponible = cantidadDisponible + 1 WHERE isbn = ?";
            PreparedStatement sentenciaActualizarInventario = conexion.prepareStatement(sqlActulizar);
            sentenciaActualizarInventario.setInt(1, CDevuelve.getIsbn());
            sentenciaActualizarInventario.executeUpdate();
            sentenciaActualizarInventario.close(); 
            
           
             
            sentenciaActualizarInventario.close();
                fabricaConexion.alertaAfrimativa("Se registro Correctame La devolucion");
         } 
         
         
         catch (Exception e) {
             fabricaConexion.alertaNegativa("hubo un errror" + e.toString());
         }
         
     }
     
     public void llenarComboEmpleado(ComboBox<String> comboBox) throws SQLException {
        try {
            Connection conexion = fabricaConexion.getConexion();
            String sql = "SELECT id_empleado FROM empleado";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()) {
                int id = datos.getInt("id_empleado");
                String idComoCadena = String.valueOf(id);
                comboBox.getItems().add(idComoCadena);
            }
            datos.close();
            sentencia.close();
        } catch (Exception e) {
            fabricaConexion.alertaNegativa("Hubo un error al cargar el ComboBox de empleados");
        }

    }
    
}
