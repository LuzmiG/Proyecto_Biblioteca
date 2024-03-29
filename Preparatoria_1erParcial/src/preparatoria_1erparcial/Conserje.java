/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial;

/**
 *
 * @author Luzmi
 */
public class Conserje extends Persona {
    
    //Estos son los atributos UNICOS DE ESTA PERSONA
    float sueldo;
    String funcion;

    public Conserje() {
    }
    

    public Conserje(float sueldo, String funcion, int id, String nombre, String apellido, String edad) {
        super(id, nombre, apellido, edad); //Atributos heredados con la palabra super
        this.sueldo = sueldo;
        this.funcion = funcion;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
    
    @Override
     public String mostrarDatos(){
        return "Sueldo: " + sueldo+ "\n Funcion: " + funcion;
    }
    
    
    
    
}
