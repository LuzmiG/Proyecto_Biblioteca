/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Verificacion_Usuario;
import Modelo.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnIngresar;
    @FXML
    private Label lblRegistrar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPass;

    
    private Conexion fabricaConexion;
    /**
     * Initializes the controller class.
     */
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
        
       
    }

    @FXML
    private void lblRegistar(MouseEvent event) {
    }
    
}
