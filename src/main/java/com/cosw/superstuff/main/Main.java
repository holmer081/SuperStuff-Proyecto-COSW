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
        
        Query q2 = session.createQuery("SELECT t from Tendero tend INNER JOIN tend.tiendas t WHERE tend.idTenderos = 4961");
        
        List<Tienda> p = q2.list();
        
        for (Tienda p1 : p) {
            System.out.println("Fecha" + p1.getNombre());
        }
        
        tx.commit();
        session.close();
    }
    
}
