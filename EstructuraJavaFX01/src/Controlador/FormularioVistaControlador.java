/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class FormularioVistaControlador implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalida;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;

    
    private Persona persona;
    
    private ObservableList<Persona> personas;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void initAttributtes(ObservableList<Persona> personas){
        this.personas = personas;
    }


    @FXML
    private void agregar(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String apellidos = this.txtApellidos.getText();
        int edad = Integer.parseInt(this.txtEdad.getText());
        
        Persona p= new Persona(nombre, apellidos, edad);
        
        if (!personas.contains(p)) {
            this.persona = p;
             Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setTitle("INFORMACIÓN");
            alerta.setContentText("Se ha añadido correctamente");
            alerta.showAndWait();
            
            Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("ERROR");
            alerta.setContentText("La persona ya existe");
            alerta.showAndWait();
            
        }
        
    }

    @FXML
    private void Salir(ActionEvent event) {
        this.persona = null;
         Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
            stage.close();
        
    }

    public Persona getPersona() {
        return persona;
    }
    
    
    
}
