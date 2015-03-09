/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Producto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioProductos extends CrudRepository<Producto, Integer>{
    
    public List<Producto> productosPorCategoria(@Param(":idCategoria") int idCategoria);
}
