package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * MetaDatos generated by hbm2java
 */
@Entity
@Table(name="MetaDatos"
    , uniqueConstraints = @UniqueConstraint(columnNames="palabraClave") 
)
public class MetaDato  implements java.io.Serializable {

     private Integer idMetaDatos;
     private String palabraClave;

    public MetaDato() {
    }
	
    public MetaDato(String palabraClave) {
        this.palabraClave = palabraClave;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="idMetaDatos", unique=true, nullable=false)
    public Integer getIdMetaDatos() {
        return this.idMetaDatos;
    }
    
    public void setIdMetaDatos(Integer idMetaDatos) {
        this.idMetaDatos = idMetaDatos;
    }

    
    @Column(name="palabraClave", unique=true, nullable=false, length=45)
    public String getPalabraClave() {
        return this.palabraClave;
    }
    
    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }
}


