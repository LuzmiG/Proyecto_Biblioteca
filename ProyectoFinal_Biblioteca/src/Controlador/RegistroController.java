/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Registro_cliente;
import Consultas.Verificacion_Usuario;
import Modelo.Cliente;
import Modelo.Conexion;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static proyectofinal_biblioteca.ProyectoFinal_Biblioteca.cerraVentana;


/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class RegistroController implements Initializable {
    
  
    

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TextField txtNombre;
    
     //Se crea una variable consulta de tipo CONSULTA_aLUMNOS
    private Registro_cliente consulta;
    
    private Conexion fabricaConexion;

    Interacciones_ventanas Iv = new Interacciones_ventanas();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.consulta = new Registro_cliente();
        this.fabricaConexion = new Conexion ();
        
    }    

    @FXML
    private void lblRegresar(MouseEvent event) throws IOException {
       
       // fabricaConexion.cerrarConexion();
        Iv.ventanaLogin();
      // fabricaConexion.cerrarConexion();
    
    }

    @FXML
    private void btnRegistrar(ActionEvent event) throws IOException {
        if (!consulta.usuarioExiste(txtUsuario.getText())) {
        Cliente cl = new Cliente();
        
        cl.setUsuario(txtUsuario.getText());
        cl.setPass(txtPass.getText());
        cl.setTelefono(parseInt(txtTelefono.getText()));
        cl.setCorreo(txtCorreo.getText());
        cl.setNombre(txtNombre.getText());
        
        consulta.registrarCliente(cl); 
        //fabricaConexion.cerrarConexion();

        
       // capturarCliente(usuario);
        
            String usuario = txtUsuario.getText();
         
            Cliente cliente = capturarCliente(usuario);

            if (cliente != null) {
                ClienteController clienteController = Iv.ventanaCliente();
                clienteController.mostrarCredenciales(cliente.getId(), cliente.getNombre());
            } 
            else {
                System.out.println("Cliente no encontrado.");
            }
         limpiarCampos();
        }       
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error: Usuario");
            alerta.setHeaderText(null);
            alerta.setContentText("El USUARIO YA EXISTE\n Por Favor ingrese otro usuario");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();
          //  System.out.println("El usuario ya existe");
        }
        
    }
    
    public Cliente capturarCliente(String usuario) {
        Cliente cliente = new Cliente(); // Crear una instancia de Cliente
        try {
            Connection conectar = fabricaConexion.getConexion();
            String sql = "SELECT id_cliente, nombre FROM cliente WHERE usuario = ?";
            PreparedStatement sentencia = conectar.prepareStatement(sql);
            sentencia.setString(1, usuario);

            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                cliente.setId(datos.getInt("id_cliente"));
                cliente.setNombre(datos.getString("nombre"));

            }
            datos.close();
        } catch (SQLException ex) {
            Logger.getLogger(Verificacion_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente; // Devuelve el objeto Cliente con los datos
    }
    
     private void limpiarCampos() {
        txtUsuario.setText("");
        txtPass.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtNombre.setText("");
        
    }
    
}
