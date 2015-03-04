package com.cosw.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cosw.superstuff.main.Funciones;
import com.cosw.superstuff.persistencia.Categoria;
import com.cosw.superstuff.persistencia.Descuento;
import com.cosw.superstuff.persistencia.DetalleCompra;
import com.cosw.superstuff.persistencia.DetalleCompraId;
import com.cosw.superstuff.persistencia.Envio;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Pais;
import com.cosw.superstuff.persistencia.Pedido;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ASUS
 */
public class TestsFunciones {
    
    private static SessionFactory sessionFactory;
    private static Session session = null;
    
    public TestsFunciones() {
    }

    @BeforeClass
    public static void setUp() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate-pruebas-h2.cfg.xml");
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
        configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session=sessionFactory.openSession(); 
        
        Pais p = new Pais("Francia", "PA", "FR", Pais.SIHAYCOBERTURA);
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> lugares = new LinkedHashSet<>();
        Lugar l = new Lugar(p, "Paris", "Louvrie");
        lugares.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        
        p1.setLugares(lugares);
        
        Proveedor proveedor = new Proveedor(1, l, "Hacemos zapatos", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor1 = new Proveedor(2, l, "Hacemos Dulce", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor2 = new Proveedor(3, l, "Hacemos Colonias", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor3 = new Proveedor(4, l, "Hacemos Pokemones", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor4 = new Proveedor(5, l, "Hacemos Casas", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor5 = new Proveedor(6, l, "Hacemos Carros", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor6 = new Proveedor(7, l, "Hacemos Aguacate", "Calle 123", "6701349", "NA", "NA");
        Proveedor proveedor7 = new Proveedor(8, l, "Hacemos Arroces", "Calle 123", "6701349", "NA", "NA");
        
        Categoria c = new Categoria(1, "Frutas", "Categoria que agrupa las frutas");
        Categoria c1 = new Categoria(100, "Alcohol", "Categoria que agrupa Bebidas Alcoholicas");
        Descuento d = new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%");
        Descuento d1 = new Descuento(100, new Date(), new Date(), "Esto es un descuento del 10%");
          
        session.save(d);
        session.save(d1);
        
        session.save(c);
        session.save(c1);
        
        session.save(p);
        session.save(p1);
        session.save(new Pais("Panama", "P", "ESPAÃ‘OL", "1"));
        session.save(new Pais("Chile", "CL", "ESPAÃ‘OL", "2"));
        
        session.save(l);
        
        session.save(proveedor);
        session.save(proveedor1);
        session.save(proveedor2);
        session.save(proveedor3);
        session.save(proveedor4);
        session.save(proveedor5);
        session.save(proveedor6);
        session.save(proveedor7);
        
        session.save(new Producto(1, c, d, "Banano", proveedor, 300));
        session.save(new Producto(2, c, d, "Manzana", proveedor1, 200));
        session.save(new Producto(3, c, d, "Pera", proveedor2, 200));
        session.save(new Producto(4, c, d, "Anana", proveedor3, 1000));
        session.save(new Producto(100, c, d, "Banano", proveedor4, 300));
        session.save(new Producto(200, c, d, "Manzana", proveedor5, 200));
        session.save(new Producto(300, c, d, "Pera", proveedor6, 200));
        session.save(new Producto(400, c, d, "Anana", proveedor7, 1000));
    }
    
    @AfterClass
    public static void finDePruebas() {
        session.close();
        sessionFactory.close();
    }

    /**
     * Esta prueba hara un test de la funcion RegistroPedido
     */
    @Test
    public void funcionRegistroPedido () throws Exception {
        String direccion = "Calle 159a #13a-16";     
        
        int[] idProductos = {1,2,3,4};
        int[] cantidades = {50,20,10,30};
        
        Funciones.registroPedido(session, direccion, new Date(), idProductos, cantidades);
       
        Pedido pedido = (Pedido)session.load(Pedido.class, 1);
        
        Assert.assertEquals("El numero de pedidos debe ser 4", idProductos.length, pedido.getDetalleCompras().size());
        Assert.assertEquals("El valor total del pedido debe ser 51000", 51000, pedido.getValorTotal());
    }
    
    /**
     * Esta funcion prueba la funcion RegistrarEnvio
     * @throws Exception 
     */
    @Test
    public void registroNuevoEnvio() throws Exception{
        int[] idPedidos = {2,3,4,5,6};
        int[] idProductos = {100,200,300,400};
        int[] cantidades = {50,20,10,30};
        
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, cantidades);
        
        int[] c2  = {1000,230,640,35};
        int[] c3  = {15,29,64,3500};
        int[] c4  = {59,50,640,650};
        int[] c5  = {60,226,640,2015};
        int[] c6  = {20,2000,640,35};
        
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, c2);       
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, c3);
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, c4);
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, c5);
        Funciones.registroPedido(session, "Calle Falsa 123", new Date(), idProductos, c6);
        
        int idEnvio = Funciones.registroEnvio(session, idPedidos, 1, new Date());
        
        Envio envioGuardado = (Envio) session.load(Envio.class, idEnvio);

        Assert.assertEquals("El envio tiene asociado 5 pedidos", 5, envioGuardado.getPedidos().size());
        Assert.assertEquals("El envio tiene asociado 1 estado", 1, envioGuardado.getEstadoEnvios().size());
    }
    
    
    @Test
    public void valorPagarProveedorPedido () throws Exception {
        Pedido pedido = (Pedido)session.load(Pedido.class, 2);
        List<Object[]> result = Funciones.valorPagarProveedorPedido(session, pedido.getIdPedidos());
        Assert.assertEquals("El resultado debe ser 15000 para el primer proveedor", 15000, (long)result.get(0)[1]); 
        Assert.assertEquals("El resultado debe ser 4000 para el segundo proveedor", 4000, (long)result.get(1)[1]); 
        Assert.assertEquals("El resultado debe ser 2000 para el tercer proveedor", 2000, (long)result.get(2)[1]); 
        Assert.assertEquals("El resultado debe ser 30000 para el cuarto proveedor", 30000, (long)result.get(3)[1]);    
    }
}
