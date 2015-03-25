/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HOMERO
 */
@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {
    @Autowired
    SuperStuffLogica superStuff;
    
    /*RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Proveedor p) {
        superStuff.crearNuevoProveedor(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@PathVariable("id") int idProveedores, 
                                     @PathVariable("idLugar") int idLugar, 
                                     @PathVariable("razonSocial") String razonSocial,
                                     @PathVariable("direccion") String direccion,
                                     @PathVariable("telefono") String contactoTelefonico,
                                     @PathVariable("sitioWeb") String sitioWeb,
                                     @PathVariable("email") String email) {
 
        superStuff.crearNuevoProveedor(idProveedores, idLugar, razonSocial, direccion, contactoTelefonico, sitioWeb, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
