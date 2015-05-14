package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tiendas generated by hbm2java
 */
@Entity
@Table(name="Tiendas")
public class Tienda  implements java.io.Serializable {

    private int idTiendas;
    private Lugar lugares;
    private String direccion;
    private Set<Factura> facturases = new HashSet<>(0);

    public Tienda() {
    }
	
    public Tienda(int idTiendas, Lugar lugares, String direccion) {
        this.idTiendas = idTiendas;
        this.lugares = lugares;
        this.direccion = direccion;
    }
    public Tienda(int idTiendas, Lugar lugares, String direccion, Set<Factura> facturases) {
       this.idTiendas = idTiendas;
       this.lugares = lugares;
       this.direccion = direccion;
       this.facturases = facturases;
    }
   
    @Id 
    @Column(name="idTiendas", unique=true, nullable=false)
    public int getIdTiendas() {
        return this.idTiendas;
    }
    
    public void setIdTiendas(int idTiendas) {
        this.idTiendas = idTiendas;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Lugar_idLugar")
    public Lugar getLugares() {
        return this.lugares;
    }
    
    public void setLugares(Lugar lugares) {
        this.lugares = lugares;
    }
    
    @Column(name="direccion", nullable=false, length=45)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "tienda")
    public Set<Factura> getFacturases() {
        return this.facturases;
    }
    
    public void setFacturases(Set<Factura> facturases) {
        this.facturases = facturases;
    }
}


