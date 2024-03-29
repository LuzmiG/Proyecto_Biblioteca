/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package estructurajavafx01;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Luzmi
 */
public class EstructuraJavaFX01 extends Application {
    
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EstructuraJavaFX01.class.getResource("/vista/tablaVista.fxml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }
        catch  (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
