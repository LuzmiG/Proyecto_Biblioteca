/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Conexion;
import Modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
