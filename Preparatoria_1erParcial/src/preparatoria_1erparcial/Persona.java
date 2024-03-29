/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial;

/**
 *
 * @author Luzmi
 */
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String edad;

    public Persona() {
    }

    
    //Creo constructor

    public Persona(int id, String nombre, String apellido, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    
    //Creo getter and setters
   //Recordar Get retorna
    public int getId() {
        return id;
    }
    
    //Set dice this
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    public String mostrarDatos(){
        return "Nombre: " + nombre+ "\nApellido: "+ apellido + "\nEdad: "+ edad;
    }
    
}
