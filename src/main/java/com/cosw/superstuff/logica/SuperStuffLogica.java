/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.logica;

import com.cosw.superstuff.persistencia.Categoria;
import com.cosw.superstuff.persistencia.DetalleCompraId;
import com.cosw.superstuff.persistencia.Lugar;
import com.cosw.superstuff.persistencia.Producto;
import com.cosw.superstuff.rep.RepositorioEnvios;
import com.cosw.superstuff.rep.RepositorioProductos;
import com.cosw.superstuff.rep.RepositorioProveedores;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HOMERO
 */
@Service
public class SuperStuffLogica {
    
    @Autowired
    private RepositorioProveedores repositorioProveedores;
    
    @Autowired
    private RepositorioProductos repositorioProductos;
    
    @Autowired
    private RepositorioEnvios repositorioEnvios;
    
    /**
     * Registra un nuevo producto
     * @param id El nuevo id que se registrara
     * @param proveedor El proveedor dueño de este producto
     * @param categoria La categoria a la que pertenece el producto
     * @param descuento El descuento aplicable a este producto
     * @param precio El precio de este producto
     * @param descripcion La descripcion caracteristica de este producto
     */
    public void registrarProducto(int id, int proveedor, int categoria, int descuento, int precio, String descripcion) {
    }
    
    /**
     * Trae todos los productos correspondiente a la categoria que llega por parametro
     * @param categoria El id de la categoria existente
     * @return Una List con los productos
     */
    public List<Producto> cargarProductosPorCategoria(int categoria){
        return null;
    }
    
    /**
     * Trae la lista de todos los productos existentes
     * @return La lista de los productos
     */
    public List<Producto> cargarTodosLosProductos(){
        return null;
    }
    
    /**
     * Trae el producto correspondiente al id del proveedor
     * @param proveedor El id del proveedor
     * @return La lista de los productos filtrados por proveedor
     */
    public List<Producto> cargarProductosPorProveedor(int proveedor){
        return null;
    }
    
    /**
     * Trae todas las categorias en existencia
     * @return Una lista de categorias
     */
    public List<Categoria> cargarCategorias(){
        return null;
    }
    
    /**
     * Registra un nuevo pedido con los detalles de compra correspondiente
     * @param direccionEnvio La direccion del envio
     * @param fechaLlegada La fecha en la que deberia llegar el producto
     * @param detalles Los detalles de pedido que van a ser asociados a este pedido
     * @return El id del nuevo pedido registrado
     */
    public int registrarPedido(String direccionEnvio, Date fechaLlegada, DetalleCompraId[] detalles){
        return 0;
    }
    
    /**
     * Registra un nuevo envio
     * @param pedidos Los pedidos asociados a este envio
     * @return El id del nuevo envio registrado
     */
    public int registrarEnvio(Set pedidos){
        return 0;
    }
    
    /**
     * Actualiza el estado del envio
     * @param idEnvio el id del envio el cual se le va a actualizar el estado
     * @param lugar Donde se encuentra el envio
     * @param estado Estado que corresponde a las variables estadicas de Estado
     * @param descripcion La descripcion 
     * @param coords Las coordenadas de la ubicacion exacta del envio
     */
    public void actualizarEstadoDeEnvio(int idEnvio, Lugar lugar, String estado, String descripcion, String coords){
        
    }
    
    /**
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
    public int crearNuevoProveedor(int idProveedores, Lugar lugares, String razonSocial, String direccion, String contactoTelefonico, String sitioWeb, String email){
        return 0;
    }
    
    /**
     * Crea un nuevo tendero
     * @param idTendero La cedula del tendero
     * @param nombre nombre del tendero
     */
    public void crearNuevoTendero(int idTendero, String nombre){
    }
}
