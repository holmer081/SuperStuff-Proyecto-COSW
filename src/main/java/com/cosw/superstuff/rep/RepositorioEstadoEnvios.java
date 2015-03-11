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
    
    @Query("UPDATE Envio e set e.estadoEnvios=:estadoEnvios where idEnvio= :idEnvio")
    public void ActualizarEnvio(@Param("idEstadoEnvios") int idEstadoEnvios,@Param("lugares") String lugares);
    
}
