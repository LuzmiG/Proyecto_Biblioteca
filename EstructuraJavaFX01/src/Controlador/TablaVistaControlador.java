/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class TablaVistaControlador implements Initializable {

    @FXML
    private TableView<Persona> tblDatos;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colEdad;
    @FXML
    private Button btnAgregar;
    
    private ObservableList<Persona> personas;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personas = FXCollections.observableArrayList();
        this.tblDatos.setItems(personas);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("Edad"));
    }    

    @FXML
    private void Agregar(ActionEvent event) {
        
        try {
            FXMLLoader nV = new FXMLLoader(getClass().getResource("/vista/formularioVista.FXML"));
            Parent root = nV.load();
            FormularioVistaControlador controlador = nV.getController();
            controlador.initAttributtes(personas);
            
            Scene escena = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(escena);
            stage.showAndWait();
            
            Persona p = controlador.getPersona();
            if (p != null) {
                this.personas.add(p);
                this.tblDatos.refresh();
                   
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(TablaVistaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        Persona p = this.tblDatos.getSelectionModel().getSelectedItem();
        
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("ERROR");
            alerta.setContentText("Debe seleccionar una persona");
            alerta.showAndWait();
    }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        
        Persona p = this.tblDatos.getSelectionModel().getSelectedItem();
        
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("ERROR");
            alerta.setContentText("Debe seleccionar una persona");
            alerta.showAndWait();
          }  
            else{
                    this.personas.remove(p);
                    this.tblDatos.refresh();
                    
                    
        
       
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("ERROR");
            alerta.setContentText("Debe seleccionar una persona");
            alerta.showAndWait();
 
    }
    
}
}
