package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Facturas generated by hbm2java
 */
@Entity
@Table(name="Facturas"
    , uniqueConstraints = @UniqueConstraint(columnNames={"idFacturas"})
)
public class Factura  implements java.io.Serializable {

    private int idFacturas;
    private long valor;
    private Pedido pedido;
            
    public Factura() {
    }

    public Factura(int idFacturas, long valor) {
       this.idFacturas = idFacturas;
       this.valor = valor;
    }
   
    @Id    
    @Column(name="idFacturas", unique=true, nullable=false)
    public int getIdFacturas() {
        return this.idFacturas;
    }
    
    public void setIdFacturas(int idFacturas) {
        this.idFacturas = idFacturas;
    }
    
    @Column(name="valor", nullable=false)
    public long getValor() {
        return this.valor;
    }
    
    public void setValor(long valor) {
        this.valor = valor;
    }

    @Column(name="Pedidos_idPedidos", nullable=false)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}