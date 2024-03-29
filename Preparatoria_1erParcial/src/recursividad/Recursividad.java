/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursividad;

/**
 *
 * @author Luzmi
 */
public class Recursividad {
  /*  public void imprimir(int x){
        if (x<=5) {
            System.out.print(x + " ");
            imprimir(x+1);
        }
    }
*/
    
    public int Factorial(int parametro){
        if (parametro >0) {
            int calcular = parametro*Factorial(parametro-1);
            return calcular;
        }
        return 1;
    }
 
}
