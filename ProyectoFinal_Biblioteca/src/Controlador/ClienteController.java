/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Cliente;
import Consultas.Cst_libro;
import Consultas.Verificacion_Usuario;
import Modelo.Libro;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class ClienteController implements Initializable {

    @FXML
    private Button btnLibros;
    @FXML
    private Button btnRecom;
    @FXML
    private AnchorPane pnl_Bienvenido;
    @FXML
    private AnchorPane pnl_VerLibro;
    @FXML
    private TableView<Libro> tblLibros;

    @FXML
    private AnchorPane pnl_recomendaciones;
    
     private Cst_Cliente consulta;
     //Opciones pARA MI CONTExt menu
    private Libro opcContex;
    @FXML
    private Label lbl_titulo;
    @FXML
    private Label lbl_autor;
    @FXML
    private Label lbl_anioPubli;
    @FXML
    private Label lbl_editorial;
    @FXML
    private Label lbl_descripcion;
    @FXML
    private Label lbl_categoria;
    @FXML
    private Label lbl_can;
    @FXML
    private Label lbl_usuario;
    @FXML
    private Label lbl_idUsuario;
    @FXML
    private Label lbl_ISBN;
     
    /**
     * lbl_titulo
     * lbl_autor
     * lbl_anioPubli
     * lbl_editorial
     * lbl_descripcion
     * lbl_categoria
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //conexiion
         this.consulta = new Cst_Cliente();
       //  consulta.
         //ver los datos de la tabla
         cargarLibros();
        
    }    

    
    
    @FXML
    public void cambiarForm(ActionEvent event) {
        if (event.getSource() == btnLibros) {
            pnl_VerLibro.setVisible(true);
            pnl_recomendaciones.setVisible(false);
            pnl_Bienvenido.setVisible(false);
        } else if (event.getSource() == btnRecom) {
            pnl_VerLibro.setVisible(false);
            pnl_recomendaciones.setVisible(true);
            pnl_Bienvenido.setVisible(false);
        } 
    }
    
    public void cargarLibros(){
         tblLibros.getItems().clear();
        tblLibros.getColumns().clear();
         //Creo un ArrayLIST DE tipo Libro
        ArrayList<Libro> lista = new ArrayList();
        //llamo a mi metodo mostrarLibro
        consulta.mostrarLibros(lista);
        // Creo un ObservableList a partir de la lista de libros
        ObservableList<Libro> datos = FXCollections.observableArrayList(lista);
        //Columna ID se crea una nueva comoluma de tabla
        //Encabezado de mi tabla
        
        
        //Columna Titulo
        TableColumn tituloCol = new TableColumn("TITULO");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
         //Columna Autor
        TableColumn autorCol = new TableColumn("AUTOR");
        autorCol.setCellValueFactory(new PropertyValueFactory("autor"));
         //categoria
        TableColumn categoriaCol = new TableColumn("CATEGORIA");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblLibros.setItems(datos);
        
        tblLibros.getColumns().addAll(tituloCol, autorCol, categoriaCol);
    }   
    
   

    @FXML
    private void seleccionarInfo(MouseEvent event) {
    
        int index = tblLibros.getSelectionModel().getSelectedIndex();
        opcContex = tblLibros.getItems().get(index);
        
        int isbn = opcContex.getIsbn();
        String txtoisbn = Integer.toString(isbn);
        lbl_ISBN.setText(txtoisbn);
        
        lbl_titulo.setText(opcContex.getTitulo());
        lbl_autor.setText(opcContex.getAutor());

        int anioPublicacion = opcContex.getAnioPublicacion();
        String textoAnio = Integer.toString(anioPublicacion);
        lbl_anioPubli.setText(textoAnio);

        lbl_editorial.setText(opcContex.getEditorial());
        lbl_descripcion.setText(opcContex.getDescripcion());

        int cantidad = opcContex.getCantidadDisponible();
        String txtCan = Integer.toString(cantidad);
        lbl_can.setText(txtCan);

        lbl_categoria.setText(opcContex.getCategoria());
    }
    
    
}

 /**
     * lbl_titulo
     * lbl_autor
     * lbl_anioPubli
     * lbl_editorial
     * lbl_descripcion
     * lbl_categoria
     */