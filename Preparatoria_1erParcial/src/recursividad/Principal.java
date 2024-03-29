/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recursividad;

import java.util.Scanner;

/**
 *
 * @author Luzmi
 */
public class Principal {
       public static void main(String[] args){
           Scanner teclado = new Scanner(System.in);
           System.out.println("Ingrese un numero");
           int numero = teclado.nextInt();
        Recursividad recur = new Recursividad();
        int factorial = recur.Factorial(numero);
           System.out.println("El factorial de: " +numero + " Es :"+ factorial);
        
    }
}
