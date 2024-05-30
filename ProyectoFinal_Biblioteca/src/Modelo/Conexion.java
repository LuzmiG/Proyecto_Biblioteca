/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
/**
 *
 * @author Luzmi
 */
public class Conexion {
    Connection conexion = null;
    String usuario = "cabolljn";
    String contrasenia = "aNp-3vnig7FXoFBRcCySGf56uFv8jYcP";
    String bd = "cabolljn";
    String ip = "fanny.db.elephantsql.com";
    String puerto = "5432";
    
    //Creo la URL
    String url = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    
    public Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasenia);
           
       //     System.out.println("CONEXION EXTIOSA");
    
        } catch (Exception e) {
              Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error en la conexion");
            alerta.setHeaderText(null);
            alerta.setContentText(e.toString());
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();

        //    System.out.println("ERROR EN LA CONEXION");
        }
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    //------------ESTE METODO LO HICE POR EL ERRO TOO MANY USER TO DATA BASE O algo asi decia el error
    /*Pero era porque no se podia conectar varias veces, estonces hice el meto cerrar*/
    public void cerrarConexion() {
    try {
        
            conexion.close();
          //  alertaAfrimativa("Se cerro conexion");
        
    } catch (SQLException e) {
        // Manejo de excepciones
        alertaNegativa("Error" +  e.toString());
    }
}
    
    public void alertaAfrimativa(String mensaje){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXITO");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
    
    public void alertaNegativa(String mensaje){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        
    }
}
