/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Cliente;
import Consultas.Cst_Empleado;
import Consultas.Cst_libro;
import Modelo.Devolucion_prestamo;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luzmi
 */
public class EmpleadoController implements Initializable {

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
    private Cst_Empleado consultaD;
    
    //Opciones pARA MI CONTExt menu
    private Libro opcContex;
    @FXML
    private Button btnExportar_libros;
    ///variabel para mostra en tablas y serializar
    ArrayList<Libro> lista = new ArrayList();
    ArrayList<Prestamo_libros> listaPrestamos = new ArrayList();
    ArrayList<Devolucion_prestamo> listaDevolucion = new ArrayList();
    
    
    @FXML
    private Label lbl_regresar;
    
    
    @FXML
    private TableView<Prestamo_libros> tblPrestamos;
    @FXML
    private ContextMenu cmOpciones1;
    @FXML
    private TextField txtIdPrestamo;
    @FXML
    private Label lblDatosPrestamo;
    
    private Cst_Empleado consultaPrestamo;
    @FXML
    private Button btnForm_ListaPrestamos;
    @FXML
    private TableView<Devolucion_prestamo> tblDevoluciones;
    @FXML
    private ContextMenu cmOpciones11;
    @FXML
    private DatePicker date_fechaDevolucion;
    @FXML
    private TextField txtMulta;
    @FXML
    private TextField txtDescripcionMulta;
    @FXML
    private ComboBox<String> cbxEmpleado;
    @FXML
    private Button btnGuardarDevolucion;
    @FXML
    private Label lbl_ISBN;
    @FXML
    private Button btnForm_AgregarEmpleado;
    @FXML
    private Button btnForm_RestaurarSistema;
    @FXML
    private AnchorPane pnl_Empleado;
    @FXML
    private TextField txtNombre_Empleado;
    @FXML
    private TextField txtPass_Empleado;
    @FXML
    private TextField txtAñoPublicacion1;
    @FXML
    private Button btnGuardar_Empleado;
    @FXML
    private Button btnExportar_libros1;
    @FXML
    private TableView<?> tbl_Empleados;
    @FXML
    private ContextMenu cmOpciones2;
    @FXML
    private AnchorPane pnl_restaurar;
    /**
     * txtTitulo txtAutor txtAñoPublicacion txtEditorial txtDescripcion
     * txtCantidad cbxCategoria
     *
     */
    
        
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //conexion
            this.consulta = new Cst_libro();
            this.consultaD = new Cst_Empleado();
            consultaD.llenarComboEmpleado(cbxEmpleado);
            //cargar tabla Libros
            cargarLibros();
            //cargar tablaPrestamos
            cargarPrestamos();
            //cargar tabla devoluciones
            CargarDevoluciones();
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
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //-----------------INTERACCION CON LOS PANELES/FORM
    @FXML
    public void cambiarForm(ActionEvent event) {
        if (event.getSource() == btnForm_AgregarLibro) {
            pnl_AgregarLibro.setVisible(true);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
            pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
            
        } else if (event.getSource() == btnForm_ListaPrestamos) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(true);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
            
        } else if (event.getSource() == btnFormRegistar_Devolucion) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(true);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
        }
        else if (event.getSource() == btnForm_AgregarEmpleado) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(true);
            pnl_restaurar.setVisible(false);
        }
          else if (event.getSource() == btnForm_RestaurarSistema) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(true);
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
        TableColumn idPCol = new TableColumn("Id Prestamo");
        //el identificardr de la variable que se encuentra en la la cles Prestamo_libro
        idPCol.setCellValueFactory(new PropertyValueFactory("id_prestamo"));
        //columna FECHA
        TableColumn FechaCol = new TableColumn("Fecha Prestamo");
        FechaCol.setCellValueFactory(new PropertyValueFactory("fecha_prestamo"));
        //Columna ISBN
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory("isbn"));
        //Columna TIUTLO LIBRO      
        TableColumn tituloCol = new TableColumn("Titulo Libro");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
        //Columan ID CLIENTE
        TableColumn idCCol = new TableColumn("id Cliente");
        idCCol.setCellValueFactory(new PropertyValueFactory("id_cliente"));
        //columna nombre cliente
        TableColumn nomCCol = new TableColumn("Cliente");
        nomCCol.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        //Cantidad De libros
        TableColumn diasCol = new TableColumn("Dias Prestamo");
        diasCol.setCellValueFactory(new PropertyValueFactory("periodo_Prestamo"));
        //estado
        TableColumn estadoCol = new TableColumn("Estado Prestamo");
        estadoCol.setCellValueFactory(new PropertyValueFactory("estado"));
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblPrestamos.setItems(datos);

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        tblPrestamos.getColumns().addAll(idPCol, FechaCol, isbnCol, tituloCol, idCCol, nomCCol, diasCol, estadoCol);
    }
    
        
    public void CargarDevoluciones(){
        tblDevoluciones.getItems().clear();
        tblDevoluciones.getColumns().clear();
        
        Cst_Empleado consultaEmp = new Cst_Empleado();
        
        consultaEmp.mostrarDevoluciones(listaDevolucion);
        // Creo un ObservableList a partir de la lista de libros
        ObservableList<Devolucion_prestamo> datos = FXCollections.observableArrayList(listaDevolucion);

        
        TableColumn idDCol = new TableColumn("Id Devolucion");
        //el identificardr de la variable que se encuentra en la la cles Prestamo_libro
        idDCol.setCellValueFactory(new PropertyValueFactory("id_devolucion"));
        //columna FECHA
        TableColumn FechaCol = new TableColumn("Fecha Devolución");
        FechaCol.setCellValueFactory(new PropertyValueFactory("fecha"));
        //Columna multa
        TableColumn multaCol = new TableColumn("Multa");
        multaCol.setCellValueFactory(new PropertyValueFactory("multa"));
        //Columna descripcion multa     
        TableColumn desMultaCol = new TableColumn("Descripcion Multa");
        desMultaCol.setCellValueFactory(new PropertyValueFactory("descripcion_multa"));
        //Columan ID prestamo
        TableColumn idPCol = new TableColumn("Id Prestamo");
        idPCol.setCellValueFactory(new PropertyValueFactory("id_prestamo"));
         //Columna ISBN
        TableColumn isbnCol = new TableColumn("ISBN");
        isbnCol.setCellValueFactory(new PropertyValueFactory("isbn"));
        //columna titulo 
        TableColumn tituloCol = new TableColumn("Titulo Libro");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
        //columna nombreCliente
        TableColumn clienteCol = new TableColumn("Cliente");
        clienteCol.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        //nombre empleado
        TableColumn empleadoCol = new TableColumn("Empleado");
        empleadoCol.setCellValueFactory(new PropertyValueFactory("nombre_emp"));
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblDevoluciones.setItems(datos);

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        tblDevoluciones.getColumns().addAll(idDCol, FechaCol, multaCol, desMultaCol, idPCol, isbnCol, tituloCol, clienteCol, empleadoCol);
   
    }  
    
    @FXML
    private void regresarLogin(MouseEvent event) throws IOException {
        Interacciones_ventanas Iv = new Interacciones_ventanas();
        Iv.ventanaLogin();
    }

    private void btnBuscar_prestamo(ActionEvent event) {
        try {
        //this.consultaPrestamo = new Cst_Empleado();
        int idPrestamo = Integer.parseInt(txtIdPrestamo.getText());
       Prestamo_libros prestamo = consultaPrestamo.obtenerPrestamoPorId(idPrestamo);

        
      //  prestamo.obtenerPrestamoPorId(idPrestamo);

        if (prestamo != null) {
            mostrarDatosPrestamo(prestamo);
           //verificarMulta(prestamo);
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
        lbl_ISBN.setText(String.valueOf(prestamo.getIsbn()));
        lblDatosPrestamo.setText(
            "ID Prestamo: " + prestamo.getId_prestamo()+ "\n" +
            "Cliente: " + prestamo.getNombreCliente()+ "\n" +
            "ISBN: " + prestamo.getIsbn()+ "\n" +
            "Libro: " + prestamo.getTitulo()+ "\n" +
            "Fecha de Préstamo: " + prestamo.getFecha_prestamo()+ "\n" +
            "Fecha de Entrega en: " + prestamo.getPeriodo_Prestamo()
  
        );
    }

    @FXML
    private void buscarIdprestamo(KeyEvent event) {
          try {
        this.consultaPrestamo = new Cst_Empleado();
        int idPrestamo = Integer.parseInt(txtIdPrestamo.getText());
       Prestamo_libros prestamo = consultaPrestamo.obtenerPrestamoPorId(idPrestamo);

        
      //  prestamo.obtenerPrestamoPorId(idPrestamo);

        if (prestamo != null) {
            mostrarDatosPrestamo(prestamo);
           //verificarMulta(prestamo);
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

    @FXML
    private void btnGuardarDevolucion(ActionEvent event) {
        tblLibros.getItems().clear();
        tblDevoluciones.getItems().clear();
        tblPrestamos.getItems().clear();
        
        Devolucion_prestamo dv = new Devolucion_prestamo();
        
        //Fecha
        String fecha = date_fechaDevolucion.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dv.setFecha(fecha);
        dv.setMulta(parseInt(txtMulta.getText()));
        dv.setDescripcion_multa(txtDescripcionMulta.getText());
        dv.setId_prestamo(parseInt(txtIdPrestamo.getText()));
        dv.setId_empleado(parseInt(cbxEmpleado.getSelectionModel().getSelectedItem()));
        dv.setIsbn(parseInt(lbl_ISBN.getText()));
        
        consultaD.registrarDevolucion(dv);
        CargarDevoluciones();
        cargarPrestamos();
        cargarLibros();
        limpiarDevolucion();
        
    }
    
    public void limpiarDevolucion(){
        lblDatosPrestamo.setText("");
        lbl_ISBN.setText("");
        txtIdPrestamo.setText("");
        txtMulta.setText("");
        txtDescripcionMulta.setText("");
     

       // cbxEmpleado.getSelectionModel().select(Integer.SIZE);

    }

    @FXML
    private void btnGuardar_Empleado(ActionEvent event) {
    }
    
}
        
    

