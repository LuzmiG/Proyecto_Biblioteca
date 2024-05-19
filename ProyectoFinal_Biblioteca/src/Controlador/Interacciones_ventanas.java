/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Conexion;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static proyectofinal_biblioteca.ProyectoFinal_Biblioteca.cerraVentana;
import static proyectofinal_biblioteca.ProyectoFinal_Biblioteca.ventanaActual;

/**
 *
 * @author Luzmi
 */
public class Interacciones_ventanas {
    
    Stage stage = new Stage();
    
     private Conexion fabricaConexion;
     
     public Interacciones_ventanas(){
         this.fabricaConexion = new Conexion ();
     }
    
    public void ventanaLogin () throws IOException{
       
        Parent vistaLogin = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vista/Login.FXML")));   
        Scene escena = new Scene(vistaLogin);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(escena);
        stage.show();
        
        cerraVentana(ventanaActual); 
        fabricaConexion.cerrarConexion();
    }
    
    public void ventanaRegistro() throws IOException{
         fabricaConexion.cerrarConexion();
         Parent vistaRegistro = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vista/Registro.FXML")));   
        Scene escena = new Scene(vistaRegistro);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(escena);
        stage.show();
        cerraVentana(ventanaActual); 
        fabricaConexion.cerrarConexion();
    }
    
    public void ventanaEmpleado() throws IOException{
        fabricaConexion.cerrarConexion();
        Parent vistaEmpleado = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vista/Empleado.FXML")));   
        Scene escena = new Scene(vistaEmpleado);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(escena);
        stage.show();
        cerraVentana(ventanaActual); 
        fabricaConexion.cerrarConexion();
    }
    
    /*
    Ya no es un public void porque no retorna nada
    y yo necesito que me retorne mi id y mi nombre de usuario por eso es que ahora es public  y de tipo controller
    que es la 
    */
    public ClienteController ventanaCliente() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Cliente.FXML"));
    Parent vistaCliente = loader.load();
    ClienteController clienteController = loader.getController();
    
    Scene escena = new Scene(vistaCliente);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(escena);
    stage.show();
    cerraVentana(ventanaActual); 
    fabricaConexion.cerrarConexion();
    
    return clienteController;
}
    
    /*public void ventanaCliente() throws IOException{
        Parent vistaCliente = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/vista/Cliente.FXML")));   
        Scene escena = new Scene(vistaCliente);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(escena);
        stage.show();
       // cerraVentana(ventanaActual); 
      fabricaConexion.cerrarConexion();
    }*/
}
