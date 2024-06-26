/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Modelo.Cliente;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
/**
 *
 * @author Luzmi
 */
public class Registro_cliente extends Conexion {
    
    private Conexion fabricaConexion;
    
    //Constructor

    public Registro_cliente() {
        this.fabricaConexion = new Conexion();
    }
                             //   Recibe mi modelo de Cliente
    public void registrarCliente(Cliente CRegistran){
        try{       
            //Se inicianla conexion     con el constructor (do you know that?)
            Connection conexion = this.fabricaConexion.getConexion();
                                                /*Campos a llenar                                 // indican que se proporcionaran valores especificos más adelante en el codigo.*/
            String sql = "INSERT INTO cliente (usuario, pass, privilegio, telefono, correo, nombre) VALUES (?, ?, ?, ?, ?, ?) ";
            /*Creo el objeto PreparedStatement      ejecuta la consulta de SQL */
            PreparedStatement sentencia = conexion.prepareStatement(sql);
         
            /*
            Ok,
            en la variable (sql) donde hive la consulta le asigne a los valores ? ¿ok?
            Ton´s aqui es donde le asignaré los valores a esos ? y en orden 
            */
                         //Posicion donde se va ubicar
                                            //se le asigna el valor que se capturo/ingreso el usario //            
            sentencia.setString(1, CRegistran.getUsuario());
                            //parametros que va remplar a ?
                                            // valor que le estoy dando en el sengundo parametro
            sentencia.setString(2, CRegistran.getPass());
            sentencia.setString(3, CRegistran.getPrivilegio());
            sentencia.setInt(4, CRegistran.getTelefono());
            sentencia.setString(5, CRegistran.getCorreo());
            sentencia.setString(6, CRegistran.getNombre());
            
       
            //ejecuta la consulta (sql (insert into)) que se defini previamente (como que la procesa)
            //SI OCURRAR UN ERROR MANDA A UN ERROR
            sentencia.executeUpdate();
            alertaAfrimativa("Se creo nuevo usuario: "+ CRegistran.getUsuario() +"\n Su Contraseña es: " + CRegistran.getPass());
           // fabricaConexion.alertaAfrimativa("Felicidades! Se registro correctamente");
            sentencia.close();
            fabricaConexion.cerrarConexion();
        }
        catch(Exception e){
            fabricaConexion.alertaNegativa("ERROR" + e.getMessage());
            
        }
    }
    
    public boolean usuarioExiste(String usuario) {
        boolean existe = false;
        try {
            Connection conexion = this.fabricaConexion.getConexion();

            PreparedStatement sentencia = conexion.prepareStatement("SELECT count(*) FROM cliente WHERE usuario = ?");
            sentencia.setString(1, usuario);
            ResultSet rs = sentencia.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                existe = count > 0;
            }
            rs.close();
            sentencia.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return existe;
}
    
  
    @Override
   public void alertaAfrimativa(String mensaje){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXITO");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
}
