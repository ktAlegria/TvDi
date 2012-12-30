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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author JOHANNA
 */
@Entity
@Table(name = "fuenterss")
@NamedQueries({
    @NamedQuery(name = "Fuenterss.findAll", query = "SELECT f FROM Fuenterss f"),
    @NamedQuery(name = "Fuenterss.findByIdrss", query = "SELECT f FROM Fuenterss f WHERE f.idrss = :idrss"),
    @NamedQuery(name = "Fuenterss.findByTitulo", query = "SELECT f FROM Fuenterss f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Fuenterss.findByCategoria", query = "SELECT f FROM Fuenterss f WHERE f.categoria = :categoria")})
public class Fuenterss implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRSS", nullable = false)
    private Integer idrss;
    @Basic(optional = false)
    @Column(name = "TITULO", nullable = false, length = 60)
    private String titulo;
    @Column(name = "CATEGORIA", length = 60)
    private String categoria;
    @Basic(optional = false)
    @Lob
    @Column(name = "URL", nullable = false, length = 65535)
    private String url;

    public Fuenterss() {
    }

    public Fuenterss(Integer idrss) {
        this.idrss = idrss;
    }

    public Fuenterss(Integer idrss, String titulo, String url) {
        this.idrss = idrss;
        this.titulo = titulo;
        this.url = url;
    }

    public Integer getIdrss() {
        return idrss;
    }

    public void setIdrss(Integer idrss) {
        this.idrss = idrss;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrss != null ? idrss.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fuenterss)) {
            return false;
        }
        Fuenterss other = (Fuenterss) object;
        if ((this.idrss == null && other.idrss != null) || (this.idrss != null && !this.idrss.equals(other.idrss))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Fuenterss[idrss=" + idrss + "]";
    }

}
