/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.main;

import com.cosw.superstuff.persistencia.Banco;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Pais;
import com.cosw.superstuff.persistencia.Pedido;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tienda;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session=sessionFactory.openSession();        
        Transaction tx=session.beginTransaction();
   
        //Aqui va codigo de pruebas
        
        int[] idProductos = {1,3,5,6,8};
        int[] cantidades = {10,20,30,40,50};
        
        try {
            Funciones.registroPedido(session, "Calle 147# 13 - 62", new Date(), idProductos, cantidades);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Query q2 = session.createQuery("SELECT e.pedidos from Envio e inner join e.pedidos as p inner join p.detalleCompras as detalleP inner join detalleP.productos as prod inner join prod.proveedores as prov where (prov.idProveedores = 1) and (day(e.fechaSalida) < day(:finalDate)) GROUP BY prov");
        Date fecha = new Date();
        Date fechaFinal = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate()-10);
        
        
        q2.setParameter("finalDate", fechaFinal);
        List<Pedido> p = q2.list();
        
         
       System.out.println(p.toString());
        System.out.println(fechaFinal);
        
        for (Pedido p1 : p) {
            System.out.println("Fecha" + p1.getFechaLlegada().toString());
        }
        
        tx.commit();
        session.close();
    }
    
}
