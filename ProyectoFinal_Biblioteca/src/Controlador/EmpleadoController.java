/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Empleado;
import Consultas.Cst_libro;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class EmpleadoController implements Initializable {

    @FXML
    private Button btnForm_Actualizar_Inventario;
    @FXML
    private Button btnFormRegistar_Devolucion;
    @FXML
    private AnchorPane pnl_AgregarLibro;
    @FXML
    private AnchorPane pnl_ActualizarInventario;
    @FXML
    private Button btnForm_AgregarLibro;
    @FXML
    private AnchorPane pnl_devolucionLibro;
    @FXML
    private AnchorPane pnl_Bienvenido;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtAñoPublicacion;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtCantidad;
    @FXML
    private ComboBox<String> cbxCategoria;
    @FXML
    private Button btnGuardar;

    @FXML
    private TableView<Libro> tblLibros;

    @FXML
    private ContextMenu cmOpciones;

    private Cst_libro consulta;
    
    //Opciones pARA MI CONTExt menu
    private Libro opcContex;
    @FXML
    private Button btnExportar_libros;
    ///variabel para mostra en tablas y serializar
    ArrayList<Libro> lista = new ArrayList();
    ArrayList<Prestamo_libros> listaPrestamos = new ArrayList();
    
    
    @FXML
    private Label lbl_regresar;
    
    
    @FXML
    private TableView<Prestamo_libros> tblPrestamos;
    @FXML
    private ContextMenu cmOpciones1;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtIdPrestamo;
    @FXML
    private Label lblDatosPrestamo;
    
private Cst_Empleado consultaPrestamo;
    /**
     * txtTitulo txtAutor txtAñoPublicacion txtEditorial txtDescripcion
     * txtCantidad cbxCategoria
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //conexion
        this.consulta = new Cst_libro();
        
        //cargar tabla Libros
        cargarLibros();
        //cargar tablaPrestamos
        cargarPrestamos();
        //---------------------------------
        // Combo Box
        String[] categorias = {"Romance", "Fantasía", "Ciencia ficción", "clásica", "Misterio", "Histórica", "Autoayuda", "Infantil", "Poesía", "Biografías"};
        ObservableList<String> items = FXCollections.observableArrayList(categorias);
        cbxCategoria.setItems(items);
        //------------------------------------
        //Context Menu
        MenuItem itemEditar = new MenuItem("Editar");
        MenuItem itemEliminar = new MenuItem("Eliminar");
        //se lo asigno a mi menuitem
        cmOpciones.getItems().addAll(itemEditar, itemEliminar);
        //y le digo donde los quiero
        tblLibros.setContextMenu(cmOpciones);
        itemEditar.setOnAction(event -> edicionLibro());
        itemEliminar.setOnAction(event -> eliminarLibro());

    }

    //-----------------INTERACCION CON LOS PANELES/FORM
    @FXML
    public void cambiarForm(ActionEvent event) {
        if (event.getSource() == btnForm_AgregarLibro) {
            pnl_AgregarLibro.setVisible(true);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
        } else if (event.getSource() == btnForm_Actualizar_Inventario) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(true);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
        } else if (event.getSource() == btnFormRegistar_Devolucion) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(true);
            pnl_Bienvenido.setVisible(false);
        }
    }

    
    //--------------------CONTEXT MENU
    private void edicionLibro() {
        int index = tblLibros.getSelectionModel().getSelectedIndex();
        opcContex = tblLibros.getItems().get(index);
        System.out.println(opcContex);
        txtTitulo.setText(opcContex.getTitulo());
        txtAutor.setText(opcContex.getAutor());

        int anioPublicacion = opcContex.getAnioPublicacion();
        String textoAnio = Integer.toString(anioPublicacion);
        txtAñoPublicacion.setText(textoAnio);
        
        txtEditorial.setText(opcContex.getEditorial());
        txtDescripcion.setText(opcContex.getDescripcion());
        
        int cantidad = opcContex.getCantidadDisponible();
        String txtCan = Integer.toString(cantidad);
        txtCantidad.setText(txtCan);
        
        cbxCategoria.getSelectionModel().select(opcContex.getCategoria());
        
        
        btnGuardar.setDisable(false);  
    }
    
    private void eliminarLibro(){
        int index = tblLibros.getSelectionModel().getSelectedIndex();
      Libro eliminarLB = tblLibros.getItems().get(index);
      consulta.eliminarLibro(eliminarLB.getIsbn());
      cargarLibros();
    }
    
    
    
    
/**
     * txtTitulo txtAutor txtAñoPublicacion txtEditorial txtDescripcion
     * txtCantidad cbxCategoria
     *
     */
    @FXML
    private void btnRegistrar(ActionEvent event) {
        
        if (opcContex != null) {
             tblLibros.getItems().clear();
            opcContex.setTitulo(txtTitulo.getText());
            opcContex.setAutor(txtAutor.getText());
            opcContex.setAnioPublicacion(parseInt(txtAñoPublicacion.getText()));
            opcContex.setEditorial(txtEditorial.getText());
            opcContex.setDescripcion(txtDescripcion.getText());
            opcContex.setCantidadDisponible(parseInt(txtCantidad.getText()));
            opcContex.setCategoria(cbxCategoria.getSelectionModel().getSelectedItem());
            consulta.editarLibro(opcContex);
            cargarLibros();
            limpiar();  
        }
        
        else{
            tblLibros.getItems().clear();
            //instancio a mi modelo
            Libro lb = new Libro();

            lb.setTitulo(txtTitulo.getText());
            lb.setAutor(txtAutor.getText());
            lb.setAnioPublicacion(parseInt(txtAñoPublicacion.getText()));
            lb.setEditorial(txtEditorial.getText());
            lb.setDescripcion(txtDescripcion.getText());
            lb.setCantidadDisponible(parseInt(txtCantidad.getText()));
            lb.setCategoria(cbxCategoria.getSelectionModel().getSelectedItem());

            consulta.registrarLibro(lb);
            cargarLibros();
            limpiar();  
        }

        
    }

    public void cargarLibros() {
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
        TableColumn isbndCol = new TableColumn("ISBN");
        //el identificardr de la variable que se encuentra en la la cles CAlumno
        isbndCol.setCellValueFactory(new PropertyValueFactory("isbn"));

        //isbn, titulo, autor, anioPublicacion, editorial, descripcion, cantidadDisponible, categoria
        //Columna Titulo
        TableColumn tituloCol = new TableColumn("TITULO");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
        //Columna Autor
        TableColumn autorCol = new TableColumn("AUTOR");
        autorCol.setCellValueFactory(new PropertyValueFactory("autor"));
        //Columna año de puclicacion
        TableColumn anioCol = new TableColumn("Año Publicacion");
        anioCol.setCellValueFactory(new PropertyValueFactory("anioPublicacion"));
        //Columan Editorial
        TableColumn editorialCol = new TableColumn("EDITORIAL");
        editorialCol.setCellValueFactory(new PropertyValueFactory("editorial"));
        //Descripcion
        TableColumn descripCol = new TableColumn("DESCRIPCION");
        descripCol.setCellValueFactory(new PropertyValueFactory("descripcion"));
        descripCol.setResizable(false);
        //Cantidad De libros
        TableColumn canCol = new TableColumn("CANTIDAD");
        canCol.setCellValueFactory(new PropertyValueFactory("cantidadDisponible"));
        //categoria
        TableColumn categoriaCol = new TableColumn("CATEGORIA");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblLibros.setItems(datos);

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        tblLibros.getColumns().addAll(isbndCol, tituloCol, autorCol, anioCol, editorialCol, descripCol, canCol, categoriaCol);
    }
  

    @FXML
    private void btnExportar_libros(ActionEvent event) {
         //Creo un ArrayLIST DE tipo Libro
        //ArrayList<Libro> lista = new ArrayList();
     
        //llamo a mi metodo exportar
        consulta.serializarTabla(lista);
    
    }
    
    
    public void limpiar() {
        
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAñoPublicacion.setText("");
        txtEditorial.setText("");
        txtDescripcion.setText("");
        txtCantidad.setText("");

        cbxCategoria.getSelectionModel().select("Eliga la categoria");

    }
    
    /*cARRGAR PRESTAMOS*/
        public void cargarPrestamos() {
        tblPrestamos.getItems().clear();
        tblPrestamos.getColumns().clear();
        
        Cst_Empleado consultaEmp = new Cst_Empleado();
        
        consultaEmp.mostrarPrestamos(listaPrestamos);
        // Creo un ObservableList a partir de la lista de libros
        ObservableList<Prestamo_libros> datos = FXCollections.observableArrayList(listaPrestamos);
        
        //Columna ID se crea una nueva comoluma de tabla
        //Encabezado de mi tabla
        TableColumn isbndCol = new TableColumn("Id Prestamo");
        //el identificardr de la variable que se encuentra en la la cles Prestamo_libro
        isbndCol.setCellValueFactory(new PropertyValueFactory("id_prestamo"));

        TableColumn tituloCol = new TableColumn("Fecha Prestamo");
        tituloCol.setCellValueFactory(new PropertyValueFactory("fecha_prestamo"));
        //Columna Autor
        TableColumn autorCol = new TableColumn("ISBN");
        autorCol.setCellValueFactory(new PropertyValueFactory("isbn"));
        //Columna año de puclicacion
        TableColumn anioCol = new TableColumn("Titulo Libro");
        anioCol.setCellValueFactory(new PropertyValueFactory("titulo"));
        //Columan Editorial
        TableColumn editorialCol = new TableColumn("id Cliente");
        editorialCol.setCellValueFactory(new PropertyValueFactory("id_cliente"));
        //Descripcion
        TableColumn descripCol = new TableColumn("Cliente");
        descripCol.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        descripCol.setResizable(false);
        //Cantidad De libros
        TableColumn canCol = new TableColumn("Dias Prestamo");
        canCol.setCellValueFactory(new PropertyValueFactory("periodo_Prestamo"));
        //categoria
        
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblPrestamos.setItems(datos);

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        tblPrestamos.getColumns().addAll(isbndCol, tituloCol, autorCol, anioCol, editorialCol, descripCol, canCol);
    }
    
    
    @FXML
    private void regresarLogin(MouseEvent event) throws IOException {
        Interacciones_ventanas Iv = new Interacciones_ventanas();
        Iv.ventanaLogin();
    }

    @FXML
    private void btnBuscar_prestamo(ActionEvent event) {
        try {
          this.consultaPrestamo = new Cst_Empleado();
        int idPrestamo = Integer.parseInt(txtIdPrestamo.getText());
       Prestamo_libros prestamo = consultaPrestamo.obtenerPrestamoPorId(idPrestamo);

        
      //  prestamo.obtenerPrestamoPorId(idPrestamo);

        if (prestamo != null) {
            mostrarDatosPrestamo(prestamo);
           // verificarMulta(prestamo);
        } else {
            lblDatosPrestamo.setText("Préstamo no encontrado.");
           // lblMulta.setText("");
        }
    } catch (NumberFormatException e) {
        lblDatosPrestamo.setText("ID de préstamo inválido.");
      //  lblMulta.setText("");
    } catch (Exception e) {
        lblDatosPrestamo.setText("Error al buscar el préstamo.");
     //   lblMulta.setText("");
    }
        
    }
    
    private void mostrarDatosPrestamo(Prestamo_libros prestamo) {
        lblDatosPrestamo.setText(
            "Libro: " + prestamo.getTitulo()+ "\n" +
            "Usuario: " + prestamo.getNombreCliente()+ "\n" +
            "Fecha de Préstamo: " + prestamo.getFecha_prestamo()+ "\n" +
            "Fecha de Entrega: " + prestamo.getPeriodo_Prestamo()
          //  "Periodo de Préstamo: " + prestamo.getPeriodoPrestamo()
        );
    }
}
