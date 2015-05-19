/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Categoria;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tendero;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HOMERO
 */
@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {
    @Autowired
    SuperStuffLogica superStuff;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Proveedor p) {
        superStuff.crearNuevoProveedor(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/all",method = RequestMethod.GET) 
    public List<Proveedor> cargarProveedores() {  
        List<Proveedor> proveedores = superStuff.cargarProveedores();
        return proveedores;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Proveedor obtenerTendero(@RequestParam("usuario") String usuario, @RequestParam("contrasena") String contrasena) {  
        Proveedor p = superStuff.iniciarSesionProveedor(usuario, contrasena);
        return p;
    }
}
