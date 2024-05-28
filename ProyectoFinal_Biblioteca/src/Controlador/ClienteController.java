/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controlador;

import Consultas.Cst_Cliente;
import Consultas.Cst_Empleado;
import Consultas.Cst_libro;
import Consultas.Verificacion_Usuario;
import Modelo.Cliente;
import Modelo.Libro;
import Modelo.Prestamo_libros;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
      private Cst_libro consultaD;
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
    @FXML
    private TextField txtBuscar;
    
    
    ///para filtrar
    private ObservableList<Libro> listaOriginal;
    
    @FXML
    private ContextMenu cmOpciones;
    @FXML
    private Button btnR_fantasia;
    @FXML
    private TableView<Libro> tblRecomendaciones;
    @FXML
    private Button btnR_romance;
    @FXML
    private Button btnR_Ficcion;
    @FXML
    private Button btnR_clasica;
    @FXML
    private Button btnR_misterio;
    @FXML
    private Button btnR_ayuda;
    @FXML
    private Button btnR_historica;
    @FXML
    private Button btnR_infantil;
    @FXML
    private Button btnR_poesia;
    @FXML
    private Button btnR_biografia;
    @FXML
    private Button btnO_ISBN;
    @FXML
    private Button btnO_Titulo;
    @FXML
    private Button btnO_Autor;
    @FXML
    private Button btnHistorialPrestamos;
    @FXML
    private TableView<Prestamo_libros> tblHistorial;
    @FXML
    private AnchorPane pnl_historial2;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtTelefono;
    @FXML
    private Button btnGuardar_edicion;
    @FXML
    private TextField txtCorreo;
    @FXML
    private AnchorPane pnl_editarPerfil;
    @FXML
    private Button btnEditarPerfil;
    @FXML
    private Label lblDatosActulizados;
     Interacciones_ventanas Iv = new Interacciones_ventanas();
    //pila
    private Stack<Libro> pilaLibros = new Stack<>();
    
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
         this.consultaD = new Cst_libro();
       //  consulta.
         //ver los datos de la tabla
         cargarLibros();
         //ver recomendaciones
         cargarRecomendaciones();
         
       
        
        
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

       public void mostrarCredenciales(int id, String nombreCliente){
                            // Este método se utiliza para convertir diferentes tipos de datos en cadenas de caracteres
    lbl_idUsuario.setText(String.valueOf(id)); // Muestra el ID del cliente
    lbl_usuario.setText(nombreCliente); // Muestra el nombre del cliente
}
    
    @FXML
    public void cambiarForm(ActionEvent event) {
        if (event.getSource() == btnLibros) {
            pnl_VerLibro.setVisible(true);
            pnl_recomendaciones.setVisible(false);
            pnl_Bienvenido.setVisible(false);
            pnl_historial2.setVisible(false);
            pnl_editarPerfil.setVisible(false);
            
        } else if (event.getSource() == btnRecom) {
            pnl_VerLibro.setVisible(false);
            pnl_recomendaciones.setVisible(true);
            pnl_Bienvenido.setVisible(false);
             pnl_historial2.setVisible(false);
      pnl_editarPerfil.setVisible(false);

        } 
        else if (event.getSource() == btnHistorialPrestamos) {
            pnl_VerLibro.setVisible(false);
            pnl_recomendaciones.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_historial2.setVisible(true);
    pnl_editarPerfil.setVisible(false);

        } 
        else if (event.getSource() == btnEditarPerfil) {
            pnl_VerLibro.setVisible(false);
            pnl_recomendaciones.setVisible(false);
            pnl_Bienvenido.setVisible(false);
             pnl_historial2.setVisible(false);
                         pnl_editarPerfil.setVisible(true);

        }
            
    }
    
    public void cargarLibros(){
         tblLibros.getItems().clear();
        tblLibros.getColumns().clear();
        
        
         //Creo un ArrayLIST DE tipo Libro
        ArrayList<Libro> lista = new ArrayList();
        
       // lista.clear();
        
        //llamo a mi metodo mostrarLibro
        consulta.mostrarLibros(lista);
        
        
        
        // Creo un ObservableList a partir de la lista de libros
        //ObservableList<Libro> datos = FXCollections.observableArrayList(lista);
        //Columna ID se crea una nueva comoluma de tabla
        //Encabezado de mi tabla
        
        
        
        //para filtrar
         listaOriginal = FXCollections.observableArrayList(lista);
        
        //Columna Titulo
        TableColumn tituloCol = new TableColumn("TITULO");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
         //Columna Autor
        TableColumn autorCol = new TableColumn("AUTOR");
        autorCol.setCellValueFactory(new PropertyValueFactory("autor"));
         //categoria
        TableColumn categoriaCol = new TableColumn("CATEGORIA");
        categoriaCol.setCellValueFactory(new PropertyValueFactory("categoria"));
        
         tblLibros.getColumns().addAll(tituloCol, autorCol, categoriaCol);
        
        //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblLibros.setItems(listaOriginal);
        
      
    }   
    
    
    public void cargarRecomendaciones() {
        tblRecomendaciones.getItems().clear();
        tblRecomendaciones.getColumns().clear();
      
        
       
        //Creo un ArrayLIST DE tipo Libro
     ArrayList<Libro> lista = new ArrayList(); 
     //Ya no lo use porque arriba lo declare para que se pueda serializar y ordenar =) y tambien esto hacia que se llenar dos veces mi tabla =/
        //llamo a mi metodo mostrarLibro
        consultaD.mostrarLibros(lista);
        //para filtrar
         listaOriginal = FXCollections.observableArrayList(lista);
        
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

        //construir las columnas
        //son objetos que representan las columnas de la tabla.
        
        tblRecomendaciones.getColumns().addAll(isbndCol, tituloCol, autorCol, anioCol, editorialCol, descripCol, canCol, categoriaCol);
        tblRecomendaciones.setItems(listaOriginal);
    
    }
    
   public void cargarHistorial(){
     
         tblHistorial.getItems().clear();
        tblHistorial.getColumns().clear();
        
        
         //Creo un ArrayLIST DE tipo Libro
        ArrayList<Prestamo_libros> lista = new ArrayList();
       
        int idCliente = Integer.parseInt(lbl_idUsuario.getText());
   
        //llamo a mi metodo mostrarLibro
        consulta.mostrarHistorial(lista, idCliente);
        

        ObservableList<Prestamo_libros> datos = FXCollections.observableArrayList(lista);

        
        //Columna Titulo
        TableColumn idPrestamoCol = new TableColumn("Id Prestamo");
        idPrestamoCol.setCellValueFactory(new PropertyValueFactory("id_prestamo"));
TableColumn isbnCol = new TableColumn("ISBN Libro");
        isbnCol.setCellValueFactory(new PropertyValueFactory("isbn"));
                
        
//Columna Autor
        TableColumn tituloCol = new TableColumn("Titulo");
        tituloCol.setCellValueFactory(new PropertyValueFactory("titulo"));
         //categoria
        TableColumn fechaCol = new TableColumn("Fecha Prestamo");
        fechaCol.setCellValueFactory(new PropertyValueFactory("fecha_prestamo"));
        
        TableColumn estadoCol = new TableColumn("Estado");
        estadoCol.setCellValueFactory(new PropertyValueFactory("estado"));
        
         TableColumn nomCol = new TableColumn("Nombre Cliente");
        nomCol.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        
          //Aqui es donde dice que los datos(conjunto de datos) se mostraran en la tablaLibros
        tblHistorial.setItems(datos);
        
        tblHistorial.getColumns().addAll(idPrestamoCol, isbnCol, tituloCol, fechaCol, estadoCol, nomCol);
        
      
        
      
      
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
       tblHistorial.refresh();
       cargarHistorial();
        
        
//  intento de pila 
        
        
    //    String nombreDelLibro = lbl_titulo.getText();
        pilaLibros.push(pl);
        
        for(Libro l : pilaLibros){
            System.out.println(l);
            System.out.println("Último libro prestado: " + lbl_titulo.getText());
        }
        
        System.out.println("Con Pop");
      System.out.println(pilaLibros.pop());
      
        
        
        // String ultimoLibroPrestado = lbl_titulo.getText();
        
        
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
    
    Iv.ventanaLogin();
    
    
    }

    @FXML
    private void txtBuscar(KeyEvent event) {
                                            //pasarlas a miusculas
         String buscar = txtBuscar.getText().toLowerCase();
         
          if (buscar.isEmpty()) {
              // Si el campo de búsqueda está vacío, mostrar todos los libros
            tblLibros.setItems(listaOriginal);
          }
          else{
             //AQUI SE van encontrar almacenar los datos que se encuentre en la tabla
        ObservableList<Libro> concidencia = FXCollections.observableArrayList();
        
        for (Libro libro : tblLibros.getItems()) {
            
        if (libro.getTitulo().toLowerCase().contains(buscar) ||
            libro.getAutor().toLowerCase().contains(buscar) ||
            libro.getCategoria().toLowerCase().contains(buscar)) {
            concidencia.add(libro);
        }  
          }
            tblLibros.setItems(concidencia);
    }
     
    }

    @FXML
    private void mostrarHistorial_btn(MouseEvent event) {
        //cargar historial
        cargarHistorial();
    }
    @FXML
    private void btnR_fantasia(ActionEvent event) {
           ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Fantasía".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    }

    @FXML
    private void btnR_romance(ActionEvent event) {
            ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Romance".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    }

    @FXML
    private void btnR_Ficcion(ActionEvent event) {
              ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Ciencia ficción".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    }

    @FXML
    private void btnR_clasica(ActionEvent event) {
                  ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("clásica".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_misterio(ActionEvent event) {
                  ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Misterio".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_ayuda(ActionEvent event) {
               ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Autoayuda".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_historica(ActionEvent event) {
             ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Histórica".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_infantil(ActionEvent event) {
          ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Infantil".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_poesia(ActionEvent event) {
           ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Poesía".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnR_biografia(ActionEvent event) {
            ObservableList<Libro> datosFiltrados = FXCollections.observableArrayList();
        // Iterar sobre los libros en la lista original y agregar aquellos que pertenecen a la categoría "fantasía"
        for (Libro libro : listaOriginal) {
            if ("Biografías".equalsIgnoreCase(libro.getCategoria())) {
                datosFiltrados.add(libro);
            }
        }

        // Actualizar la tabla con la lista filtrada
        tblRecomendaciones.setItems(datosFiltrados);
    
    }

    @FXML
    private void btnO_ISBN(ActionEvent event) {
         listaOriginal.sort(Comparator.comparingInt(libro -> libro.getIsbn()));
    
        tblRecomendaciones.setItems(FXCollections.observableArrayList(listaOriginal));
  
    }

    @FXML
    private void btnO_Titulo(ActionEvent event) {
           listaOriginal.sort(Comparator.comparing(libro -> libro.getTitulo()));
      
        tblRecomendaciones.setItems(FXCollections.observableArrayList(listaOriginal));
   
    }

    @FXML
    private void btnO_Autor(ActionEvent event) {
            listaOriginal.sort(Comparator.comparing(libro -> libro.getAutor()));
          tblRecomendaciones.setItems(FXCollections.observableArrayList(listaOriginal));
     
    }

    

    @FXML
    private void editarPerfil(MouseEvent event) {
       int idCliente = Integer.parseInt(lbl_idUsuario.getText());
        Cliente cliente = consulta.retornarCliente(idCliente);
        
        if (cliente != null) {
            txtNombre.setText(cliente.getNombre());
            txtUsuario.setText(cliente.getUsuario());
            txtPass.setText(cliente.getPass());
            txtTelefono.setText(String.valueOf(cliente.getTelefono()));
            txtCorreo.setText(cliente.getCorreo());
        }
    }

    @FXML
    private void btnGuardar_edicion(ActionEvent event) {
        
        int idCliente = Integer.parseInt(lbl_idUsuario.getText());
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        cliente.setNombre(txtNombre.getText());
        cliente.setUsuario(txtUsuario.getText());
        cliente.setPass(txtPass.getText());
        cliente.setTelefono(Integer.parseInt(txtTelefono.getText()));
        cliente.setCorreo(txtCorreo.getText());
        
        consulta.editarCliente(cliente);
        mostrarDatosActualizacion(cliente);
    }
    
     private void mostrarDatosActualizacion(Cliente Cl_Actulizado) {
     //   lbl_idUsuario.setText(String.valueOf(Cl_Actulizado.getId()));
        lblDatosActulizados.setText(
                "ID Cliente: " + Cl_Actulizado.getId()+ "\n"
                + "Nombre: " + Cl_Actulizado.getNombre()+ "\n"
                + "Usuario: " + Cl_Actulizado.getUsuario()+ "\n"
                + "Contraseña: " + Cl_Actulizado.getPass()+ "\n"
                + "Telefono: " + Cl_Actulizado.getTelefono()+ "\n"
                + "Correo: " + Cl_Actulizado.getCorreo()
        );
    }
    
  
}

 /**
  
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