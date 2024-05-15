/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Verificacion_Usuario;
import Modelo.Cliente;
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

    private Cliente opcContex;

    
    private Conexion fabricaConexion;
    

    
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
        //Porque cuando se inicia hace una conexion, es decir hay un usario conectando, por eso cierrro y ya verrificar abre pero ya en su clase 
        
       fabricaConexion.cerrarConexion();
        Verificacion_Usuario v = new Verificacion_Usuario();
        String usuario = txtUsuario.getText();
        String pass = txtPass.getText();
        v.verificar(usuario, pass);
        fabricaConexion.cerrarConexion();

       

        
       /* Cliente cl = new Cliente();
        String u = cl.getNombre();
        verificar.capturarId(u);
        lbl_idUsuario.setText(u);
       String index = u;
      opcContex = index.s*/

      /* int id = opcContex.getId();
        String txtoid = Integer.toString(id);
       // lbl_idUsuario.setText(txtoid);
        
       // lbl_idUsuario.setText(usuario);
        //verificar.capturarId(usuario);
     
       */
    }

    @FXML
    private void lblRegistar(MouseEvent event) throws IOException {
       Interacciones_ventanas Iv = new Interacciones_ventanas();
        Iv.ventanaRegistro();
       fabricaConexion.cerrarConexion();
    }

    
}
