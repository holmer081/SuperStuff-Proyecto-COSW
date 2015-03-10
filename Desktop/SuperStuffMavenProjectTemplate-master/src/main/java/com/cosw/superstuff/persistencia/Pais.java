package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Paises generated by hbm2java
 */
@Entity
@Table(name="Paises")
public class Pais  implements java.io.Serializable {

    public static final String SIHAYCOBERTURA = "S";
    public static final String NOHAYCOBERTURA = "N";
    
    private String pais;
    private String abreviacion;
    private String idioma;
    private String cobertura;
    private Set<Lugar> lugares = new HashSet<>(0);

    public Pais() {
    }
	
    public Pais(String pais, String abreviacion, String idioma, String cobertura) {
        this.pais = pais;
        this.abreviacion = abreviacion;
        this.idioma = idioma;
        this.cobertura = cobertura;
    }
    
    public Pais(String pais, String abreviacion, String idioma, String cobertura, Set<Lugar> lugares) {
       this.pais = pais;
       this.abreviacion = abreviacion;
       this.idioma = idioma;
       this.cobertura = cobertura;
       this.lugares = lugares;
    }
   
    @Id 
    @Column(name="pais", unique=true, nullable=false, length=45)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Column(name="abreviacion", nullable=false, length=45)
    public String getAbreviacion() {
        return this.abreviacion;
    }
    
    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    
    @Column(name="idioma", nullable=false, length=45)
    public String getIdioma() {
        return this.idioma;
    }
    
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    @Column(name="cobertura", nullable=false, length=2)
    public String getCobertura() {
        return this.cobertura;
    }
    
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
    
    @OneToMany(fetch=FetchType.EAGER ,mappedBy="paises", cascade = CascadeType.ALL)
    public Set<Lugar> getLugares() {
        return this.lugares;
    }
    
    public void setLugares(Set<Lugar> lugares) {
        this.lugares = lugares;
    }
}


