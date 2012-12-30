/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johanna
 */
@Entity
@Table(name = "noticia")
@NamedQueries({
    @NamedQuery(name = "Noticia.findAll", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.findById", query = "SELECT n FROM Noticia n WHERE n.id = :id"),
    @NamedQuery(name = "Noticia.findByTitulo", query = "SELECT n FROM Noticia n WHERE n.titulo = :titulo"),
    @NamedQuery(name = "Noticia.findByFechapublicacion", query = "SELECT n FROM Noticia n WHERE n.fechapublicacion = :fechapublicacion"),
    @NamedQuery(name = "Noticia.findByDuracion", query = "SELECT n FROM Noticia n WHERE n.duracion = :duracion"),
    @NamedQuery(name = "Noticia.findByVersion", query = "SELECT n FROM Noticia n WHERE n.version = :version")})
public class Noticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITULO", nullable = false, length = 60)
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "DESCRIPCION", nullable = false, length = 65535)
    private String descripcion;
    @Lob
    @Column(name = "URLIMAGEN", length = 65535)
    private String urlimagen;
    @Lob
    @Column(name = "URLVIDEO", length = 65535)
    private String urlvideo;
    @Basic(optional = false)
    @Column(name = "FECHAPUBLICACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechapublicacion;
    @Column(name = "DURACION")
    @Temporal(TemporalType.TIME)
    private Date duracion;
    @Column(name = "VERSION")
    private Integer version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "noticia")
    private List<Carrusel> carruselList;

    public Noticia() {
    }

    public Noticia(Integer id) {
        this.id = id;
    }

    public Noticia(Integer id, String titulo, String descripcion, Date fechapublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechapublicacion = fechapublicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getUrlvideo() {
        return urlvideo;
    }

    public void setUrlvideo(String urlvideo) {
        this.urlvideo = urlvideo;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<Carrusel> getCarruselList() {
        return carruselList;
    }

    public void setCarruselList(List<Carrusel> carruselList) {
        this.carruselList = carruselList;
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
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Noticia[id=" + id + "]";
    }

}
