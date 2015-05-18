/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.rest;

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Factura;
import com.cosw.superstuff.persistencia.Tendero;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    
    @RequestMapping(method = RequestMethod.POST)
    public String realizarPedido(@RequestParam(value = "numTarjeta") String numTarjeta
            , @RequestParam(value = "securityCode") String securityCode
            , @RequestParam(value = "idTendero") int idTendero
            , @RequestParam(value = "idTienda") int idTienda
            , @RequestParam(value = "dia") int dia
            , @RequestParam(value = "mes") int mes
            , @RequestParam(value = "ano") int ano
            , @RequestParam(value = "idProductos") int[] idProductos
            , @RequestParam(value = "cantidades") int[] cantidades) {  
        
        Date date = new Date();
        date.setDate(dia);
        date.setMonth(mes);
        date.setYear(ano);
        
        try {
            Tendero tendero = superStuff.cargarTenderoPorId(idTendero);
            Factura factura = superStuff.registrarPedido(idTienda, date, idProductos, cantidades);
            return realizarPago(numTarjeta, securityCode, tendero.getNombre(), String.valueOf(factura.getValor()));
        } catch (Exception ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Error";
    }

    private String realizarPago(String numTarjeta, String securityCode, String nombre, String cantidad) {
        try {
            String stringurl = "https://pasarelacosw.herokuapp.com/rest/PAYPAL/pago/tarjeta/" + numTarjeta +
                    "/" + nombre +"/VISA/" + securityCode + "/correoFalso" + "/monto/" + cantidad + "/seguridad/3/SuperStuff";
            URL url = new URL(stringurl);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.close();
            
            return getStringFromInputStream(httpCon.getInputStream());
        } catch (MalformedURLException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
