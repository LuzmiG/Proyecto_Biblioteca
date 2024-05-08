/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
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
           
            /*if (conexion != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Conexion");
            alerta.setHeaderText(null);
            alerta.setContentText("Conexion exitosa");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();               
            }*/
            
        } catch (Exception e) {
              Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error en la conexion");
            alerta.setHeaderText(null);
            alerta.setContentText(e.toString());
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();

            
        }
    }
    
    public Connection getConexion() {
        return conexion;
    }
}
