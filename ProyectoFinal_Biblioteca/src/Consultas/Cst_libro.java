/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.Libro;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
/**
 *
 * @author Luzmi
 */
public class Cst_libro {
    
    //variable mpara establecer conexion
    private Conexion fabricaConexion;
   
    
   
    //Constructor
    public Cst_libro(){
        this.fabricaConexion = new Conexion();
    }
    
    public void registrarLibro(Libro CRegistran){
        try {
            //Establezco Conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //establezco/declaro consulta para sql
            String sql = "INSERT INTO libro (titulo, autor, anioPublicacion, editorial, descripcion, cantidadDisponible, categoria)" 
			+ "VALUES (?, ?, ?, ?, ?, ?,?)";
            //Pre-Consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //Pas los valores a mis ?
            sentencia.setString(1, CRegistran.getTitulo());
            sentencia.setString(2, CRegistran.getAutor());
            sentencia.setInt(3, CRegistran.getAnioPublicacion());
            sentencia.setString(4, CRegistran.getEditorial());
            sentencia.setString(5, CRegistran.getDescripcion());
            sentencia.setInt(6, CRegistran.getCantidadDisponible());
            sentencia.setString(7, CRegistran.getCategoria());
            //Prosesa la consulta
            sentencia.executeUpdate();
            
            Stack<String> pila = new Stack<>();
            pila.push(sql);
         //   fabricaConexion.alertaAfrimativa("El Libro se registro Correctamente");
            sentencia.close();
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al Registrar " + e);
            
        }
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
    
    
    
    public void editarLibro(Libro CEdita){
        try {
            //Establezcon Conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Consulta
            String sql = "UPDATE libro SET titulo=?, autor=?, anioPublicacion=?, editorial=?, descripcion=?, cantidadDisponible=?, categoria=?"+
                    "WHERE isbn=?";
            //Pre-consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //Pas los valores a mis ?
            sentencia.setString(1, CEdita.getTitulo());
            sentencia.setString(2, CEdita.getAutor());
            sentencia.setInt(3, CEdita.getAnioPublicacion());
            sentencia.setString(4, CEdita.getEditorial());
            sentencia.setString(5, CEdita.getDescripcion());
            sentencia.setInt(6, CEdita.getCantidadDisponible());
            sentencia.setString(7, CEdita.getCategoria());
            
            //isbn
            sentencia.setInt(8,CEdita.getIsbn());
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
    
    public void eliminarLibro (int isbn){
        try {

            
            //alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMACION");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente desea Eliminar al Alumno ");
            alert.initStyle(StageStyle.UTILITY);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                            //establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //consulta
            String sql = "DELETE FROM libro WHERE isbn=?";
            //Pre consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //asino mi valor a ?
            sentencia.setInt(1, isbn);
            //ejectu la consulta
            sentencia.executeUpdate();
            //cierro
            sentencia.close();
            
                fabricaConexion.alertaAfrimativa("Se elimino Correctamente");

            } else {
                
                fabricaConexion.alertaNegativa("No se puede eliminar ");

            }
        
        
        } catch (SQLException e) {
        fabricaConexion.alertaNegativa("Hubo un erro al momento de Eliminar " + e);


        }
        
    }
    
    public void serializarTabla(ArrayList<Libro> listaLibros){
        Path ruta = Paths.get("Lista de Libros.txt");
        try{
            OutputStream flujoSalida = Files.newOutputStream(ruta);
            ObjectOutputStream objSalida = new ObjectOutputStream(flujoSalida);
            
            objSalida.writeObject(listaLibros);
            //System.out.println("Se exportaron correctamente");
           
            fabricaConexion.alertaAfrimativa("Se exportaron correctamente --LIBROS--");
            objSalida.close();
        }
        catch(IOException e){
           fabricaConexion.alertaNegativa("Hubo un error al exportar los datos" + e);
        }
    
    }
    
       public ArrayList<Libro> deserializarTablaLibros() {
    ArrayList<Libro> listaLibro = new ArrayList<>();
    Path ruta = Paths.get("Lista de Libros.txt");

    try {
        InputStream flujoEntrada = Files.newInputStream(ruta);
        ObjectInputStream objEntrada = new ObjectInputStream(flujoEntrada);

        listaLibro = (ArrayList<Libro>) objEntrada.readObject();
        objEntrada.close();
        fabricaConexion.alertaAfrimativa("Se Importo Correctamente");
    } catch (IOException | ClassNotFoundException e) {
        // Maneja las excepciones adecuadamente
        e.printStackTrace();
        fabricaConexion.alertaNegativa("Hubo un error al momento de importar" + e);
    }

    return listaLibro;
}
    
  public void reemplazarLibros(ArrayList<Libro> libros) {
    try {
        // Establecer la conexión
        Connection conexion = this.fabricaConexion.getConexion();
        
        // Preparar una consulta para verificar la existencia de un libro por su ISBN
        String sqlVerificar = "SELECT COUNT(*) FROM libro WHERE isbn = ?";
        PreparedStatement sentenciaVerificar = conexion.prepareStatement(sqlVerificar);
        
        // Preparar consultas para insertar y actualizar libros
        String sqlInsertar = "INSERT INTO libro (isbn, titulo, autor, anioPublicacion, editorial, descripcion, cantidadDisponible, categoria)" 
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement sentenciaInsertar = conexion.prepareStatement(sqlInsertar);
        
        String sqlActualizar = "UPDATE libro SET titulo = ?, autor = ?, anioPublicacion = ?, editorial = ?, descripcion = ?, cantidadDisponible = ?, categoria = ? WHERE isbn = ?";
        PreparedStatement sentenciaActualizar = conexion.prepareStatement(sqlActualizar);
        
        // Iterar sobre cada libro importado
        for (Libro libro : libros) {
            // Verificar si el libro ya existe en la base de datos
            sentenciaVerificar.setInt(1, libro.getIsbn());
            ResultSet resultado = sentenciaVerificar.executeQuery();
            resultado.next();
            int count = resultado.getInt(1);
            
            if (count > 0) {
                // El libro ya existe, actualizar sus datos
                sentenciaActualizar.setString(1, libro.getTitulo());
                sentenciaActualizar.setString(2, libro.getAutor());
                sentenciaActualizar.setInt(3, libro.getAnioPublicacion());
                sentenciaActualizar.setString(4, libro.getEditorial());
                sentenciaActualizar.setString(5, libro.getDescripcion());
                sentenciaActualizar.setInt(6, libro.getCantidadDisponible());
                sentenciaActualizar.setString(7, libro.getCategoria());
                sentenciaActualizar.setInt(8, libro.getIsbn());
                sentenciaActualizar.executeUpdate();
            } else {
                // El libro no existe, insertarlo como un nuevo registro
                sentenciaInsertar.setInt(1, libro.getIsbn());
                sentenciaInsertar.setString(2, libro.getTitulo());
                sentenciaInsertar.setString(3, libro.getAutor());
                sentenciaInsertar.setInt(4, libro.getAnioPublicacion());
                sentenciaInsertar.setString(5, libro.getEditorial());
                sentenciaInsertar.setString(6, libro.getDescripcion());
                sentenciaInsertar.setInt(7, libro.getCantidadDisponible());
                sentenciaInsertar.setString(8, libro.getCategoria());
                sentenciaInsertar.executeUpdate();
            }
        }
        
        // Cerrar la conexión
        //conexion.close();
        
        // Si es correcto, enviar mensaje de éxito
        fabricaConexion.alertaAfrimativa("Los libros se importaron correctamente y se reemplazaron en la base de datos.");
    } catch (SQLException e) {
        fabricaConexion.alertaNegativa("Hubo un error al reemplazar los libros en la base de datos: " + e);
    }
}

    

  
}
    
   
    
    

