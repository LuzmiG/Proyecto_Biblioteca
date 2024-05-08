/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal_biblioteca;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Luzmi
 */
public class ProyectoFinal_Biblioteca extends Application {

     @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ProyectoFinal_Biblioteca.class.getResource("/Vista/Login.fxml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        }
        catch  (IOException e){
            //Poner una alertar de error
            System.out.println(e.getMessage());
        }
    }
    
      public static void main(String[] args) {
       launch(args);
    }
 
    
}
