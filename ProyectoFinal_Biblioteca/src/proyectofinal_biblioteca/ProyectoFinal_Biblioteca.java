/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal_biblioteca;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Luzmi
 */
public class ProyectoFinal_Biblioteca extends Application {

     @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Creacion de primer commit en GIT");
        StackPane root = new StackPane();
        root.getChildren().add(label);
           
        Scene escena = new Scene(root, 300, 250);
      
        
        primaryStage.setScene(escena);
        primaryStage.show();
    }
    
      public static void main(String[] args) {
       launch(args);
    }
 
    
}
