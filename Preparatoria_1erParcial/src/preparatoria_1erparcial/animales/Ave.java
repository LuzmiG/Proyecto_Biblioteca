/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial.animales;

/**
 *
 * @author Luzmi
 */
public class Ave  extends Animal{
    int crias;
    
    public int getCrias(){
        return crias;
    }
    
    public void setCrias(int crias){
        this.crias = crias;
    }
    
                //VA PRIMERO EL PARAMETRO QUE YO INCLUYO EN ESTE METODOS
   // public Ave(int crias, String nombre, String color, float peso, String sexo )
        //super(nombre, color, peso, sexo):
         //this.crias = crias;

    public Ave(int crias, String nombre, String color, float peso, String sexo) {
        super(nombre, color, peso, sexo);
        this.crias = crias;
    }
    
    public String mostrarDatos(){
        return "El ave tiene: " +crias;
    }
}
