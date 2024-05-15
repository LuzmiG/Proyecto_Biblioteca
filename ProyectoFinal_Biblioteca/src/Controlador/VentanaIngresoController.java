/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fxml;

import Controlador.Interacciones_ventanas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author iglesiadecristo
 */
public class VentanaIngresoController implements Initializable {

    @FXML
    private Button botoningresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void boton_ingresar(ActionEvent event) throws IOException {
        Interacciones_ventanas iv = new Interacciones_ventanas();
        iv.ventanaLogin();
    }
    
}
