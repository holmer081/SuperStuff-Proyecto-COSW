package com.cosw.superstuff.persistencia;
// Generated Feb 18, 2015 10:14:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Categorias generated by hbm2java
 */
@Entity
@Table(name="Categorias"
    , uniqueConstraints = @UniqueConstraint(columnNames="categoria") 
)
public class Categoria  implements java.io.Serializable {

     private int idCategorias;
     private String categoria;
     private String descripcion;

    public Categoria() {
    }

    public Categoria(int idCategorias, String categoria, String descripcion) {
        this.idCategorias = idCategorias;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    @Id 
    @Column(name="idCategorias", unique=true, nullable=false)
    public int getIdCategorias() {
        return this.idCategorias;
    }
    
    public void setIdCategorias(int idCategorias) {
        this.idCategorias = idCategorias;
    }

    
    @Column(name="categoria", unique=true, nullable=false, length=45)
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    @Column(name="descripcion", nullable=false, length=500)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


