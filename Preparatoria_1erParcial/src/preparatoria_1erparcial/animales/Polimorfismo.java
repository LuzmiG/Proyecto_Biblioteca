/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package preparatoria_1erparcial.animales;

/**
 *
 * @author Luzmi
 */
public class Polimorfismo {
    
       public static void main(String[] args) {

       Perro animalitos = new Perro("Pastor Aleman", "Hercules","Cafe", (float) 2.5, "Macho");
       Ave av = new Ave(5,"Pepe", "verde", 1.5f, "Macho");
       
       System.out.println(animalitos.mostrarDatos());
           System.out.println(av.mostrarDatos());
           
           animalitos.EmitirSonido();
           System.out.println("holaaa");
    }

}
