/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Conexion;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Luzmi
 */
public class Cst_Empleado extends Libro {
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
            String sql = "SELECT id_prestamo, fecha, libro.isbn, libro.titulo, cliente.id_cliente, cliente.nombre, periodoprestamo FROM prestamo_libro "
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
        
                
                listaPrestamos.add(pl);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Prestamos" + e.toString());
        }
    }
    
     public Prestamo_libros obtenerPrestamoPorId(int id) {
        

        String sql = "SELECT id_prestamo, titulo_libro, nombre_usuario, fecha_prestamo, fecha_entrega, periodo_prestamo FROM prestamo_libro WHERE id = ?";
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
                prestamo.setFecha_prestamo(rs.getString("fecha_prestamo"));
                prestamo.setPeriodo_Prestamo(rs.getString("periodo_Prestamo"));
                prestamo.setIsbn(rs.getInt("isbn"));
                prestamo.setTitulo(rs.getString("titulo"));
                prestamo.setId_cliente(rs.getInt("id_cliente"));
                prestamo.setNombreCliente(rs.getString("nombreCliente"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return prestamo;
    }
    
    
    
}
