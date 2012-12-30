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
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "tercera_parte_servicio")
@NamedQueries({
    @NamedQuery(name = "TerceraParteServicio.findAll", query = "SELECT t FROM TerceraParteServicio t"),
    @NamedQuery(name = "TerceraParteServicio.findBySecTerceraParteServicio", query = "SELECT t FROM TerceraParteServicio t WHERE t.secTerceraParteServicio = :secTerceraParteServicio")})
public class TerceraParteServicio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_TERCERA_PARTE_SERVICIO", nullable = false)
    private Integer secTerceraParteServicio;
    @JoinColumn(name = "SEC_TERCERA_PARTE", referencedColumnName = "SEC_TERCERA_PARTE")
    @ManyToOne
    private TerceraParte terceraParte;
    @JoinColumn(name = "SEC_SERVICIO_WEB_CLIMA", referencedColumnName = "SEC_SERVICIO_WEB_CLIMA")
    @ManyToOne
    private ServicioWebClima servicioWebClima;

    public TerceraParteServicio() {
    }

    public TerceraParteServicio(Integer secTerceraParteServicio) {
        this.secTerceraParteServicio = secTerceraParteServicio;
    }

    public Integer getSecTerceraParteServicio() {
        return secTerceraParteServicio;
    }

    public void setSecTerceraParteServicio(Integer secTerceraParteServicio) {
        this.secTerceraParteServicio = secTerceraParteServicio;
    }

    public TerceraParte getTerceraParte() {
        return terceraParte;
    }

    public void setTerceraParte(TerceraParte terceraParte) {
        this.terceraParte = terceraParte;
    }

    public ServicioWebClima getServicioWebClima() {
        return servicioWebClima;
    }

    public void setServicioWebClima(ServicioWebClima servicioWebClima) {
        this.servicioWebClima = servicioWebClima;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secTerceraParteServicio != null ? secTerceraParteServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerceraParteServicio)) {
            return false;
        }
        TerceraParteServicio other = (TerceraParteServicio) object;
        if ((this.secTerceraParteServicio == null && other.secTerceraParteServicio != null) || (this.secTerceraParteServicio != null && !this.secTerceraParteServicio.equals(other.secTerceraParteServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TerceraParteServicio[secTerceraParteServicio=" + secTerceraParteServicio + "]";
    }

}
