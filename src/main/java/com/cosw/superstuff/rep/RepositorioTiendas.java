/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Tienda;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HOMERO
 */
public interface RepositorioTiendas extends CrudRepository<Tienda, Integer>{
    
}
