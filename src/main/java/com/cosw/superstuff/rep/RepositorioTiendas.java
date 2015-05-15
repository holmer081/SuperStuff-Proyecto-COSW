/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Tienda;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioTiendas extends CrudRepository<Tienda, Integer>{
    
    @Query("SELECT t from Tendero tend INNER JOIN tend.tiendas t WHERE tend.idTenderos = :idTendero")
    public List<Tienda> getTiendasPorTendero (@Param("idTendero") int idTendero);
}
