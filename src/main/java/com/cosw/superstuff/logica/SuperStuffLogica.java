/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.logica;

import com.cosw.superstuff.rep.RepositorioEnvios;
import com.cosw.superstuff.rep.RepositorioProductos;
import com.cosw.superstuff.rep.RepositorioProveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOMERO
 */
@Service
public class SuperStuffLogica {
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    
    public void registrarProducto(){
        
    }
    
    public void cargarProductosPorCategoria(){
        
    }
    
    public void cargarTodosLosProductos(){
        
    }
    
    public void cargarProductosPorProveedor(){
        
    }
    
    public void cargarCategorias(){
        
    }
    
    public void registrarPedido(){
        
    }
    
    public void registrarEnvio(){
        
    }
    
    public void actualizarEstadoDeEnvio(){
        
    }
    
    public void crearNuevoProveedor(){
        
    }
    
    public void crearNuevoTenedreo(){
        
    }
}
