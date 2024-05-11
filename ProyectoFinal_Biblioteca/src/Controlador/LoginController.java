/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Verificacion_Usuario;
import Modelo.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class LoginController implements Initializable {
    
    /*MANEJO DE VENTANAS*/
    Stage stageLogin = new Stage();

    @FXML
    private Button btnIngresar;
    @FXML
    private Label lblRegistrar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPass;

    
    private Conexion fabricaConexion;
    
    Interacciones_ventanas Iv = new Interacciones_ventanas();
    @FXML
    private Label lbl_idUsuario;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fabricaConexion = new Conexion();
       if(this.fabricaConexion.getConexion() != null){
           btnIngresar.setVisible(true);
           lblRegistrar.setVisible(true);
       }
       else{
           btnIngresar.setVisible(false);
           lblRegistrar.setVisible(false);
       }
        
    }    

    @FXML
    private void btnIngresar(ActionEvent event) throws IOException {
        Verificacion_Usuario verificar = new Verificacion_Usuario();
        String usuario = txtUsuario.getText();
        String pass = txtPass.getText();
        verificar.verificar(usuario, pass);
        verificar.capturarId(usuario);
        lbl_idUsuario.setText(usuario);
        //verificar.capturarId(usuario);
        
       
        
        
        
       
    }

    @FXML
    private void lblRegistar(MouseEvent event) throws IOException {
       Iv.ventanaRegistro();
    }

    
}
