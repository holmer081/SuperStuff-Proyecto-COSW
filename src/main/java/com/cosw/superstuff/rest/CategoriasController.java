/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Camilo
 */
@RestController
@RequestMapping("/categoria")
public class CategoriasController {
    
    @Autowired
    SuperStuffLogica superStuff;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> traerCategorias() {  
        List<Categoria> cat = superStuff.cargarCategorias();
        return cat;
    }
}
