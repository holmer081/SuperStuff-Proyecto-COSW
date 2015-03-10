/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Usuario
 */
public interface RepositorioCategorias extends CrudRepository<Categoria, Integer>{
    
    @Query("FROM Categoria c")
    public List<Categoria> CargarCategorias();
}
