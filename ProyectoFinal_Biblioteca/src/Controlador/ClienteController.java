/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Cliente;
import Consultas.Cst_libro;
import Consultas.Verificacion_Usuario;
import Modelo.Cliente;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

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

    @FXML
    private ComboBox<String> cbxDias;
    @FXML
    private Label lbl_fecha;
    @FXML
    private Button btnPrestamo;
    @FXML
    private Label lbl_regresar;
     

    /**
     * lbl_titulo
     * lbl_autor
     * lbl_anioPubli
     * lbl_editorial
     * lbl_descripcion
     * lbl_categoria
     * 
     * -----
     * lbl_fecha
     * cbxDias
     * lbl_ISBN
     * lbl_idUsuario
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //conexiion
         this.consulta = new Cst_Cliente();
       //  consulta.
         //ver los datos de la tabla
         cargarLibros();
        
         //---------------------------------
        // Combo Box
        String [] dias ={"3 Dias", "7 dias", "15 dias", "30 dias"}; 
        ObservableList<String> items = FXCollections.observableArrayList(dias);
        cbxDias.setItems(items);
        
        //-----------------------------------
        //Fecha
        LocalDate fecha = LocalDate.now();
        //transformar la fecha en año/mes/dia
        String transFecha = fecha.toString();
        lbl_fecha.setText(transFecha);
    
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
        /*asi es como regularmente lo hice para convertirlo a String pero 
        ahora lo intentare con valuOf
        int isbn = opcContex.getIsbn();
        String txtoisbn = Integer.toString(isbn);
        lbl_ISBN.setText(txtoisbn);
        */
        lbl_ISBN.setText(String.valueOf(opcContex.getIsbn()));
        
        
        lbl_titulo.setText(opcContex.getTitulo());
        lbl_autor.setText(opcContex.getAutor());
        
        lbl_anioPubli.setText(String.valueOf(opcContex.getAnioPublicacion()));
       /* int anioPublicacion = opcContex.getAnioPublicacion();
        String textoAnio = Integer.toString(anioPublicacion);
        lbl_anioPubli.setText(textoAnio);
        */
        lbl_editorial.setText(opcContex.getEditorial());
        lbl_descripcion.setText(opcContex.getDescripcion());

        lbl_can.setText(String.valueOf(opcContex.getCantidadDisponible()));

        lbl_categoria.setText(opcContex.getCategoria());
    }
    
    public void mostrarCredenciales(int id, String nombreCliente){
                            // Este método se utiliza para convertir diferentes tipos de datos en cadenas de caracteres
    lbl_idUsuario.setText(String.valueOf(id)); // Muestra el ID del cliente
    lbl_usuario.setText(nombreCliente); // Muestra el nombre del cliente
}

    @FXML
    private void btnPrestamo(ActionEvent event) {
    if(opcContex != null ){
         //Instancio a mi modelo
        Prestamo_libros pl = new Prestamo_libros();
        pl.setFecha_prestamo(lbl_fecha.getText());
        pl.setPeriodo_Prestamo(cbxDias.getSelectionModel().getSelectedItem());
        
        String textoISBN = lbl_ISBN.getText();
        int isbn = Integer.parseInt(textoISBN);
        pl.setIsbn(isbn);
        
        String textoIdCliente = lbl_idUsuario.getText();
        int idCliente = Integer.parseInt(textoIdCliente);
        pl.setId_cliente(idCliente);
        
   
        
        consulta.registrarPrestamo(pl);
        
        /*-------------------------
        String nomU = lbl_usuario.getText(); 
        String tituloL = lbl_titulo.getText();
        String dias = cbxDias.getSelectionModel().getSelectedItem();
                

        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de LIBRO");
        alert.setHeaderText(null);
        alert.setContentText( nomU + " Presto el Libro: " + tituloL+"\n Tiene un plazo de devolución de: " + dias );
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        //-------------------------
        */
        cargarLibros(); 
        limpiar();
    }
    else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("No hay ningun libro seleccionado");
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
        limpiar();
    }
        
       
    }
    
        public void limpiar() {
        lbl_ISBN.setText("----------------");
        lbl_titulo.setText("----------------");
        lbl_autor.setText("---------");
        lbl_anioPubli.setText("---------");
        lbl_editorial.setText("---------");
        lbl_descripcion.setText("---------");
        lbl_categoria.setText("---------");
        lbl_can.setText("-----------");
        tblLibros.getSelectionModel().clearSelection();
        cbxDias.getSelectionModel().select("Eliga cantidad de dias");
        
        

    }

    @FXML
    private void regresarLogin(MouseEvent event) throws IOException {
    Interacciones_ventanas Iv = new Interacciones_ventanas();
    Iv.ventanaLogin();
    
    }
}

 /**
  * 
  * lbl_titulo
     * lbl_autor
     * lbl_anioPubli
     * lbl_editorial
     * lbl_descripcion
     * lbl_categoria
    * lbl_fecha
     * cbxDias
     * lbl_ISBN
     * lbl_idUsuario
     */