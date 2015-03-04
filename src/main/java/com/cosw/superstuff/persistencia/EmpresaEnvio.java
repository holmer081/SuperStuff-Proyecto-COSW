package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EmpresasEnvio generated by hbm2java
 */
@Entity
@Table(name="EmpresasEnvio")
public class EmpresaEnvio  implements java.io.Serializable {
    
     private int idEmpresasEnvio;
     private Lugar lugares;
     private String nombre;
     private String direccion;
     private String contactoTelefonico;
     private String sitioWeb;
     private String correo;
     private Set<Envio> envios = new HashSet(0);

    public EmpresaEnvio() {
    }

	
    public EmpresaEnvio(int idEmpresasEnvio, Lugar lugares, String nombre, String direccion, String contactoTelefonico, String sitioWeb, String correo) {
        this.idEmpresasEnvio = idEmpresasEnvio;
        this.lugares = lugares;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contactoTelefonico = contactoTelefonico;
        this.sitioWeb = sitioWeb;
        this.correo = correo;
    }
    public EmpresaEnvio(int idEmpresasEnvio, Lugar lugares, String nombre, String direccion, String contactoTelefonico, String sitioWeb, String correo, Set<Envio> envioses) {
       this.idEmpresasEnvio = idEmpresasEnvio;
       this.lugares = lugares;
       this.nombre = nombre;
       this.direccion = direccion;
       this.contactoTelefonico = contactoTelefonico;
       this.sitioWeb = sitioWeb;
       this.correo = correo;
       this.envios = envioses;
    }
   
    @Id
    @Column(name="idEmpresasEnvio", unique=true, nullable=false)
    public int getIdEmpresasEnvio() {
        return this.idEmpresasEnvio;
    }
    
    public void setIdEmpresasEnvio(int idEmpresasEnvio) {
        this.idEmpresasEnvio = idEmpresasEnvio;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Lugar_idLugar", nullable=false)
    public Lugar getLugares() {
        return this.lugares;
    }
    
    public void setLugares(Lugar lugares) {
        this.lugares = lugares;
    }

    
    @Column(name="nombre", nullable=false, length=45)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="direccion", nullable=false, length=45)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Column(name="contactoTelefonico", nullable=false, length=45)
    public String getContactoTelefonico() {
        return this.contactoTelefonico;
    }
    
    public void setContactoTelefonico(String contactoTelefonico) {
        this.contactoTelefonico = contactoTelefonico;
    }

    
    @Column(name="sitioWeb", nullable=false, length=45)
    public String getSitioWeb() {
        return this.sitioWeb;
    }
    
    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    
    @Column(name="correo", nullable=false, length=45)
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @OneToMany(fetch=FetchType.LAZY)
    public Set<Envio> getEnvios() {
        return this.envios;
    }
    
    public void setEnvios(Set<Envio> envios) {
        this.envios = envios;
    }
}


