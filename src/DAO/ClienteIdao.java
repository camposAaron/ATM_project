/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Cliente;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author jos_a
 */
public interface ClienteIdao extends IDao<Cliente>{
       List<Cliente> findById(String id)throws IOException;
}
