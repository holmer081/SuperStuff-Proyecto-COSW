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
public interface PagoMock {
    
    /**
     * Reliza un pago a una entidad bancaria
     * @param idEntidadOrigen El identificador de la entidad Bancaria origen
     * @param idEntidadDestino El identificador de la entidad Bancaria destino
     * @param valorTotal El monto total que se descontara de la cuenta origen y aumentara en la de destino
     * @param nmroCuentaOrigen El numero de la cuenta origen
     * @param nmroCuentaDestino El numero de la cuenta destino
     * @return El identificador de la factura realizada en el pago
     */
    public int realizarPago(int idEntidadOrigen, int idEntidadDestino, String nmroCuentaOrigen, String nmroCuentaDestino, long valorTotal);
}
