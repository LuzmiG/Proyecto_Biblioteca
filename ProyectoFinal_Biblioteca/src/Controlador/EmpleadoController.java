/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Cliente;
import Consultas.Cst_Empleado;
import Consultas.Cst_libro;
import Modelo.Cliente;
import Modelo.Devolucion_prestamo;
import Modelo.Empleado;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import javafx.stage.StageStyle;

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
    private TableView<Empleado> tbl_Empleados;

    @FXML
    private ContextMenu cmOpciones;

    private Cst_libro consulta;
  //  private Cst_libro consulta = new Cst_libro();
    private Cst_Empleado consultaD;
    
 //   private Cst_Empleado consultaD = new Cst_Empleado();
    
    //Opciones pARA MI CONTExt menu
    private Libro opcContex;
    
    private Empleado opcContex_Emp;
    
    @FXML
    private Button btnExportar_libros;
    ///variabel para mostra en tablas y serializar
    ArrayList<Libro> lista = new ArrayList();
    ArrayList<Prestamo_libros> listaPrestamos = new ArrayList();
    ArrayList<Devolucion_prestamo> listaDevolucion = new ArrayList();
    ArrayList<Empleado> listaEmpleado = new ArrayList();
    ArrayList<Cliente> listaCliente = new ArrayList();
    
    
    @FXML
    private Label lbl_regresar;
    
    
    @FXML
    private TableView<Prestamo_libros> tblPrestamos;
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
    private Button btnGuardar_Empleado;
        @FXML
    private AnchorPane pnl_restaurar;
    @FXML
    private TextField txtSueldo_Empleado;
    @FXML
    private ComboBox<String> cbxHorario_empleado;
    @FXML
    private ContextMenu cmOpciones_Empleado;
    @FXML
    private Button btnForm_ListaUsuarios;
    @FXML
    private AnchorPane pnl_listaUsuaro;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private ContextMenu cmOpciones_ListaClientes;
    @FXML
    private ContextMenu cmOpciones_ListaPrestamos;
    @FXML
    private Button btnExportar_Clientes;
    @FXML
    private Button btnImportar_Clientes;
    @FXML
    private Button btnImportar_Libros;
    /**
     * txtTitulo txtAutor txtAñoPublicacion txtEditorial txtDescripcion
     * txtCantidad cbxCategoria
     * 
     * /-------------------
     *txtNombre_Empleado
     * txtPass_Empleado
     * txtSueldo_Empleado
     * cbxHorario_empleado
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
            //cargar tabla empleados
            cargarEmpleados();
            //cargar tabla de Clientes
            cargarClientes();
            //---------------------------------
            // Combo Box
            String[] categorias = {"Romance", "Fantasía", "Ciencia ficción", "clásica", "Misterio", "Histórica", "Autoayuda", "Infantil", "Poesía", "Biografías"};
            ObservableList<String> items = FXCollections.observableArrayList(categorias);
            cbxCategoria.setItems(items);
               // Combo Box EMPLADOS
            String[] horario = {"Lun-Vie", "Sab-Dom"};
            ObservableList<String> items2 = FXCollections.observableArrayList(horario);
            cbxHorario_empleado.setItems(items2);
            //------------------------------------
            //Context Menu LIBRO
            MenuItem itemEditar = new MenuItem("Editar");
            MenuItem itemEliminar = new MenuItem("Eliminar");
            MenuItem itemOrdenar_ISBN = new MenuItem("Ordenar por ISBN");
            MenuItem itemOrdenar_Titulo = new MenuItem("Ordenar Por TITULO");
            MenuItem itemOrdenar_Autor = new MenuItem("Ordenar Por Autor");
            //se lo asigno a mi menuitem
            cmOpciones.getItems().addAll(itemEditar, itemEliminar, itemOrdenar_ISBN, itemOrdenar_Titulo, itemOrdenar_Autor);
            //y le digo donde los quiero
            tblLibros.setContextMenu(cmOpciones);
            itemEditar.setOnAction(event -> edicionLibro());
            itemEliminar.setOnAction(event -> eliminarLibro());
            itemOrdenar_ISBN.setOnAction(event -> ordenarPorISBN());
            itemOrdenar_Titulo.setOnAction(event -> ordenarPorTitulo());
            itemOrdenar_Autor.setOnAction(event -> ordenarPorAutor());
            
            //---------------------------------------
            //Context MENU EMPLEADO
            MenuItem itemEditar_Empleado = new MenuItem("Editar");
            MenuItem itemEliminar_Empleado = new MenuItem("Eliminar");
            
            cmOpciones_Empleado.getItems().addAll(itemEditar_Empleado, itemEliminar_Empleado);
            //y le digo donde los quiero
            tbl_Empleados.setContextMenu(cmOpciones_Empleado);
            itemEditar_Empleado.setOnAction(event -> edicionEmpleado());
            itemEliminar_Empleado.setOnAction(event -> eliminarEmpleado());
            
            //-----------------------------------------
            //Conetex Cliente
             MenuItem itemOrdenar_IdCliente = new MenuItem("Ordenar por ID");
            MenuItem itemOrdenar_nombreCliente = new MenuItem("Ordenar por Nombre");
            
            cmOpciones_ListaClientes.getItems().addAll(itemOrdenar_IdCliente, itemOrdenar_nombreCliente);
            //y le digo donde los quiero
            tblClientes.setContextMenu(cmOpciones_ListaClientes);
            itemOrdenar_IdCliente.setOnAction(event -> ordenarPorIdCliente());
            itemOrdenar_nombreCliente.setOnAction(event -> ordenarPorNombre());
          
            
            //-----------------------------------------
            //Conetex Cliente
            MenuItem itemOrdenar_Idprestamo = new MenuItem("Ordenar Por ID prestamo");
             MenuItem itemOrdenar_NombreCliente_prestamo = new MenuItem("Ordenar por Nombre Cliente");
            MenuItem itemOrdenar_ISBNCliente_prestamo = new MenuItem("Ordenar por ISBN");
            MenuItem itemOrdenar_TituloCliente_prestamo = new MenuItem("Ordenar por Titulo");
            MenuItem itemOrdenar_Estado_prestamo = new MenuItem("Ordenar por Estado Prestamo");
            
            cmOpciones_ListaPrestamos.getItems().addAll(itemOrdenar_Idprestamo, itemOrdenar_NombreCliente_prestamo, itemOrdenar_ISBNCliente_prestamo, itemOrdenar_TituloCliente_prestamo, itemOrdenar_Estado_prestamo);
            //y le digo donde los quiero
            tblPrestamos.setContextMenu(cmOpciones_ListaPrestamos);
           
            itemOrdenar_NombreCliente_prestamo.setOnAction(event -> ordenarNombreCliente_prestamod());
            itemOrdenar_ISBNCliente_prestamo.setOnAction(event -> ordenarPorISBN_prestado());
            itemOrdenar_TituloCliente_prestamo.setOnAction(event -> ordenarPorTitulo_prestado());
            itemOrdenar_TituloCliente_prestamo.setOnAction(event -> ordenarPorTitulo_prestado());
            itemOrdenar_Idprestamo.setOnAction(event -> ordenarPorId_prestado());
            itemOrdenar_Estado_prestamo.setOnAction(event -> ordenarEstado_prestamo());
          
          
            
            
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
            pnl_listaUsuaro.setVisible(false);
            
        } else if (event.getSource() == btnForm_ListaPrestamos) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(true);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
            pnl_listaUsuaro.setVisible(false);
            
        } else if (event.getSource() == btnFormRegistar_Devolucion) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(true);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
            pnl_listaUsuaro.setVisible(false);
        }
        else if (event.getSource() == btnForm_AgregarEmpleado) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(true);
            pnl_restaurar.setVisible(false);
            pnl_listaUsuaro.setVisible(false);
        }
          else if (event.getSource() == btnForm_RestaurarSistema) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(true);
            pnl_listaUsuaro.setVisible(false);
        }
          else if (event.getSource() == btnForm_ListaUsuarios) {
            pnl_AgregarLibro.setVisible(false);
            pnl_ActualizarInventario.setVisible(false);
            pnl_devolucionLibro.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_Empleado.setVisible(false);
            pnl_restaurar.setVisible(false);
            pnl_listaUsuaro.setVisible(true);
        }
        
    }

    
    
    /*----------------- L I B R O -------------------------*/
    
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
      tblLibros.refresh();
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
             
            opcContex.setTitulo(txtTitulo.getText());
            opcContex.setAutor(txtAutor.getText());
            opcContex.setAnioPublicacion(parseInt(txtAñoPublicacion.getText()));
            opcContex.setEditorial(txtEditorial.getText());
            opcContex.setDescripcion(txtDescripcion.getText());
            opcContex.setCantidadDisponible(parseInt(txtCantidad.getText()));
            opcContex.setCategoria(cbxCategoria.getSelectionModel().getSelectedItem());
            consulta.editarLibro(opcContex);
          //  cargarLibros();
              tblLibros.refresh();
                 
            limpiar();  
        }
        
        else{
       //     tblLibros.getItems().clear();
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EXITO");
            alert.setHeaderText(null);
            alert.setContentText("El libro se registro Exitosamente");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

            tblLibros.refresh();
            cargarLibros();
            limpiar();
        }

        
    }
    
    public void cargarLibros() {
        tblLibros.getItems().clear();
        tblLibros.getColumns().clear();
        lista.clear();
        
       
        //Creo un ArrayLIST DE tipo Libro
     // ArrayList<Libro> lista = new ArrayList(); Ya no lo use porque arriba lo declare para que se pueda serializar y ordenar =) y tambien esto hacia que se llenar dos veces mi tabla =/
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
    
    public void ordenar(){
                          //es la lista de libros
       // Collections.sort(lista, (Libro a, Libro b) ->a.getIsbn().compareTo(b.getIsbn()));
    }
    
    public void ordenarPorISBN() {
       lista.sort(Comparator.comparingInt(libro -> libro.getIsbn()));
    
        tblLibros.setItems(FXCollections.observableArrayList(lista));
  
    }
    
     public void ordenarPorTitulo() {
         
          lista.sort(Comparator.comparing(libro -> libro.getTitulo()));
      
        tblLibros.setItems(FXCollections.observableArrayList(lista));
    }
     
     public void ordenarPorAutor(){
         lista.sort(Comparator.comparing(libro -> libro.getAutor()));
          tblLibros.setItems(FXCollections.observableArrayList(lista));
     }
  



    
    /*---------------P R E S T A M O S  ----------------------*/
 /*cARRGAR PRESTAMOS*/
    public void cargarPrestamos() {
        tblPrestamos.getItems().clear();
        tblPrestamos.getColumns().clear();
        listaPrestamos.clear();

        //Cst_Empleado consultaEmp = new Cst_Empleado();

        consultaD.mostrarPrestamos(listaPrestamos);
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

    public void ordenarPorISBN_prestado() {
       listaPrestamos.sort(Comparator.comparingInt(Prestamo_Libro -> Prestamo_Libro.getIsbn()));
    
        tblPrestamos.setItems(FXCollections.observableArrayList(listaPrestamos));
  
    }
     
       public void ordenarPorId_prestado() {
       listaPrestamos.sort(Comparator.comparingInt(Prestamo_Libro -> Prestamo_Libro.getId_prestamo()));
    
        tblPrestamos.setItems(FXCollections.observableArrayList(listaPrestamos));
  
    }
   
    
     public void ordenarPorTitulo_prestado() {
         
          listaPrestamos.sort(Comparator.comparing(Prestamo_Libro -> Prestamo_Libro.getTitulo()));
      
        tblPrestamos.setItems(FXCollections.observableArrayList(listaPrestamos));
    }
     
     public void ordenarNombreCliente_prestamod(){
         listaPrestamos.sort(Comparator.comparing(Prestamo_Libro -> Prestamo_Libro.getNombreCliente()));
          tblPrestamos.setItems(FXCollections.observableArrayList(listaPrestamos));
     }
  
        public void ordenarEstado_prestamo(){
         listaPrestamos.sort(Comparator.comparing(Prestamo_Libro -> Prestamo_Libro.getEstado()));
          tblPrestamos.setItems(FXCollections.observableArrayList(listaPrestamos));
     }
 
    private void mostrarDatosPrestamo(Prestamo_libros prestamo) {
        lbl_ISBN.setText(String.valueOf(prestamo.getIsbn()));
        lblDatosPrestamo.setText(
                "ID Prestamo: " + prestamo.getId_prestamo() + "\n"
                + "Cliente: " + prestamo.getNombreCliente() + "\n"
                + "ISBN: " + prestamo.getIsbn() + "\n"
                + "Libro: " + prestamo.getTitulo() + "\n"
                + "Fecha de Préstamo: " + prestamo.getFecha_prestamo() + "\n"
                + "Fecha de Entrega en: " + prestamo.getPeriodo_Prestamo()
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


    /*--------------- D E V O L U C I O N E S  ----------------------*/       
    
    public void CargarDevoluciones(){
        tblDevoluciones.getItems().clear();
        tblDevoluciones.getColumns().clear();
        
        listaDevolucion.clear();
        
       // Cst_Empleado consultaEmp = new Cst_Empleado();
        
        consultaD.mostrarDevoluciones(listaDevolucion);
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
    private void btnGuardarDevolucion(ActionEvent event) {
      
        
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
       
       
        tblDevoluciones.refresh();
        CargarDevoluciones();
        
        tblLibros.refresh();
        cargarLibros();
        
        tblPrestamos.refresh();
        cargarPrestamos();
        
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
        
    /*--------------- E M P L E A D O S ----------------------*/    
    
    public void cargarEmpleados(){
        tbl_Empleados.getItems().clear();
        tbl_Empleados.getColumns().clear();
        listaEmpleado.clear();
        
        
     //   Cst_Empleado consultaEmp = new Cst_Empleado();
        
        consultaD.mostrarEmpleados(listaEmpleado);
        // Creo un ObservableList a partir de la lista de libros
        ObservableList<Empleado> datos = FXCollections.observableArrayList(listaEmpleado);
        
        //Columna ID se crea una nueva comoluma de tabla
        //Encabezado Empleado
        TableColumn idPCol = new TableColumn("Id Empleado");
        //el identificardr de la variable que se encuentra en la la cles Prestamo_libro
        idPCol.setCellValueFactory(new PropertyValueFactory("id_empleado"));
        //columna NOMBRE
        TableColumn nomCol = new TableColumn("Nombre del Empleado");
        nomCol.setCellValueFactory(new PropertyValueFactory("nombre"));
        //Columna PASS
        TableColumn passCol = new TableColumn("Contraseña");
        passCol.setCellValueFactory(new PropertyValueFactory("pass"));
        //Columna sueldo    
        TableColumn sueldoCol = new TableColumn("Sueldo");
        sueldoCol.setCellValueFactory(new PropertyValueFactory("sueldo"));
        //Columan ID CLIENTE
        TableColumn hCCol = new TableColumn("Horario");
        hCCol.setCellValueFactory(new PropertyValueFactory("horario"));
        
        tbl_Empleados.setItems(datos);
        
        tbl_Empleados.getColumns().addAll(idPCol, nomCol, passCol, sueldoCol, hCCol);
    }
    
     private void edicionEmpleado() {
        int index = tbl_Empleados.getSelectionModel().getSelectedIndex();
        opcContex_Emp = tbl_Empleados.getItems().get(index);
      
        txtNombre_Empleado.setText(opcContex_Emp.getNombre());
        txtPass_Empleado.setText(opcContex_Emp.getPass());
        
        txtSueldo_Empleado.setText(String.valueOf(opcContex_Emp.getSueldo()));
      
        cbxHorario_empleado.getSelectionModel().select(opcContex_Emp.getHorario());
      btnGuardar.setDisable(false);  
    }
    
    private void eliminarEmpleado(){
        
        int index = tbl_Empleados.getSelectionModel().getSelectedIndex();
      Empleado eliminarEmp = tbl_Empleados.getItems().get(index);
      consultaD.eliminarLibro(eliminarEmp.getId_empleado());
      tbl_Empleados.refresh();
      cargarEmpleados();
  
      
    }
    
    
     @FXML
    private void btnGuardar_Empleado(ActionEvent event) {
                   
         if (opcContex_Emp != null) {
       
             opcContex_Emp.setNombre(txtNombre_Empleado.getText());
             opcContex_Emp.setPass(txtPass_Empleado.getText());

             //   opcContex_Emp.setSueldo(Float.parseFloat(txtSueldo_Empleado.getText()));
             float sueldo = Float.parseFloat(txtSueldo_Empleado.getText());
             opcContex_Emp.setSueldo(sueldo);

             // txtSueldo_Empleado.setText(String.valueOf(sueldo));
             opcContex_Emp.setHorario(cbxHorario_empleado.getSelectionModel().getSelectedItem());

             consultaD.editarEmpleado(opcContex_Emp);

             // Refrescar la tabla
             tbl_Empleados.refresh();
          //   cargarEmpleados();

          tblDevoluciones.refresh();
        CargarDevoluciones();
          
          
             // Limpiar los campos de entrada
             limpiarEmpleado();
       

        }
        
        else{
            
             Empleado emp = new Empleado();
             emp.setNombre(txtNombre_Empleado.getText());
             emp.setPass(txtPass_Empleado.getText());
             emp.setSueldo(Float.parseFloat(txtSueldo_Empleado.getText()));
             emp.setHorario(cbxHorario_empleado.getSelectionModel().getSelectedItem());

             consultaD.agregarEmpleado(emp);
             
               tbl_Empleados.refresh();
                cargarEmpleados();
                limpiarEmpleado();
            }
       
    }
    
    public void limpiarEmpleado(){

        
  
     txtNombre_Empleado.setText("");
     txtPass_Empleado.setText("");
     txtSueldo_Empleado.setText("");
     cbxCategoria.getSelectionModel().select("Eliga la categoria");
    }
    
       /*----------------- USUARIOS -------------------------*/
    
    public void cargarClientes() {
        tblClientes.getItems().clear();
        tblClientes.getColumns().clear();

        //Creo un ArrayLIST DE tipo Libro
      // ArrayList<Cliente> listaCliente = new ArrayList();
        //llamo a mi metodo mostrarLibro
        consultaD.mostrarClientes(listaCliente);
        // Creo un ObservableList a partir de la lista de libros
        ObservableList<Cliente> datos = FXCollections.observableArrayList(listaCliente);
        //Columna ID se crea una nueva comoluma de tabla
        //Encabezado de mi tabla
        TableColumn idCol = new TableColumn("Id Cliente");
        //el identificardr de la variable que se encuentra en la la cles CAlumno
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn nomCol = new TableColumn("Nombre del Cliente");
        //el identificardr de la variable que se encuentra en la la cles CAlumno
        nomCol.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        //USUARIO COLUMA
        TableColumn usCol = new TableColumn("Usuario");
        usCol.setCellValueFactory(new PropertyValueFactory("usuario"));
        //Columna CONTRASEÑA
        TableColumn passCol = new TableColumn("Contraseña");
        passCol.setCellValueFactory(new PropertyValueFactory("pass"));
        //Columna telefono
        TableColumn TelCol = new TableColumn("Telefono");
        TelCol.setCellValueFactory(new PropertyValueFactory("telefono"));
        //Columan CORREO
        TableColumn correoCol = new TableColumn("Correo");
        correoCol.setCellValueFactory(new PropertyValueFactory("correo"));
       
        tblClientes.setItems(datos);

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        tblClientes.getColumns().addAll(idCol, nomCol, usCol, passCol, TelCol, correoCol);
    }
    
        public void ordenarPorIdCliente() {
       listaCliente.sort(Comparator.comparingInt(Cliente -> Cliente.getId()));
    
        tblClientes.setItems(FXCollections.observableArrayList(listaCliente));
  
    }
    
     public void ordenarPorNombre() {
         
          listaCliente.sort(Comparator.comparing(Cliente -> Cliente.getNombre()));
        tblClientes.setItems(FXCollections.observableArrayList(listaCliente));
    }
     
 
  //  cmOpciones_ListaClientes
    
    @FXML
    private void regresarLogin(MouseEvent event) throws IOException {
        Interacciones_ventanas Iv = new Interacciones_ventanas();
        Iv.ventanaLogin();
    }

    @FXML
    private void btnExportar_Clientes(ActionEvent event) {
        consultaD.serializarTablaCliente(listaCliente);
    }

    @FXML
    private void btnImportar_Clientes(ActionEvent event) {
    listaCliente = consultaD.deserializarTablaCliente();
    if (!listaCliente.isEmpty()) {
        tblClientes.getItems().clear();
        tblClientes.getItems().addAll(listaCliente);
        tblClientes.refresh();
        System.out.println("se importro correctamente");
    }
    else {
        System.out.println("La lista de clientes está vacía.");
    }
         
    }

    @FXML
    private void btnImportar_Libros(ActionEvent event) {
        lista = consulta.deserializarTablaLibros();
    if (!lista.isEmpty()) {
        tblLibros.getItems().clear();
        tblLibros.getItems().addAll(lista);
        tblLibros.refresh();
        consulta.reemplazarLibros(lista);
        System.out.println("se importro correctamente");
        
    }
    else {
        System.out.println("La lista de clientes está vacía.");
    }
    }


    


    


   
    
}
        
    

