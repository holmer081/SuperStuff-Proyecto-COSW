/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;


import com.cosw.superstuff.persistencia.EstadoEnvio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Usuario
 */
public interface RepositorioEstadoEnvios extends CrudRepository<EstadoEnvio,Integer> {
    
    @Query("UPDATE EstadoEnvio FROM EstadoEnvio es inner join es.envios en SET estado=:estado, descripcion=:descripcion, coordenadas=:coordenadas WHERE envios.idEnvio=:idEnvio")
    public void ActualizarEnvio(@Param("idEnvio") int idEnvio,@Param("estado") String estado,@Param("descripcion") String descripcion,@Param("coordenadas") String coordenadas);
    
}
