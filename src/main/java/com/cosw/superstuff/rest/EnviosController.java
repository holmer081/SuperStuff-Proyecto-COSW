/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Pedido;
import java.util.Date;
import java.util.Set;
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
 *
 * @author Camilo
 */
@RestController
@RequestMapping("/envio")
public class EnviosController {
    @Autowired
    SuperStuffLogica superStuff;
    
    @RequestMapping(value="/",method = RequestMethod.POST)
    public ResponseEntity<?> persist(@PathVariable() int[] pedidos) {  
        superStuff.registrarEnvio(pedidos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
