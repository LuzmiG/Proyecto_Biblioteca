/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Consultas;

import Controlador.Interacciones_ventanas;
import Modelo.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static proyectofinal_biblioteca.ProyectoFinal_Biblioteca.cerraVentana;

/**
 *
 * @author Luzmi
 */
public class Verificacion_Usuario {
    
    private Conexion fabricaConexion;
    Interacciones_ventanas Iv = new Interacciones_ventanas();
    
    private  int userId;
    
    public Verificacion_Usuario(){
        //se llama automáticamente cuando se crea un objeto tipo Verificacion_Usuario, Esto permite que los métodos de la clase Consultas realicen operaciones en la base de datos utilizando esa conexión
        this.fabricaConexion = new Conexion();
    }
    
  
    public void verificar(String usuario, String pass) throws IOException{
   
        
        
        try {      
         //Crea la conexion a la base de datos 
        Connection conectar = fabricaConexion.getConexion();
        
        /*Constuyo la consulta 
        Aqui debo consultar dos tablas (empleado, cliente) porque estas dos tablas son mis usuarios,
        lo hice asi, poque hasta el momento no sabia como hacer que cuando se agregara un cliente o empleado se agregar automaticamente en una tabla usario, ton's se trabaja con lo que se sabe 
        la funcion union  hace que la consulta busque conincidencias de los tres campos que tiene en comun que conforma un "usuario"
        de esta manera estas dos tablas se unen
        */
        String sql = "SELECT usuario, pass, privilegio FROM empleado WHERE usuario = '"+usuario+"' and  pass = '"+pass+"' UNION "
                     +"SELECT usuario, pass, privilegio FROM cliente WHERE usuario = '"+usuario+"' and  pass = '"+pass+"'" ;
       
        /*Consulta preconstruida al momento de ejecutarse y que se correcto el result set obtiene los resultados,
        Prepared statement verifica que se posible la consulta en la base de datos
        result set accede a los datos (conjunto de datos = informacion)de la base de datos listo para tener una funcionalidad
        */
     
            PreparedStatement sentencia = conectar.prepareStatement(sql);
            ResultSet datos = sentencia.executeQuery();
            
            //Si hay datos,
            /*
            .next busca de manera secuencial de buscar o iterar dentro del result set
            */
            if(datos.next()){
                
                /*
                las variables  representa las columnas de (usuario, pass, privilegio) que se encuentra en ambas tablas 
                */
                String u = datos.getString("usuario");
                String c = datos.getString("pass");
                String rol = datos.getString("privilegio");
                
                
                //verifica si la contraseña recuperada en "c" coincide con la contraseña "pass" Si coinciden pasmos al otro if
                if(c.equals(pass)){
                   // verifica si el rol del usuario es “cliente”
                    if(rol.equals("cliente")){
                   //     FXMLLoader vistaV = new FXMLLoader(getClass().getResource("/vista/Cliente.FXML"));
                            Iv.ventanaCliente();
  
                       
                    }
                    else if (rol.equals("empleado")){
                       
                       Iv.ventanaEmpleado();
                    }
                }            
            }
            else{
                fabricaConexion.alertaNegativa("El usuario o contraseña no es correcta");
                fabricaConexion.cerrarConexion();
                System.out.println("----------El usuario o contraseña no es correcta");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(Verificacion_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
// preparedStatement
    }
    
    public void capturarId(String usuario){
          //Crea la conexion a la base de datos 
       
        try {
            Connection conectar = fabricaConexion.getConexion();
            int userId = 0;
            String sql = "SELECT id_cliente FROM cliente WHERE usuario = ?";
            PreparedStatement sentencia = conectar.prepareStatement(sql);
            sentencia.setString(1, usuario);
            ResultSet datos = sentencia.executeQuery();
             if (datos.next()) {
                userId = datos.getInt("id_cliente");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Verificacion_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
     
    }
    
    
}
