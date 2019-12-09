/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;





/**
 *
 * @author jos_a
 */


public class Cliente {
    
       private String id;                           //16*2+3 = 35
       private String numeroCuenta;                 //10*2+3 = 23
       private String PIN;                          // 4*2+3 = 11  
       private String nombre, apellido;             //15*2+3 = 33
           //total = 102
      
   
    public Cliente() {
    }

    public Cliente(String id,String numeroCuenta,String PIN, String nombre, String apellido) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.apellido = apellido;
       
        this.id =id;
        this.PIN = PIN;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
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

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   /* @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", numeroCuenta=" + numeroCuenta + ", PIN=" + PIN + ", nombre=" + nombre + ", apellido=" + apellido  + '}';
    }*/
    
    
       
       
       
}
