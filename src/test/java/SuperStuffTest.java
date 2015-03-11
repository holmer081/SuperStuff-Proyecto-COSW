/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cosw.superstuff.logica.SuperStuffLogica;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Pais;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.rep.RepositorioLugares;
import com.cosw.superstuff.rep.RepositorioPaises;
import com.cosw.superstuff.rep.RepositorioProveedores;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
    private RepositorioPaises repositorioPaises;
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    public SuperStuffTest() {
    }
    
    @Before
    public void setUp() {
        Pais p = new Pais("Francia", "PA", "FR", Pais.SIHAYCOBERTURA);
        Pais p1 = new Pais("Colombia", "COL", "ESPAÃ‘OL", Pais.SIHAYCOBERTURA);
        Set<Lugar> lugares = new LinkedHashSet<>();
        lugares.add(new Lugar(p1, "BogotÃ¡", "Cedritos"));
        p1.setLugares(lugares);
        repositorioPaises.save(p1);
        repositorioPaises.save(p);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * 
     */
    @Test
    public void crearNuevoProveedorTest(){
        List<Lugar> lugares = (List<Lugar>)repositorioLugares.findAll();
        superStuff.crearNuevoProveedor(new Proveedor(1, lugares.get(0), "Licorera El Tio Moe", "Calle Falsa 123", "3044463405", "www.eltiomoe.com", "eltiomoe@mail.com"));
        superStuff.crearNuevoProveedor(new Proveedor(2, lugares.get(0), "Jabones Mr. Chispa", "Calle Falsa 121", "3044463404", "www.mrchispa.com", "mrchispa@mail.com"));
        superStuff.crearNuevoProveedor(new Proveedor(3, lugares.get(0), "Alpina", "Calle Falsa 124", "3045463402", "www.prueba.com", "prueba@mail.com"));
        superStuff.crearNuevoProveedor(new Proveedor(4, lugares.get(0), "Ramo", "Calle Falsa 125", "3045463403", "www.prueba.com", "prueba@mail.com"));
        superStuff.crearNuevoProveedor(new Proveedor(5, lugares.get(0), "Telas ECI", "Calle Falsa 126", "3045463404", "www.prueba.com", "prueba@mail.com"));
        superStuff.crearNuevoProveedor(new Proveedor(6, lugares.get(0), "Lacteos ECI", "Calle Falsa 127", "3045463405", "www.prueba.com", "prueba@mail.com"));
        List<Proveedor> proveedores = superStuff.cargarTodosLosProveedores();
        assertEquals("La clase logica ha cargado 6 proveedores?", 6, proveedores.size());
    }
    
    @Test
    public void registrarProductoTest(){
        
    }
    
    @Test
    public void cargarProductosPorProveedorTest(){
    
    }
    
    @Test
    public void crearNuevoTenderoTest(){
        
    }
    
}
