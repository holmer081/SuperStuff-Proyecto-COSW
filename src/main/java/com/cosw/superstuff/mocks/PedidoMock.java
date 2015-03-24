/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cosw.superstuff.mocks;

/**
 *
 * @author Camilo
 */
public interface PedidoMock {
    
    /**
     * Monta un nuevo pedido en la empresa de envio dada
     * @param idEmpresaEnvio El identificador de la empresa de envio
     * @param pedido El indentificador del pedido que se asociara a esta empresa
     * @return false - en caso de que no se pueda agregar el pedido a esta empresa
     * @throws java.lang.Exception en Caso de cualquier error
     */
    public boolean montarPedido(int idEmpresaEnvio, int pedido) throws Exception;
}
