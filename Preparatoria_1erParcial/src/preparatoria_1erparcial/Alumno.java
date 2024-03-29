package preparatoria_1erparcial;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luzmi
 */
public class Alumno extends Persona {
    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    String promedio;



    
    //Getter and Setter
    /*
    Los Getter son para mostrar un dato
    y Los setters son para modificar un dato
    */
    
    
    
    public String getPromedio(){
        return promedio;
    }
    
    public void setPromedio(String promedio){
        this.promedio = promedio;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
    
    
    //Metodos
    public void mostraNombre(){
        System.out.println("Hola soy un alumno y puedo mostrar mi nombre:" );
    }
    
    public void aprobar(double calificacion){
        if(calificacion >= 6){
            System.out.println("Aprobado");
           } 
        else{
               System.out.println("No Aprobado");
        }
    }
    
    /*
    Intancia
    Crear una instancia es decir crear un objeto
    Hasta aqui estamos solo usando el pilar de abstraccion 
    
    --------------------------------------
    */
    
    /*
    ENCAPSULAMIENTO
    Evita la exposición de los datos de un objeto.
    las variables de mi clase debe ser privadas y los setter publicos
    */
    
    //cONSTRUCTOR vACIO
    public Alumno() {
    }
    
    //cONTRUCTORES

    public Alumno(int id, String nombre, String apellido, String promedio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.promedio = promedio;
    }

   
    
    @Override
    public String mostrarDatos(){
        return "Promedio: " + promedio;
    }
    
}
