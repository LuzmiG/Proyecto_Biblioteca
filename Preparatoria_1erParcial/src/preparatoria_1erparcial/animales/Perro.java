/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial.animales;

/**
 *
 * @author Luzmi
 */
    public class Perro extends Animal {

    public Perro() {
        
    }
    
    String raza;
    
    public String getRaza(){
        return raza;
    }
    
    public void setRaza(String raza){
        this.raza = raza;
    }

    public Perro(String raza, String nombre, String color, float peso, String sexo) {
        super(nombre, color, peso, sexo);
        this.raza = raza;
    }
    
   
    
    public void EmitirSonido(){
        System.out.println("El perro hace GUAG");
    }
    @Override
    public String mostrarDatos(){
        
        //Muestra solo raza
       return  "La Raza del Perro es:" + raza;
    }
    


  
    
}
