/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "carrusel")
@NamedQueries({
    @NamedQuery(name = "Carrusel.findAll", query = "SELECT c FROM Carrusel c"),
    @NamedQuery(name = "Carrusel.findById", query = "SELECT c FROM Carrusel c WHERE c.id = :id"),
    @NamedQuery(name = "Carrusel.findByPosicion", query = "SELECT c FROM Carrusel c WHERE c.posicion = :posicion")})
public class Carrusel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "POSICION", nullable = false)
    private int posicion;
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Noticia noticia;

    public Carrusel() {
    }

    public Carrusel(Integer id) {
        this.id = id;
    }

    public Carrusel(Integer id, int posicion) {
        this.id = id;
        this.posicion = posicion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrusel)) {
            return false;
        }
        Carrusel other = (Carrusel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Carrusel[id=" + id + "]";
    }

}
