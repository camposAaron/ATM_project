/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CONTROL.control;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jos_a
 */
public class Pricipal {

    static Scanner sc = new Scanner(System.in);
    static control obj = new control();

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {

        int opc = 0;
        do {
            do {

                System.out.println("*****************************************************************************");
                System.out.println("                                PROYECTO ATM               ");
                System.out.println("1.-Registrar usuario al sistema");
                System.out.println("2.-Mostrar usuarios");
                System.out.println("3.-Eliminar usuario");
                System.out.println("4.-Buscar usuario");
                System.out.println("5.-Iniciar sesion");
                System.out.print("6.-Salir"
                        + "\n opcion->");
                opc = sc.nextInt();

            } while (opc < 1 || opc > 6);

            switch (opc) {
                case 1:
                    obj.RegistrarUsuario();
                    break;
                case 2:
                    obj.showUsers();
                    break;
                case 3:
                    obj.deleteUser();        
                    break;
                case 4:
                    obj.findUsr();
                    break;
                case 5:
                     obj.inicioSesion();
                    break;
            }

        } while (opc != 6);
        
    }    
  
}
