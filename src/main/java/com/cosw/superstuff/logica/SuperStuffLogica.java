/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.logica;

import com.cosw.superstuff.persistencia.Categoria;
import com.cosw.superstuff.persistencia.Descuento;
import com.cosw.superstuff.persistencia.DetalleCompra;
import com.cosw.superstuff.persistencia.DetalleCompraId;
import com.cosw.superstuff.persistencia.Envio;
import com.cosw.superstuff.persistencia.Factura;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Pedido;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.persistencia.Proveedor;
import com.cosw.superstuff.persistencia.Tendero;
import com.cosw.superstuff.persistencia.Tienda;
import com.cosw.superstuff.rep.RepositorioCategorias;
import com.cosw.superstuff.rep.RepositorioDescuentos;
import com.cosw.superstuff.rep.RepositorioDetalleCompra;
import com.cosw.superstuff.rep.RepositorioEnvios;
import com.cosw.superstuff.rep.RepositorioEstadoEnvios;
import com.cosw.superstuff.rep.RepositorioFacturas;
import com.cosw.superstuff.rep.RepositorioPedidos;
import com.cosw.superstuff.rep.RepositorioProductos;
import com.cosw.superstuff.rep.RepositorioProveedores;
import com.cosw.superstuff.rep.RepositorioTenderos;
import com.cosw.superstuff.rep.RepositorioTiendas;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Representa la clase logica
 * @author HOMERO
 */
@Service
public class SuperStuffLogica {
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioCategorias repositorioCategorias;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    @Autowired
    private RepositorioEstadoEnvios repositorioEstadoEnvios;
    
    @Autowired
    private RepositorioPedidos repositorioPedidos;
    
    @Autowired
    private RepositorioDetalleCompra repositorioDetalleCompra;
    
    @Autowired
    private RepositorioTenderos repositorioTenderos;
    
    @Autowired
    private RepositorioTiendas repositorioTiendas;
    
    @Autowired
    private RepositorioDescuentos repositorioDescuentos;   
    
    @Autowired
    private RepositorioFacturas repositorioFacturas;   
    
    /**
     * @author Holmer
     * Registra un nuevo producto
     * @param id El nuevo id que se registrara
     * @param proveedor El proveedor dueÃ±o de este producto
     * @param categoria La categoria a la que pertenece el producto
     * @param descuento El descuento aplicable a este producto
     * @param precio El precio de este producto
     * @param descripcion La descripcion caracteristica de este producto
     */
    public void registrarProducto(int id, int proveedor, int categoria, int descuento, int precio, String descripcion) {
        Proveedor prov = repositorioProveedores.findOne(Integer.valueOf(proveedor));
        Categoria cat = repositorioCategorias.findOne(Integer.valueOf(categoria));
        Descuento desc = repositorioDescuentos.findOne(Integer.valueOf(descuento));
        Producto producto = new Producto(id, cat, desc, descripcion, prov, precio);
        repositorioProductos.save(producto);
    }
    
    /**
     * @author Holmer
     * Registra un nuevo producto a partir de un objeto instanciado
     * @param producto 
     */
    public void registrarProducto(Producto producto){
        repositorioProductos.save(producto);
    }
    
    /**
     * @author Andres
     * Trae todos los productos correspondiente a la categoria que llega por parametro
     * @param categoria El id de la categoria existente
     * @return Una List con los productos
     */
    public List<Producto> cargarProductosPorCategoria(int categoria){
        return repositorioProductos.productosPorCategoria(categoria);
    }
    
    /**
     * @author Andres
     * Trae la lista de todos los productos existentes
     * @return La lista de los productos
     */
    public List<Producto> cargarTodosLosProductos(){
        return (List<Producto>) repositorioProductos.findAll();
    }
    
    /**
     * @author Holmer
     * Trae el producto correspondiente al id del proveedor
     * @param proveedor El id del proveedor
     * @return La lista de los productos filtrados por proveedor
     */
    public List<Producto> cargarProductosPorProveedor(int proveedor){
        List<Producto> productos = repositorioProductos.cargarProductosPorProveedor(proveedor);
        return productos;
    }
    
    /**
     * @author Andres
     * Trae todas las categorias en existencia
     * @return Una lista de categorias
     */
    public List<Categoria> cargarCategorias(){
        return (List<Categoria>) repositorioCategorias.findAll();
    }
    
    /**
     * @author Camilo
     * Registra un nuevo pedido con los detalles de compra correspondiente
     * @param idTienda
     * @param direccion La direccion del envio
     * @param fecha La fecha en la que deberia llegar el producto
     * @param idProductos La lista de los productos
     * @param cantidades Las cantidades
     * @return El valorTotal del pedido del nuevo pedido registrado
     * @throws java.lang.Exception En caso de que las cantidades no correspondan a los productos
     */
    public Factura registrarPedido(int idTienda, String direccion, Date fecha, int[] idProductos, int cantidades[]) throws Exception{
        if(idProductos.length != cantidades.length)
            throw new Exception("cantidades no corresponder al numero de productos a pedir");
        
        Tienda tienda = repositorioTiendas.findOne(idTienda);
        
        int valorPedido = 0;
        Set<DetalleCompra> detallesCompra = new HashSet<>();
        Pedido pedido = new Pedido(direccion, fecha, 0);
        repositorioPedidos.save(pedido);
        
        for (int i = 0; i < idProductos.length; i++) {
            int idProducto = idProductos[i];
            DetalleCompraId idDetalle = new DetalleCompraId(pedido.getIdPedidos(), idProducto);    
            Producto producto = repositorioProductos.findOne(idProducto);
            
            valorPedido += cantidades[i] * producto.getPrecioLista();
            
            DetalleCompra detalleCompra = new DetalleCompra(idDetalle, producto, cantidades[i], producto.getPrecioLista());
            detallesCompra.add(detalleCompra);
            
            repositorioDetalleCompra.save(detalleCompra);
        }
        
        pedido.setDetalleCompras(detallesCompra);
        pedido.setValorTotal(valorPedido);
        
        repositorioPedidos.save(pedido);
        
        Factura nuevaFactura = new Factura(1, valorPedido);
        nuevaFactura.setPedido(pedido);
        repositorioFacturas.save(nuevaFactura);
        
        tienda.getFacturases().add(nuevaFactura);
        repositorioTiendas.save(tienda);
        
        return nuevaFactura;
    }
    
    /**
     * @author Camilo
     * Registra un nuevo envio
     * @param idPedidos Los pedidos asociados a este envio
     * @return El id del nuevo envio registrado
     */
    public int registrarEnvio(int[] idPedidos){
        Envio envio = new Envio(new Date());
        Set<Pedido> pedidos = new HashSet<>();
        for (int idP : idPedidos) {
            pedidos.add(repositorioPedidos.findOne(idP));
        }
        envio.setPedidos(pedidos);
        repositorioEnvios.save(envio);
        return envio.getIdEnvio();
    }
    
    /**
     * @Author Andres
     * Actualiza el estado del envio
     * @param idEnvio el id del envio el cual se le va a actualizar el estado
     * @param lugar Donde se encuentra el envio
     * @param estado Estado que corresponde a las variables estadicas de Estado
     * @param descripcion La descripcion 
     * @param coords Las coordenadas de la ubicacion exacta del envio
     */
    public void actualizarEstadoDeEnvio(int idEnvio, Lugar lugar, String estado, String descripcion, String coords){
        repositorioEstadoEnvios.ActualizarEnvio(idEnvio,estado,descripcion,coords,lugar);
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo proveedor
     * @param idProveedores El id del proveedor
     * @param lugares la ubicacion geografica del proveedor
     * @param razonSocial Razon social del proveedor
     * @param direccion Direccion de oficina del proveedor
     * @param contactoTelefonico El numero telefonocio de este proveedor
     * @param sitioWeb El sitio web del proveedor
     * @param email Mail por si acaso
     * @return El id del nuevo proveedor
     */
    public int crearNuevoProveedor(int idProveedores, Lugar lugares, String razonSocial, String direccion, String contactoTelefonico, String sitioWeb, String email, String nombreUsuario, String contrasena){
        Proveedor p = new Proveedor(idProveedores, lugares, razonSocial, direccion, contactoTelefonico, sitioWeb, email, nombreUsuario, contrasena);
        repositorioProveedores.save(p);
        return p.getIdProveedores();
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo proveedor
     * @param proveedor
     * @return id del proveedo, una vez tiene un estado persistente
     */
    public int crearNuevoProveedor(Proveedor proveedor){
        repositorioProveedores.save(proveedor);
        return proveedor.getIdProveedores();
    }
    
    /**
     * @Author Holmer
     * Crea un nuevo tendero
     * @param idTendero La cedula del tendero
     * @param nombre nombre del tendero
     */
    public void crearNuevoTendero(int idTendero, String nombre, String usuario, String contrasena){
        Tendero t = new Tendero(idTendero, nombre, usuario, contrasena);
        repositorioTenderos.save(t);
    }
    
    /**
     * Carga todos los proveedores
     */
    public List<Proveedor> cargarTodosLosProveedores(){
        return (List<Proveedor>) repositorioProveedores.findAll();
    }

    /**
     * @Author Holmer
     * Crea un nuevo tendero
     * @param t n
     */
    public void crearNuevoTendero(Tendero t) {
       repositorioTenderos.save(t);
    }
    
    /**
     * Guarda un envio en la base de datos
     * @param envio 
     */
    public void registrarEnvio(Envio envio) {
        repositorioEnvios.save(envio);
    }

    /**
     * Carga un envio por su identificador
     * @param id
     * @return 
     */
    public Envio cargarEnvioPorId(int id) {
        return repositorioEnvios.findOne(id);
    }
    
    /**
     * Retorna una lista con todos los tenderos
     * @return lista de tenderos
     */
    public List<Tendero> cargarTenderos() {
        return (List<Tendero>) repositorioTenderos.findAll();
    }
    
    /**
     * Retorna una lista con todos los tenderos
     * @return lista de tenderos
     */
    public Tendero cargarTenderoPorId(int id) {
        return repositorioTenderos.findOne(id);
    }
    
    
    public Tendero iniciarSesionTendero(String usuario, String contrasena) {
        Tendero t = repositorioTenderos.obtenerTenderoPorCrendenciales(usuario, contrasena);
        return t;
    }
    
    public List<Pedido> getPedidos() {
        return (List<Pedido>)repositorioPedidos.findAll();
    }
}