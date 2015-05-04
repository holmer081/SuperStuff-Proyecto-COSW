/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Tendero;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author HOMERO
 */
public interface RepositorioTenderos extends CrudRepository<Tendero, Integer>{
    
    @Query("SELECT t FROM Tendero t WHERE t.usuario = :usuario AND t.contrasena = :contrasena")
    public Tendero obtenerTenderoPorCrendenciales(@Param("usuario") String usuario, @Param("contrasena") String contrasena);
}
