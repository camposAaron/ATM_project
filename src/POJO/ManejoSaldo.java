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
public class ManejoSaldo {
    private String numeroCuenta;//23
    private String tipo; //20
    private double Entrada; //8
    private double Salida; //8
    private double Saldo; //8
    private String fechaTransaccion; //20  
    private String horaTransaccion; //20
    
     public ManejoSaldo() {
    }

   
    public ManejoSaldo( String numeroCuenta, String tipo, String hora, String fecha,Double Entrada,Double Salida,Double Saldo ) {
        this.numeroCuenta = numeroCuenta;
        this.Entrada = Entrada;
        this.Salida = Salida;
        this.Saldo = Saldo;
        this.fechaTransaccion = fecha;
        this.tipo =tipo;
        this.horaTransaccion = hora;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
    public double getEntrada() {
        return Entrada;
    }

    public void setEntrada(Double Entrada) {
        this.Entrada = Entrada;
    }

    public double getSalida() {
        return Salida;
    }

    public void setSalida(Double Salida) {
        this.Salida = Salida;
    }

    public void setSaldo(double saldo){
        this.Saldo = saldo;
    }
            
    
    public double getSaldo() {
        return Saldo;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public String getHoraTransaccion() {
        return horaTransaccion;
    }

    public void setHoraTransaccion(String horaTransaccion) {
        this.horaTransaccion = horaTransaccion;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ManejoSaldo{" + "numeroCuenta=" + numeroCuenta + ", tipo=" + tipo + ", Entrada=" + Entrada + ", Salida=" + Salida + ", Saldo=" + Saldo + ", fechaTransaccion=" + fechaTransaccion + ", horaTransaccion=" + horaTransaccion + '}';
    }

   
    
   
    
}
