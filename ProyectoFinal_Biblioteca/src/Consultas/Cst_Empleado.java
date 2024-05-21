/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.Devolucion_prestamo;
import Modelo.Empleado;
import Modelo.Libro;
import Modelo.Prestamo_libros;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.StageStyle;

/**
 *
 * @author Luzmi
 */
public class Cst_Empleado extends Prestamo_libros{
    //variable mpara establecer conexion
    private Conexion fabricaConexion;
   
    
    //Constructor
    public Cst_Empleado(){
        this.fabricaConexion = new Conexion();
    }
    
    /*--------------EMPLEADOS-------------*/
    
    public void mostrarEmpleados(ArrayList<Empleado> listaEmpleados){
        try {
            
            Connection conexion = this.fabricaConexion.getConexion();
            String sql = "SELECT id_empleado, usuario, pass, sueldo, horario from empleado";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet datos = sentencia.executeQuery();
            while(datos.next()){
                Empleado ep = new Empleado();
                ep.setId_empleado(datos.getInt(1));
                ep.setNombre(datos.getString(2));
                ep.setPass(datos.getString(3));
                ep.setSueldo(datos.getFloat(4));
                ep.setHorario(datos.getString(5));
            
                listaEmpleados.add(ep);
                
            }
            
            sentencia.close();
            datos.close();
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error a mostrar la tabla de empleados" + e.toString());
            
        }
    }
    
    public void agregarEmpleado(Empleado CRegistran){
         try {
            //Establezco Conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //establezco/declaro consulta para sql
            String sql = "INSERT INTO empleado (usuario, pass, privilegio, sueldo, horario)" 
			+ "VALUES (?, ?, ?, ?, ?)";
            //Pre-Consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //Pas los valores a mis ?
            sentencia.setString(1, CRegistran.getNombre());
            sentencia.setString(2, CRegistran.getPass());
            sentencia.setString(3, CRegistran.getPrivilegio());
            sentencia.setFloat(4, CRegistran.getSueldo());
            sentencia.setString(5, CRegistran.getHorario());
        
            //Prosesa la consulta
            sentencia.executeUpdate();
            //Si es correcto manda su mensaje
            fabricaConexion.alertaAfrimativa("El Usuario se registro Correctamente");
            sentencia.close();
            
        }
         catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al Registrar " + e);
            
        }
    } 
  
    public void editarEmpleado(Empleado CEdita){
        try {
            //Establezcon Conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Consulta
            String sql = "UPDATE empleado SET usuario=?, pass=?, sueldo=?, horario=?"+
                    "WHERE id_empleado=?";
            //Pre-consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //Pas los valores a mis ?
            sentencia.setString(1, CEdita.getNombre());
            sentencia.setString(2, CEdita.getPass());
            sentencia.setFloat(3, CEdita.getSueldo());
            sentencia.setString(4, CEdita.getHorario());
     
            
            //isbn
            sentencia.setInt(5,CEdita.getId_empleado());
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
    
    public void eliminarLibro (int idEmpleado){
        try {

            
            //alerta
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMACION");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente desea Eliminar al Empleado ");
            alert.initStyle(StageStyle.UTILITY);
            
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == ButtonType.OK) {
                            //establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //consulta
            String sql = "DELETE FROM empleado WHERE id_empleado=?";
            //Pre consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //asino mi valor a ?
            sentencia.setInt(1, idEmpleado);
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
    
    
    
    /* ---------- PRESTAMOS ------------------*/
      
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
                dv.setNombreCliente(datos.getString(8));
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
    
     
     /*-----------CLIENTES-----------------*/
        public void mostrarClientes(ArrayList<Cliente> listaClientes){
         try {
            //Establezco mi conexion
            Connection conexion = this.fabricaConexion.getConexion();
            //Defino mi consulta
            String sql = "select id_cliente, nombre, usuario, pass, telefono, correo from cliente";
            //Pre-consulta
            //Preparo al consulta, como decri okis es valido tu parametros de //consulta
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            //obtengo los resultados
            ResultSet datos = sentencia.executeQuery();
       
            //Mi while me ayuda a recorre las FILAS // de lo que datos leidos en resultSet //
            while(datos.next()){
                //next avanza al siguiente registro
                
                //instancio mi objeto
                Cliente cl = new Cliente();
                
                 //con mis Set coloco lo que esta capturado en mi objeto DEPUES LE DIGO (okey pero lo que esta en esa columno solo puede ser presentado por un INT/String, y ya depsue se le asigna el numero o indice de columna
               //Es deci con mis SetID coloco lo que esta capturado en mi objeto alumn y en numero de indice q que es mi columna 
               
                             // se coloca en columna 1
               cl.setId(datos.getInt(1));
               cl.setNombre(datos.getString(2));
               cl.setUsuario(datos.getString(3));
               cl.setPass(datos.getString(4));
               cl.setTelefono(datos.getInt(5));
               cl.setCorreo(datos.getString(6));
               
                
                listaClientes.add(cl);
            }
                datos.close();
                sentencia.close();
            
            
        } catch (SQLException e) {
            fabricaConexion.alertaNegativa("Hubo un error al mostrar la Lista de Libros" + e.toString());
        }
    }
        
        public void serializarTablaCliente(ArrayList<Cliente> listaCliente){
        Path ruta = Paths.get("Lista de Clientes.txt");
        try{
            OutputStream flujoSalida = Files.newOutputStream(ruta);
            ObjectOutputStream objSalida = new ObjectOutputStream(flujoSalida);
            
            objSalida.writeObject(listaCliente);
            //System.out.println("Se exportaron correctamente");
            fabricaConexion.alertaAfrimativa("Se exportaron correctamente, su Lista de  Clientes");
            objSalida.close();
        }
        catch(IOException e){
           fabricaConexion.alertaNegativa("Hubo un error al exportar los datos" + e);
        }
    
    }
        
      public ArrayList<Cliente> deserializarTablaCliente() {
    ArrayList<Cliente> listaCliente = new ArrayList<>();
    Path ruta = Paths.get("Lista de Clientes.txt");

    try {
        InputStream flujoEntrada = Files.newInputStream(ruta);
        ObjectInputStream objEntrada = new ObjectInputStream(flujoEntrada);

        listaCliente = (ArrayList<Cliente>) objEntrada.readObject();
        objEntrada.close();
    } catch (IOException | ClassNotFoundException e) {
        // Maneja las excepciones adecuadamente
        e.printStackTrace();
    }

    return listaCliente;
}

}
