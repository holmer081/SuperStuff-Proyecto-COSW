/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rep;

import com.cosw.superstuff.persistencia.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HOMERO
 */
public interface RepositorioProveedores extends CrudRepository<Proveedor, Integer>{

    @Query("SELECT p FROM Proveedor p WHERE p.usuario = :usuario AND p.contrasena = :contrasena")
    public Proveedor obtenerProveedorPorCrendenciales(String usuario, String contrasena);
    
}
