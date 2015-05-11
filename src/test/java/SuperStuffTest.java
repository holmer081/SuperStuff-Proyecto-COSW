/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Categoria;
import com.cosw.superstuff.persistencia.Descuento;
import com.cosw.superstuff.persistencia.DetalleCompra;
import com.cosw.superstuff.persistencia.DetalleCompraId;
import com.cosw.superstuff.persistencia.Envio;
import com.cosw.superstuff.persistencia.EstadoEnvio;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Pais;
import com.cosw.superstuff.persistencia.Pedido;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tendero;
import com.cosw.superstuff.rep.RepositorioCategorias;
import com.cosw.superstuff.rep.RepositorioDescuentos;
import com.cosw.superstuff.rep.RepositorioDetalleCompra;
import com.cosw.superstuff.rep.RepositorioEnvios;
import com.cosw.superstuff.rep.RepositorioEstadoEnvios;
import com.cosw.superstuff.rep.RepositorioLugares;
import com.cosw.superstuff.rep.RepositorioPaises;
import com.cosw.superstuff.rep.RepositorioPedidos;
import com.cosw.superstuff.rep.RepositorioProductos;
import com.cosw.superstuff.rep.RepositorioProveedores;
import com.cosw.superstuff.rep.RepositorioTenderos;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.IteratorUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author HOMERO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextH2.xml"})
public class SuperStuffTest {
    
    @Autowired
    private SuperStuffLogica superStuff;
    
    @Autowired
    private RepositorioLugares repositorioLugares;
    
    @Autowired
    private RepositorioDetalleCompra repositorioDetalleCompra;
    
    @Autowired
    private RepositorioPaises repositorioPaises;
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioDescuentos repositorioDescuentos;
    
    @Autowired
    private RepositorioCategorias repositorioCategorias;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioPedidos repositorioPedidos;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    @Autowired
    private RepositorioEstadoEnvios repositorioEstadoEnvios;
    
    @Autowired
    private RepositorioTenderos repositorioTenderos;
    
    private static boolean DATOSPREPARADOS = false;
    
    public SuperStuffTest() {
    }
    
    @Before
    public void setUp() {
        repositorioDetalleCompra.deleteAll();
        repositorioProductos.deleteAll();
        repositorioProveedores.deleteAll();
        repositorioCategorias.deleteAll();
        repositorioDescuentos.deleteAll();
        repositorioEstadoEnvios.deleteAll();
        repositorioEnvios.deleteAll();
        repositorioPedidos.deleteAll();
        repositorioPaises.deleteAll();
        repositorioLugares.deleteAll(); 
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * prueba para crear un nuevo proveedor
     */
    @Test
    public void crearNuevoProveedorTest(){
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Pais p2 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        newPlaces.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        newPlaces.add(new Lugar(p1,"Bogota","Las Orquideas"));
        
        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        repositorioPaises.save(p2);
        
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        
        superStuff.crearNuevoProveedor(new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com", "p1", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com", "p2", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com", "p3", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com", "p5", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com", "p6", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com", "p7", "1234"));
            
        List<Proveedor> proveedores = superStuff.cargarTodosLosProveedores();
        assertEquals("La clase logica ha cargado 6 proveedores?", 6, proveedores.size());
    }
    
    /**
     * prueba para cargar productos por proveedor
     */
    @Test
    public void cargarProductosPorProveedorTest(){
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Pais p2 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        newPlaces.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        newPlaces.add(new Lugar(p1,"Bogota","Las Orquideas"));
        
        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        repositorioPaises.save(p2);
        
        boolean prueba = true;
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        superStuff.crearNuevoProveedor(new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com" , "p1", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com" , "p2", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com", "p3", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com", "p4", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com", "p5", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com", "p6", "1234"));
        
           
        repositorioCategorias.save(new Categoria(1, "Frutas", "Categoria que agrupa las frutas"));
        repositorioCategorias.save(new Categoria(100, "Alcohol", "Categoria que agrupa Bebidas Alcoholicas"));
        repositorioDescuentos.save(new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%"));
        repositorioDescuentos.save(new Descuento(100, new Date(), new Date(), "Esto es un descuento del 10%"));
        
        Categoria c = repositorioCategorias.findOne(1);
        Categoria c1 = repositorioCategorias.findOne(100);
        Iterable<Descuento> descuentos = repositorioDescuentos.findAll();
        Descuento d = descuentos.iterator().next();
        Descuento d1 = descuentos.iterator().next();
        Proveedor p = repositorioProveedores.findOne(1);
        Proveedor pr2 = repositorioProveedores.findOne(2);
        Proveedor pr3 = repositorioProveedores.findOne(3);
        Proveedor pr4 = repositorioProveedores.findOne(4);
        Proveedor pr5 = repositorioProveedores.findOne(5);
        Proveedor pr6 = repositorioProveedores.findOne(6);
        repositorioProductos.save(new Producto(1, c1, d1, "Jack Daniel´s Whiskey Old Time", p, 1000000));
        repositorioProductos.save(new Producto(2, c1, d1, "Cerveza Aguila", p, 1000000));
        repositorioProductos.save(new Producto(3, c1, d1, "Aguardiente Antioqueño", p, 1000000));
        repositorioProductos.save(new Producto(4, c1, d1, "Vino Cariñoso", p, 1000000));
        repositorioProductos.save(new Producto(5, c1, d1, "Aguardiente Blanco del Valle Ice", p, 1000000));
        repositorioProductos.save(new Producto(6, c1, d1, "Baileys Irish Cream", p, 1000000));
        repositorioProductos.save(new Producto(7, c1, d1, "A", pr2, 1000000));
        repositorioProductos.save(new Producto(8, c1, d1, "B", pr3, 1000000));
        repositorioProductos.save(new Producto(9, c1, d1, "C", pr4, 1000000));
        repositorioProductos.save(new Producto(10, c1, d1, "D", pr5, 1000000));
        repositorioProductos.save(new Producto(11, c1, d1, "E", pr6, 1000000));
        repositorioProductos.save(new Producto(12, c1, d1, "F", pr6, 1000000));
               
        List<Producto> producto = superStuff.cargarProductosPorProveedor(1);
        
        for (Producto producto1 : producto) {
            if(producto1.getProveedores().getIdProveedores() != 1){
                prueba = false;
            }
        }assertTrue("Algun producto tiene un proveedor disntinto al solicitado", prueba);
    }
    
    /**
     * prueba para registrar un tendero
     */
    @Test
    public void crearNuevoTenderoTest(){
        repositorioTenderos.save(new Tendero(1, "Homero J Simpson", "default", "default"));
        repositorioTenderos.save(new Tendero(2, "Marge Simpson", "default", "default"));
        repositorioTenderos.save(new Tendero(3, "Lisa Simpson", "default", "default"));
        repositorioTenderos.save(new Tendero(4, "Bart Simpson", "default", "default"));
        repositorioTenderos.save(new Tendero(5, "Nelson Muntz", "default", "default"));
        repositorioTenderos.save(new Tendero(6, "Seymour Skinner", "default", "default"));
        repositorioTenderos.save(new Tendero(7, "Moe Szyslak", "default", "default"));
        List<Tendero> tenderos = (List<Tendero>)repositorioTenderos.findAll();
        assertEquals("Hay 7 tenderos?", 7, tenderos.size());
    }
    
    /**
     * Prueba para registrar un producto
     */
    @Test
    public void registrarProductoTest(){     
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Pais p2 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        newPlaces.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        newPlaces.add(new Lugar(p1,"Bogota","Las Orquideas"));
        
        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        repositorioPaises.save(p2);
        
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        
        repositorioCategorias.save(new Categoria(1, "Frutas", "Categoria que agrupa las frutas"));
        repositorioCategorias.save(new Categoria(100, "Alcohol", "Categoria que agrupa Bebidas Alcoholicas"));
        repositorioDescuentos.save(new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%"));
        repositorioDescuentos.save(new Descuento(100, new Date(), new Date(), "Esto es un descuento del 10%"));
        
        superStuff.crearNuevoProveedor(new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com" , "p1", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com", "p2", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com" , "p3", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com" , "p4", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com" , "p5", "1234"));
        superStuff.crearNuevoProveedor(new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com", "p6", "1234"));
        
        Proveedor p = repositorioProveedores.findOne(1);
        Categoria c1 = repositorioCategorias.findOne(100);
        Iterable<Descuento> d1 = repositorioDescuentos.findAll();
        superStuff.registrarProducto(new Producto(1, c1, d1.iterator().next(), "Jack Daniel´s Whiskey Old Time", p, 1000000));
        Producto producto = repositorioProductos.findOne(1);
        
        assertEquals("Ha cargado la descripcion del producto?", "Jack Daniel´s Whiskey Old Time", producto.getDescripcion());
        assertNotNull("La categoria del producto no debe ser nula", producto.getCategoria());
        assertNotNull("El descuento del producto no debe ser nulo", producto.getDescuentos());
        assertNotNull("El proveedor del producto no debe ser nulo", producto.getProveedores());
        assertEquals("El precio del producto es 1000000?", 1000000, producto.getPrecioLista());
    }
    
    /**
     * Prueba para registrar un pedido
     */
    @Test
    public void registrarPedido() {
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Pais p2 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        newPlaces.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        newPlaces.add(new Lugar(p1,"Bogota","Las Orquideas"));
        
        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        repositorioPaises.save(p2);
        
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        Proveedor pr1 = new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com" , "p1", "1234");
        Proveedor pr2 = new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com" , "p2", "1234");
        Proveedor pr3 = new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com" , "p3", "1234");
        Proveedor pr4 = new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com" , "p4", "1234");
        Proveedor pr5 = new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com" , "p5", "1234");
        Proveedor pr6 = new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com" , "p6", "1234");
        
        superStuff.crearNuevoProveedor(pr1);
        superStuff.crearNuevoProveedor(pr2);
        superStuff.crearNuevoProveedor(pr3);
        superStuff.crearNuevoProveedor(pr4);
        superStuff.crearNuevoProveedor(pr5);
        superStuff.crearNuevoProveedor(pr6);

        Categoria c1 = new Categoria(1, "Frutas", "Categoria que agrupa las frutas");
        Categoria c2 = new Categoria(2, "Alcohol", "Categoria que agrupa Bebidas Alcoholicas");
        Descuento d1 = new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%");
        Descuento d2 = new Descuento(50, new Date(), new Date(), "Esto es un descuento del 50%");
        
        repositorioCategorias.save(c1);
        repositorioCategorias.save(c2);
        repositorioDescuentos.save(d1);
        repositorioDescuentos.save(d2);
        
        repositorioProductos.save(new Producto(1, c1, d1, "Jack Daniel´s Whiskey Old Time", pr1, 1000000));
        repositorioProductos.save(new Producto(2, c1, d1, "Cerveza Aguila", pr2, 1000000));
        repositorioProductos.save(new Producto(3, c1, d1, "Aguardiente Antioqueño", pr3, 1000000));
        repositorioProductos.save(new Producto(4, c1, d1, "Vino Cariñoso", pr4, 1000000));
        repositorioProductos.save(new Producto(5, c1, d1, "Aguardiente Blanco del Valle Ice", pr5, 1000000));
        repositorioProductos.save(new Producto(6, c1, d1, "Baileys Irish Cream", pr6, 1000000));
        
        int[] idProductos = {1,2,3,4,5};
        int[] cantidades = {20,15,5,40,10};
        
        int[] idProductos2 = {3,4,6,1};
        int[] cantidades2 = {10,20,30,40};
        
        try {
            superStuff.registrarPedido("Calle 159a #13a-46", new Date(), idProductos, cantidades);
            superStuff.registrarPedido("Calle 142 #13-62", new Date(), idProductos2, cantidades2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        Iterator<Pedido> myIterator = repositorioPedidos.findAll().iterator();
        List<Pedido> myList = IteratorUtils.toList(myIterator);
                
        assertEquals("El numero de pedidos registrados fue de 2", 2, myList.size());
    }
    
    /**
     * Prueba para registrar un envio
     */
    @Test
    public void registrarNuevoEnvio() {
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Pais p2 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        
        Set<Lugar> newPlaces = new LinkedHashSet<>();
        newPlaces.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        newPlaces.add(new Lugar(p1,"Bogota","Las Orquideas"));
        
        p1.setLugares(newPlaces);
        repositorioPaises.save(p1);
        repositorioPaises.save(p2);
        
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        Proveedor pr1 = new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com" , "p1", "1234");
        Proveedor pr2 = new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com" , "p2", "1234");
        Proveedor pr3 = new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com" , "p3", "1234");
        Proveedor pr4 = new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com" , "p4", "1234");
        Proveedor pr5 = new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com" , "p5", "1234");
        Proveedor pr6 = new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com" , "p6", "1234");
        
        superStuff.crearNuevoProveedor(pr1);
        superStuff.crearNuevoProveedor(pr2);
        superStuff.crearNuevoProveedor(pr3);
        superStuff.crearNuevoProveedor(pr4);
        superStuff.crearNuevoProveedor(pr5);
        superStuff.crearNuevoProveedor(pr6);

        Categoria c1 = new Categoria(1, "Frutas", "Categoria que agrupa las frutas");
        Categoria c2 = new Categoria(2, "Alcohol", "Categoria que agrupa Bebidas Alcoholicas");
        Descuento d1 = new Descuento(0, new Date(), new Date(), "Esto es un descuento del 0%");
        Descuento d2 = new Descuento(50, new Date(), new Date(), "Esto es un descuento del 50%");
        
        repositorioCategorias.save(c1);
        repositorioCategorias.save(c2);
        repositorioDescuentos.save(d1);
        repositorioDescuentos.save(d2);
        
        repositorioProductos.save(new Producto(1, c1, d1, "Jack Daniel´s Whiskey Old Time", pr1, 1000000));
        repositorioProductos.save(new Producto(2, c1, d1, "Cerveza Aguila", pr2, 1000000));
        repositorioProductos.save(new Producto(3, c1, d1, "Aguardiente Antioqueño", pr3, 1000000));
        repositorioProductos.save(new Producto(4, c1, d1, "Vino Cariñoso", pr4, 1000000));
        repositorioProductos.save(new Producto(5, c1, d1, "Aguardiente Blanco del Valle Ice", pr5, 1000000));
        repositorioProductos.save(new Producto(6, c1, d1, "Baileys Irish Cream", pr6, 1000000));
        
        int[] idProductos = {1,2,3,4,5};
        int[] cantidades = {20,15,5,40,10};
        
        int[] idProductos2 = {3,4,6,1};
        int[] cantidades2 = {10,20,30,40};
        
        try {
            superStuff.registrarPedido("Calle 159a #13a-46", new Date(), idProductos, cantidades);
            superStuff.registrarPedido("Calle 142 #13-62", new Date(), idProductos2, cantidades2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        Iterator<Pedido> myIterator = repositorioPedidos.findAll().iterator();
        List<Pedido> myList = IteratorUtils.toList(myIterator);
        int[] idPedidos = new int[myList.size()];
        
        for (int i = 0; i < myList.size(); i++) {
            idPedidos[i] = myList.get(i).getIdPedidos();
        }
        
        int i = superStuff.registrarEnvio(idPedidos);
        
        Envio envio = repositorioEnvios.findOne(i);
        
        assertEquals("El numero de pedidos registrados fue de 2", 2, envio.getPedidos().size());
    }
}
