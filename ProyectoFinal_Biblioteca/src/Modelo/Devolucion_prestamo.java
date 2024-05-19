/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Luzmi
 */
public class Devolucion_prestamo extends Prestamo_libros{
    
   private int id_devolucion;
   private String fecha;
   private int multa;
   private String descripcion_multa;
   private int id_prestamo;
 /*  private int isbn;
     private String titulo;
   private String nombre_cl;
 */  
   private String nombre_emp;
   
   private int id_empleado;
   
 

   
   /*
   id_devolucion
   fecha
   multa
   descripcion_multa
   id_prestamo
   id_empleado
   isbn
   titulo
   nombre_cl
   nombre_emp
   */
    public int getId_devolucion() {
        return id_devolucion;
    }

    public void setId_devolucion(int id_devolucion) {
        this.id_devolucion = id_devolucion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    public String getDescripcion_multa() {
        return descripcion_multa;
    }

    public void setDescripcion_multa(String descripcion_multa) {
        this.descripcion_multa = descripcion_multa;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
    /*Estos son para mostrar en mi tabala devoluciones*/

  /*  public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    
    public String getNombre_cl() {
        return nombre_cl;
    }

    public void setNombre_cl(String nombre_cl) {
        this.nombre_cl = nombre_cl;
    }
*/
    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }
   
    
}
