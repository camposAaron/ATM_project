/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

/**
 *
 * @author jos_a
 */
import java.io.RandomAccessFile;
import java.io.File;
import DAO.ClienteIdao;

import POJO.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IdaoCliente implements ClienteIdao {
    private RandomAccessFile raf;
    private File f;
    private final int SIZE = 109;
    private String RAFNAME = "Usuario.dat";


    public IdaoCliente() {
        f = new File(RAFNAME);
    }

    public void Open() throws IOException {
        if (!f.exists()) {
            f.createNewFile();
            raf = new RandomAccessFile(f, "rw");
            raf.seek(0);
            raf.writeInt(0);//n
            raf.writeInt(0);//k
        } else {
            raf = new RandomAccessFile(f, "rw");
        }
    }

    public void Close() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }

    @Override
    public void save(Cliente t) throws IOException {
        Open();
        raf.seek(0);
        int n = raf.readInt();
        int k = raf.readInt();

        long pos = 8 + SIZE * k;
      
        raf.seek(pos);
        raf.writeUTF(t.getId());
        raf.writeUTF(t.getNombre());
        raf.writeUTF(t.getApellido());
        raf.writeUTF(t.getNumeroCuenta());
        raf.writeUTF(t.getPIN());
      
        raf.seek(0);
        raf.writeInt(++n);
        raf.writeInt(++k);
        Close();
    }

    @Override
    public int update(Cliente t) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String numero) throws IOException {
      
      boolean ind=false;
        boolean flag2=false;
        File tmp = new File("tmp.dat");
        tmp.createNewFile();
        RandomAccessFile tmpr = new RandomAccessFile(tmp, "rw");
        Open();
        raf.seek(0);
        int n = raf.readInt();
        int k = raf.readInt();
        int c = 0, n1 = 0, k1 = 0;
        for (int i = 0; i < n; i++) {
            tmpr.seek(0);
            tmpr.writeInt(n1);//n
            tmpr.writeInt(k1);//k
            long pos = 8 + i * SIZE ;
            raf.seek(pos);
            String cedula = raf.readUTF();

            if (cedula.equals(numero)) {
                ind=true;
                continue;
            }

            tmpr.seek(0);
            tmpr.writeInt(++n1);
            tmpr.writeInt(++k1);

            raf.seek(pos);
            cedula = raf.readUTF();
            String nombre = raf.readUTF();
            String apellido = raf.readUTF();
            String Ncuenta = raf.readUTF();
            String pin = raf.readUTF();
          

            long posT = 8 + c * SIZE ;
            
            tmpr.seek(posT);
            tmpr.writeUTF(cedula);
            tmpr.writeUTF(nombre);
            tmpr.writeUTF(apellido);
            tmpr.writeUTF(Ncuenta);
            tmpr.writeUTF(pin);
            c++;
        }

        Close();
        tmpr.close();

        boolean flag = f.delete();

        if (flag) {
            tmp.renameTo(f);
        }
        
        if(flag && ind){
            flag2=true;
        }

        return flag2;
          
            
       
    }

    @Override
    public List<Cliente> findAll() throws IOException {
      List<Cliente> clientes = new ArrayList<>();
      Open();
      raf.seek(0);
      int n = raf.readInt();
       for(int i = 0; i < n; i++){
               long pos = 8 + SIZE * i;
               raf.seek(pos);
               Cliente c = new Cliente();
               c.setId(raf.readUTF());
               c.setNombre(raf.readUTF());
               c.setApellido(raf.readUTF());
               c.setNumeroCuenta(raf.readUTF());
               c.setPIN(raf.readUTF());
               
               clientes.add(c);
               
           }
       Close();
       return clientes;
       }
      
    

    @Override
    public List<Cliente> findById(String id) throws IOException {
       
        List<Cliente> usr = new ArrayList<>();
        Open();
        raf.seek(0);
        int n = raf.readInt();
        
        for(int i=0;i<n;i++){
        long pos = 8+SIZE*i;
        raf.seek(pos);
        
          Cliente c = new Cliente();
             
          c.setId(raf.readUTF());
               c.setNombre(raf.readUTF());
               c.setApellido(raf.readUTF());
               c.setNumeroCuenta(raf.readUTF());
               c.setPIN(raf.readUTF());
               
          if(c.getId().equals(id))
              usr.add(c);
    }
        Close();
        return usr;
    }

}
