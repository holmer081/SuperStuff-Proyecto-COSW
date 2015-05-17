/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Tienda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HOMERO
 */
@RestController
@RequestMapping("/tiendas")
public class TiendasController {
    
    @Autowired
    SuperStuffLogica superStuff;
    
    @RequestMapping(value="/{idTendero}", method = RequestMethod.GET)
    public List<Tienda> getTiendasPorTendero(@PathVariable int idTendero) {
        return superStuff.getTiendasPorTendero(idTendero);
    }
}
