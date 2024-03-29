/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial.animales;

/**
 *
 * @author Luzmi
 */
public abstract class Animal {

   
    
    private String nombre;
    private String color;
    private float peso;
    String sexo;
    
    public Animal(){
        //Constructor vacio
    }
    
    //Constructor
        public Animal(String nombre, String color, float peso, String sexo) {
        this.nombre = nombre;
        this.color = color;
        this.peso = peso;
        this.sexo = sexo;
    }
    
    public String getNombre(){
        return nombre;
    }
    //Los gets retorna y los set tiene parametros
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
     public String getColor(){
         return color;
     }
     public void setColor(String color){
         this.color = color;
     }
     public float getPeso(){
         return peso;
     }
     public void setPeso(float peso){
         this.peso = peso;
     }
     public String getSexo(){
     
         return sexo;
     }
     public void setSexo(String sexo){
     }
     
     public String mostrarDatos(){
         return "Es un: " + nombre + "\nSu color es: " + color + "\nTiene un peso de: " + peso +  "\n Su genero es: " + sexo;
     }
     
}
