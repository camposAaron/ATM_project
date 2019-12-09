/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.IDao;

import POJO.ManejoSaldo;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jos_a
 */
public class ManejoIdaoImpl implements IDao<ManejoSaldo>{
    private RandomAccessFile raf;
    private File f;
    private String RAFNAME = "Manejo.dat";
    private final int SIZE = 113;
    public ManejoIdaoImpl() {
        f = new File(RAFNAME);
    }
    
       private void open() throws IOException{
      
        if(!f.exists()){
            f.createNewFile();
            raf = new RandomAccessFile(f, "rw");
            raf.seek(0);
            raf.writeInt(0);
            raf.writeInt(0);
        }else{
            raf = new RandomAccessFile(f, "rw");
        }
    }
         private void close() throws IOException{
        if(raf != null){
            raf.close();
        }
    }

    

    @Override
    public void save(ManejoSaldo t) throws IOException {
        open();
        raf.seek(0);
        int n = raf.readInt();
        int k = raf.readInt();
        
        long pos = 8 + SIZE *k;
        
        raf.seek(pos);
        raf.writeUTF(t.getNumeroCuenta());
        raf.writeUTF(t.getTipo());
        raf.writeUTF(t.getHoraTransaccion());
        raf.writeUTF(t.getFechaTransaccion());
        raf.writeDouble(t.getEntrada());
        raf.writeDouble(t.getSalida());
        raf.writeDouble(t.getSaldo());
        
        raf.seek(0);
        raf.writeInt(++n);
        raf.writeInt(++k);
        close();
    }

    @Override
    public int update(ManejoSaldo t) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String numero) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ManejoSaldo> findAll() throws IOException {
      
        List<ManejoSaldo> manejo = new ArrayList<>();
        open();
        raf.seek(0);
        int n = raf.readInt();
        for(int i = 0; i < n; i++){
         
            long pos = 8 + SIZE *i;
            raf.seek(pos);
            ManejoSaldo ms = new ManejoSaldo();  
            ms.setNumeroCuenta(raf.readUTF());
            ms.setTipo(raf.readUTF());
            ms.setHoraTransaccion(raf.readUTF());
            ms.setFechaTransaccion(raf.readUTF());
            ms.setEntrada(raf.readDouble());
            ms.setSalida(raf.readDouble());
            ms.setSaldo(raf.readDouble());
            
            manejo.add(ms);
        }    
        close();
        return manejo;
    }
         
         
}
