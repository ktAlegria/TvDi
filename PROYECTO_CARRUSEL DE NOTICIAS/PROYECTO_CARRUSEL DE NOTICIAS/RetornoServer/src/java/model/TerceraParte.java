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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "tercera_parte")
@NamedQueries({
    @NamedQuery(name = "TerceraParte.findAll", query = "SELECT t FROM TerceraParte t"),
    @NamedQuery(name = "TerceraParte.findBySecTerceraParte", query = "SELECT t FROM TerceraParte t WHERE t.secTerceraParte = :secTerceraParte"),
    @NamedQuery(name = "TerceraParte.findByNombreTerceraParte", query = "SELECT t FROM TerceraParte t WHERE t.nombreTerceraParte = :nombreTerceraParte"),
    @NamedQuery(name = "TerceraParte.findByPassword", query = "SELECT t FROM TerceraParte t WHERE t.password = :password"),
    @NamedQuery(name = "TerceraParte.findByIdTerceraParte", query = "SELECT t FROM TerceraParte t WHERE t.idTerceraParte = :idTerceraParte")})
public class TerceraParte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_TERCERA_PARTE", nullable = false)
    private Integer secTerceraParte;
    @Basic(optional = false)
    @Column(name = "NOMBRE_TERCERA_PARTE", nullable = false, length = 50)
    private String nombreTerceraParte;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;
    @Basic(optional = false)
    @Column(name = "ID_TERCERA_PARTE", nullable = false, length = 30)
    private String idTerceraParte;
    @OneToMany(mappedBy = "terceraParte")
    private List<TerceraParteServicio> terceraParteServicioList;

    public TerceraParte() {
    }

    public TerceraParte(Integer secTerceraParte) {
        this.secTerceraParte = secTerceraParte;
    }

    public TerceraParte(Integer secTerceraParte, String nombreTerceraParte, String password, String idTerceraParte) {
        this.secTerceraParte = secTerceraParte;
        this.nombreTerceraParte = nombreTerceraParte;
        this.password = password;
        this.idTerceraParte = idTerceraParte;
    }

    public Integer getSecTerceraParte() {
        return secTerceraParte;
    }

    public void setSecTerceraParte(Integer secTerceraParte) {
        this.secTerceraParte = secTerceraParte;
    }

    public String getNombreTerceraParte() {
        return nombreTerceraParte;
    }

    public void setNombreTerceraParte(String nombreTerceraParte) {
        this.nombreTerceraParte = nombreTerceraParte;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdTerceraParte() {
        return idTerceraParte;
    }

    public void setIdTerceraParte(String idTerceraParte) {
        this.idTerceraParte = idTerceraParte;
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
        hash += (secTerceraParte != null ? secTerceraParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerceraParte)) {
            return false;
        }
        TerceraParte other = (TerceraParte) object;
        if ((this.secTerceraParte == null && other.secTerceraParte != null) || (this.secTerceraParte != null && !this.secTerceraParte.equals(other.secTerceraParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TerceraParte[secTerceraParte=" + secTerceraParte + "]";
    }

}
