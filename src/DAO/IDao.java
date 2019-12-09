/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author jos_a
 */
public interface IDao<T> {
       void save(T t)throws IOException;
       int update(T t)throws IOException;
       boolean delete(String Ncuenta)throws IOException;
       List<T> findAll()throws IOException;
}
