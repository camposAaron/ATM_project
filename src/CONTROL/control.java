/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import POJO.Cliente;
import POJO.ManejoSaldo;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jos_a
 */
public class control{
    
   
    private Scanner sc;
    List<Cliente> clientes;
    List<ManejoSaldo> manejo ;
    static File f = new File("Usuario.dat");
    IdaoCliente idaoc ;
    ManejoIdaoImpl idaoM;
   
    public control() {
     
        this.sc = new Scanner(System.in);
        this.clientes=new ArrayList<>();
        this.manejo =  new ArrayList<>();
        this.idaoc = new IdaoCliente();
        this.idaoM = new ManejoIdaoImpl();
      
    }
    
   public void inicioSesion() throws IOException{         //verifica si la cuenta existe e inicia sesion
     clientes = idaoc.findAll();
       boolean flag = false;           
       String pin=null,numero,nombre= null;
      
        
      do{
              
          System.out.print("\nNcuenta: ");
          numero = sc.next();
         
          for (Cliente cliente : clientes) {
          if( cliente.getNumeroCuenta().equals(numero)){
              nombre = cliente.getNombre();
              flag = true;
       
              do{
                System.out.print("\nPIN:    "); 
                pin =sc.next();
              
              if(pin.length()>4 || pin.length()<4)
                       System.err.println("Ingrese un pin valido");
                
               }while(pin.length()>4 || pin.length()<4);
             
              if(cliente.getPIN().equals(pin)){
                 flag = true;
                
              }else{
                  flag = false;
                  System.err.println("PIN no coincide, intentelo de nuevo");
              }
              
       }    
    
          }     
      }while(!flag );
    
      ControlUser(numero,nombre);     //llama al metodo que controla las operaciones del usuario
   
      }
    
   public void ControlUser(String numberAccount, String nombre){
        int opc = 0;
        
        System.out.println("\n->Inicio de sesion con exito");
        System.out.println("\nUsuario: "+nombre);
        
    do{
       do{
       System.out.print("\nQue desea hacer?"
               + "\n1.-Retiro \n2.-Deposito \n3.-Mostrar movimientos \n4.-Cerrar sesion\n Opcion->");
       opc = sc.nextInt();
       if(opc>4 || opc<1)
         System.err.println("opcion invalida");
      }while(opc<1 || opc>4);
        
        switch(opc){
            case 1:
       {
           try {
               RegistrarSalida(numberAccount);
           } catch (IOException ex) {
               Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
                break;
            case 2:
       {
           try {
               RegistrarEntrada(numberAccount);
           } catch (IOException ex) {
               Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
                break;
            case 3:
             
       {
           try {
               mostrarTransacciones(numberAccount);
           } catch (IOException ex) {
               Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
        }
        
    }while(opc!=4);
   }
       
    public void RegistrarEntrada(String numberAccount) throws IOException{
       List<ManejoSaldo> RegistroUsr = new ArrayList<>();
       manejo = idaoM.findAll();                                                                        //crea una lista
     
        for (ManejoSaldo m : manejo) {
            if(m.getNumeroCuenta().equals(numberAccount)){           //Busca los registros del usuario en particular  
              RegistroUsr.add(m);
            }
        }
  
       
       ManejoSaldo ms = new ManejoSaldo();                           //instancia de objeto manejoSaldo
       Date date = new Date();
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");                                                             
       DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
             
      
        String fechaActual = dateFormat.format(date);                  
        String horaActual  = hourFormat.format(date);
        
        System.out.println("----------------------------------------------------");
        System.out.println("Hora: "+hourFormat.format(date));         //imprime por pantalla fecha y hora actual del sistema                                      
        System.out.println("Fecha: "+dateFormat.format(date));
     
          int ultimo =  RegistroUsr.size()-1;                               //longitud de la lista
          double saldo = RegistroUsr.get(ultimo).getSaldo();               //obtiene el saldo del ultimo elemento de la lista
          String temp;                                                
         
           do{
        System.out.print("deposito($): ");                             //ingreso del deposito con validacion numerica
        temp = sc.next();
        }while(isDouble(temp) == false);
          Double deposito = parseDouble(temp);
      
          ms.setNumeroCuenta(numberAccount);
          ms.setEntrada(deposito);                                    
          ms.setFechaTransaccion(fechaActual);    
          ms.setHoraTransaccion(horaActual);
          ms.setTipo("entrada");
          ms.setSaldo(saldo+deposito);
          RegistroUsr.add(ms);
         
          idaoM.save(ms);                                             //guarda la nueva entrada en el archivo manejo.dat
         
         
    }
    
    public  void RegistrarSalida(String numberAccount) throws IOException{
        
        List<ManejoSaldo> RegistroUsr = new ArrayList<>();
         manejo = idaoM.findAll();
         for (ManejoSaldo m : manejo) {
            if(m.getNumeroCuenta().equals(numberAccount)){           
               RegistroUsr.add(m);
                                                                        
            }
        }
         
       ManejoSaldo ms = new ManejoSaldo();                           //instancia de objeto manejoSaldo
       Date date = new Date();
       System.out.println("----------------------------------------------------");         //Caso 1: obtener la hora y salida por pantalla con formato:
       DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
       System.out.println("Hora: "+hourFormat.format(date));
                                                                     //Caso 2: obtener la fecha y salida por pantalla con formato:
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       System.out.println("Fecha: "+dateFormat.format(date));
       
        String fechaActual = dateFormat.format(date);                   //imprime por pantala fecha y hora actual del sistema
        String horaActual  = hourFormat.format(date);
        
          int ultimo =  RegistroUsr.size()-1;                               //longitud de la lista
          double saldo = RegistroUsr.get(ultimo).getSaldo();                //obtiene el saldo del ultimo elemento de la lista
          String temp;            
          
               do{
        
        System.out.print("Retiro($): ");                             //ingreso del deposito con validacion numerica
        temp = sc.next();
        }while(isDouble(temp) == false);
        Double retiro = parseDouble(temp);
          
        if(retiro<=saldo){
            ms.setNumeroCuenta(numberAccount);
            ms.setSalida(retiro);
            ms.setFechaTransaccion(fechaActual);
            ms.setHoraTransaccion(horaActual);
            ms.setSaldo(saldo-retiro);
            ms.setTipo("retiro");
            RegistroUsr.add(ms);
            idaoM.save(ms);
             
        }else{
            System.err.println("Saldo insuficiente");
        }
           
         
    } 
    
   //Registro de usuario
    public void RegistrarUsuario() throws IOException{
        
        Cliente usr = new Cliente();
        ManejoSaldo ms = new ManejoSaldo();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
       
        
        double apertura ;
        
        System.out.println("-----------------------------------------------------------------------------"
                + "\n                               REGISTRO USUARIO\n");
        
         System.out.print("Nombre: ");
         String nom = sc.next();
         System.out.print("Apellido: ");
         String ape = sc.next();
         System.out.print("NCedula: ");
         String id = sc.next();
       
         do{
         System.out.println("Apertura de cuenta MIN(300 C$)");
         apertura = sc.nextDouble();
         if(apertura<300){
             System.err.println("La apertura de la cuenta debe ser minimo de 300$");
         }
         }while(apertura<300);
          
         
          usr.setNombre(nom);
          usr.setApellido(ape);
          usr.setId(id);
          String numeroCuenta = GenNumberAccount(usr);
          usr.setNumeroCuenta(numeroCuenta);
          usr.setPIN(GenPIN());
          
          
          String fechaActual = dateFormat.format(date);   
          String horaActual  = hourFormat.format(date);
          ms.setNumeroCuenta(numeroCuenta);
          ms.setTipo("saldo de apertura");
          ms.setHoraTransaccion(horaActual);
          ms.setFechaTransaccion(fechaActual);
          ms.setEntrada(apertura);
          ms.setSaldo(apertura);
          
          
        
      
          idaoc.save(usr);
          idaoM.save(ms);
        //  manejo = idaoM.findAll();
        //  clientes = idaoc.findAll();

          
          System.out.println("\nUsuario registrado con exito!"
                  +"\n nombre:           "+nom+" "+ape 
                  +"\n NCedula:          "+id
                  +"\n Numero de cuenta: "+usr.getNumeroCuenta()
                  +"\n PIN:              "+usr.getPIN());
                
        
     
     }
    
   
     
     //generador de numero de cuenta
     public String GenNumberAccount(Cliente usr){
         String name = usr.getNombre();
         String last = usr.getApellido();
         String dn = usr.getId();
         String fragment = dn.substring(0, 3) + name.substring(0,1) + last.substring(0,1);
          
            Random r = new Random();
              int valorDado = r.nextInt(1000)+301;
              
              String NumberAccount = valorDado+fragment;
              return NumberAccount;
     }
     
     //generador de PIN
    public String GenPIN(){
          int n[] = new int[4];
          for(int i=0;i<4;i++){
              n[i] =(int) ((Math.random())*10);
          }
                  
              return ""+n[0]+""+n[1]+""+n[2]+""+n[3] ; 
    }
    
     
    public boolean isNumeric(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException nfe){
        return false;
        }
    }
    
    public boolean isDouble(String temp){
        try{
            Double.parseDouble(temp);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }
    
       public void showUsers() throws IOException{
         clientes = idaoc.findAll();
         System.out.format("%15s %15s %15s %15s %14s", "NCEDULA","NOMBRE","APELLIDO","NCUENTA","PIN\n");
         for (Cliente cliente : clientes) {
             printUser(cliente);
        }
     } 
  
    private void printUser(Cliente e){
        
      System.out.format("%14s %14s %14s %14s %14s\n",
              e.getId(), e.getNombre(), e.getApellido(), e.getNumeroCuenta(), e.getPIN());
               
    }
     public void  mostrarTransacciones(String numero) throws IOException{
   
         List<ManejoSaldo> RegistroUsr = new ArrayList<>();
       manejo = idaoM.findAll();                                                                        //crea una lista
     
        for (ManejoSaldo m : manejo) {
            if(m.getNumeroCuenta().equals(numero)){           //Busca los registros del usuario en particular  
              RegistroUsr.add(m);
            }
        }
        
         for (ManejoSaldo m : RegistroUsr) {
             System.out.println();
             System.out.println("HORA: "+m.getHoraTransaccion());
             System.out.println("FECHA: "+m.getFechaTransaccion());
           
             if(m.getTipo().equals("entrada"))
                 System.out.println("TIPO: "+m.getTipo()+" = "+m.getEntrada());
             else
                if(m.getTipo().equals("saldo de apertura"))
                 System.out.println("TIPO: "+m.getTipo()+" = "+m.getEntrada());
                   else
                     System.out.println("TIPO: "+m.getTipo()+" = "+m.getSalida());
           
             System.out.println("SALDO: "+m.getSaldo());
         }
        
      }
     
     public void deleteUser() throws IOException{
         String numero;
         if(!f.exists() || f.length() == 0){
             System.err.println("Aun no hay usuarios registrados");
         }else{
             System.out.println("Digite el NCedula del usuario");
             numero = sc.next();
             if(idaoc.delete(numero))
                 System.out.println("Cuenta eliminada con exito!");
             else
                  System.err.println("Falla al eliminar registro");
         }
     }
     
     public void findUsr() throws IOException{
       List<Cliente> usr =new ArrayList<>();
    
       System.out.println("Digite el NCedula:");
           String cedula = sc.next();
        if(cedula.length() == 16){  
           idaoc.findById(cedula);
                usr = idaoc.findById(cedula);
                   
          System.out.format("%15s %15s %15s %15s %14s", "NCEDULA","NOMBRE","APELLIDO","NCUENTA","PIN\n");
         for (Cliente c : usr) {
             printUser(c);
        }
        }else{
            System.err.println("Ncedula invalido");
        }
     }
}
