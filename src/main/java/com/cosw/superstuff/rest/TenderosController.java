/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tendero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HOMERO
 */
@RestController
@RequestMapping("/tenderos")
public class TenderosController {
    @Autowired
    SuperStuffLogica superStuff;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@RequestBody Tendero t) {
        superStuff.crearNuevoTendero(t);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@PathVariable("id") int idTendero, 
                                     @PathVariable("nombre") String nombre) {

        superStuff.crearNuevoTendero(idTendero, nombre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
