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
@Table(name = "composicion")
@NamedQueries({
    @NamedQuery(name = "Composicion.findAll", query = "SELECT c FROM Composicion c"),
    @NamedQuery(name = "Composicion.findByIdcomposicion", query = "SELECT c FROM Composicion c WHERE c.idcomposicion = :idcomposicion"),
    @NamedQuery(name = "Composicion.findByBrvideo", query = "SELECT c FROM Composicion c WHERE c.brvideo = :brvideo"),
    @NamedQuery(name = "Composicion.findByBraudio", query = "SELECT c FROM Composicion c WHERE c.braudio = :braudio"),
    @NamedQuery(name = "Composicion.findByNombrets", query = "SELECT c FROM Composicion c WHERE c.nombrets = :nombrets")})
public class Composicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOMPOSICION", nullable = false)
    private Integer idcomposicion;
    @Column(name = "BRVIDEO", length = 20)
    private String brvideo;
    @Column(name = "BRAUDIO", length = 20)
    private String braudio;
    @Column(name = "NOMBRETS", length = 20)
    private String nombrets;
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    @ManyToOne
    private Noticia noticia;

    public Composicion() {
    }

    public Composicion(Integer idcomposicion) {
        this.idcomposicion = idcomposicion;
    }

    public Integer getIdcomposicion() {
        return idcomposicion;
    }

    public void setIdcomposicion(Integer idcomposicion) {
        this.idcomposicion = idcomposicion;
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

    public String getNombrets() {
        return nombrets;
    }

    public void setNombrets(String nombrets) {
        this.nombrets = nombrets;
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
        hash += (idcomposicion != null ? idcomposicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Composicion)) {
            return false;
        }
        Composicion other = (Composicion) object;
        if ((this.idcomposicion == null && other.idcomposicion != null) || (this.idcomposicion != null && !this.idcomposicion.equals(other.idcomposicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Composicion[idcomposicion=" + idcomposicion + "]";
    }

}
