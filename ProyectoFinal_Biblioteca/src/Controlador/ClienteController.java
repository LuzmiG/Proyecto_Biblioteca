/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class ClienteController implements Initializable {

    @FXML
    private Button btnForm_Agregar;
    @FXML
    private Button btnForm_Actualizar_Inventario;
    @FXML
    private Button btnFormRegistar_Devolucion;
    @FXML
    private AnchorPane pnl_AgregarLibro;
    @FXML
    private AnchorPane pnl_ActualizarInventario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnForm_Agregar(ActionEvent event) {
    }

    @FXML
    private void btnForm_Actualizar_Inventario(ActionEvent event) {
    }

    @FXML
    private void btnFormRegistar_Devolucion(ActionEvent event) {
    }
    
}
