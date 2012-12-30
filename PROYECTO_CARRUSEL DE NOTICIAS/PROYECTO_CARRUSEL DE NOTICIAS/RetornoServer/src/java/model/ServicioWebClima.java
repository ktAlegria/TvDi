/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "servicio_web_clima")
@NamedQueries({
    @NamedQuery(name = "ServicioWebClima.findAll", query = "SELECT s FROM ServicioWebClima s"),
    @NamedQuery(name = "ServicioWebClima.findBySecServicioWebClima", query = "SELECT s FROM ServicioWebClima s WHERE s.secServicioWebClima = :secServicioWebClima"),
    @NamedQuery(name = "ServicioWebClima.findByNombreServicio", query = "SELECT s FROM ServicioWebClima s WHERE s.nombreServicio = :nombreServicio")})
public class ServicioWebClima implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_SERVICIO_WEB_CLIMA", nullable = false)
    private Integer secServicioWebClima;
    @Basic(optional = false)
    @Column(name = "NOMBRE_SERVICIO", nullable = false, length = 30)
    private String nombreServicio;
    @Lob
    @Column(name = "DESCRIPCION_SERVICIO", length = 65535)
    private String descripcionServicio;
    @OneToMany(mappedBy = "servicioWebClima")
    private List<TerceraParteServicio> terceraParteServicioList;

    public ServicioWebClima() {
    }

    public ServicioWebClima(Integer secServicioWebClima) {
        this.secServicioWebClima = secServicioWebClima;
    }

    public ServicioWebClima(Integer secServicioWebClima, String nombreServicio) {
        this.secServicioWebClima = secServicioWebClima;
        this.nombreServicio = nombreServicio;
    }

    public Integer getSecServicioWebClima() {
        return secServicioWebClima;
    }

    public void setSecServicioWebClima(Integer secServicioWebClima) {
        this.secServicioWebClima = secServicioWebClima;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public List<TerceraParteServicio> getTerceraParteServicioList() {
        return terceraParteServicioList;
    }

    public void setTerceraParteServicioList(List<TerceraParteServicio> terceraParteServicioList) {
        this.terceraParteServicioList = terceraParteServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secServicioWebClima != null ? secServicioWebClima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioWebClima)) {
            return false;
        }
        ServicioWebClima other = (ServicioWebClima) object;
        if ((this.secServicioWebClima == null && other.secServicioWebClima != null) || (this.secServicioWebClima != null && !this.secServicioWebClima.equals(other.secServicioWebClima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ServicioWebClima[secServicioWebClima=" + secServicioWebClima + "]";
    }

}
