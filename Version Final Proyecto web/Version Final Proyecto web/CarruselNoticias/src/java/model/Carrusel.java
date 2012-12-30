/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author johanna
 */
@Entity
@Table(name = "carrusel")
@NamedQueries({
    @NamedQuery(name = "Carrusel.findAll", query = "SELECT c FROM Carrusel c"),
    @NamedQuery(name = "Carrusel.findByIdcarrusel", query = "SELECT c FROM Carrusel c WHERE c.idcarrusel = :idcarrusel"),
    @NamedQuery(name = "Carrusel.findByPosicion", query = "SELECT c FROM Carrusel c WHERE c.posicion = :posicion"),
    @NamedQuery(name = "Carrusel.findByBrvideo", query = "SELECT c FROM Carrusel c WHERE c.brvideo = :brvideo"),
    @NamedQuery(name = "Carrusel.findByBraudio", query = "SELECT c FROM Carrusel c WHERE c.braudio = :braudio"),
    @NamedQuery(name = "Carrusel.findByNombrevideo", query = "SELECT c FROM Carrusel c WHERE c.nombrevideo = :nombrevideo"),
    @NamedQuery(name = "Carrusel.findByNombreaudio", query = "SELECT c FROM Carrusel c WHERE c.nombreaudio = :nombreaudio")})
public class Carrusel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCARRUSEL", nullable = false)
    private Integer idcarrusel;
    @Basic(optional = false)
    @Column(name = "POSICION", nullable = false)
    private int posicion;
    @Column(name = "BRVIDEO", length = 20)
    private String brvideo;
    @Column(name = "BRAUDIO", length = 20)
    private String braudio;
    @Column(name = "NOMBREVIDEO", length = 20)
    private String nombrevideo;
    @Column(name = "NOMBREAUDIO", length = 20)
    private String nombreaudio;
    @JoinColumn(name = "ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Noticia noticia;

    public Carrusel() {
    }

    public Carrusel(Integer idcarrusel) {
        this.idcarrusel = idcarrusel;
    }

    public Carrusel(Integer idcarrusel, int posicion) {
        this.idcarrusel = idcarrusel;
        this.posicion = posicion;
    }

    public Integer getIdcarrusel() {
        return idcarrusel;
    }

    public void setIdcarrusel(Integer idcarrusel) {
        this.idcarrusel = idcarrusel;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getBrvideo() {
        return brvideo;
    }

    public void setBrvideo(String brvideo) {
        this.brvideo = brvideo;
    }

    public String getBraudio() {
        return braudio;
    }

    public void setBraudio(String braudio) {
        this.braudio = braudio;
    }

    public String getNombrevideo() {
        return nombrevideo;
    }

    public void setNombrevideo(String nombrevideo) {
        this.nombrevideo = nombrevideo;
    }

    public String getNombreaudio() {
        return nombreaudio;
    }

    public void setNombreaudio(String nombreaudio) {
        this.nombreaudio = nombreaudio;
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
        hash += (idcarrusel != null ? idcarrusel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrusel)) {
            return false;
        }
        Carrusel other = (Carrusel) object;
        if ((this.idcarrusel == null && other.idcarrusel != null) || (this.idcarrusel != null && !this.idcarrusel.equals(other.idcarrusel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Carrusel[idcarrusel=" + idcarrusel + "]";
    }

}
