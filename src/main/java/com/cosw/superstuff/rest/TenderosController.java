/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tendero;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET) 
    public List<Tendero> traerTenderos(@PathVariable int id) {
        List<Tendero> tenderos = superStuff.cargarTenderos();
        return tenderos;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Tendero obtenerTendero(@RequestParam("usuario") String usuario, @RequestParam("contrasena") String contrasena) {  
        Tendero t = superStuff.iniciarSesionTendero(usuario, contrasena);
        return t;
    }
}
