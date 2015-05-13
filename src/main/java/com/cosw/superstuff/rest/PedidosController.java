/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.DetalleCompra;
import com.cosw.superstuff.persistencia.Pedido;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Tendero;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Camilo
 */
@RestController
@RequestMapping("/pedido")
public class PedidosController {
    
    @Autowired
    SuperStuffLogica superStuff;

    
    @RequestMapping(value="/tarjeta/{numTarjeta}"
            + "/securityCode/{securityCode}"
            + "/nombre/{nombre}"
            + "/monto/{monto}"
            + "/correo/{correo}"
            + "/direccion/{direccion}"
            + "/dia/{dia}"
            + "/mes/{mes}"
            + "/ano/{ano}"
            + "/hora/{hora}"
            + "/idProductos/{idProductos}"
            + "/cantidades/{cantidades}",method = RequestMethod.POST)
    public ResponseEntity<?> realizarPedido(@PathVariable String numTarjeta
            , @PathVariable String securityCode
            , @PathVariable String nombre
            , @PathVariable String monto
            , @PathVariable String correo
            , @PathVariable String direccion
            , @PathVariable int dia
            , @PathVariable int mes
            , @PathVariable int ano
            , @PathVariable int hora
            , @PathVariable int[] idProductos
            , @PathVariable int[] cantidades) {  
        
        Date date = new Date();
        date.setDate(dia);
        date.setMonth(mes);
        date.setYear(ano);
        date.setHours(hora);
        
        
        try {
            realizarPago(numTarjeta, securityCode, nombre, monto, correo);
            superStuff.registrarPedido(direccion, date, idProductos, cantidades);
            return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
        } catch (Exception ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public Date prueba() {
        return new Date();
    }
    
    
    
    private void realizarPago(String numTarjeta, String securityCode, String nombre, String cantidad, String correo) {
        try {
            String stringurl = "https://pasarelacosw.herokuapp.com/rest/PAYPAL/pago/tarjeta/" +
                    "/" + nombre +"/VISA/" + securityCode + "/" + correo + "/monto/" + cantidad + "/seguridad/3/SuperStuff";
            URL url = new URL(stringurl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.close();
            httpCon.getInputStream();
        } catch (MalformedURLException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
