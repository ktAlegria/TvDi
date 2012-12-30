/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "pronostico")
@NamedQueries({
    @NamedQuery(name = "Pronostico.findAll", query = "SELECT p FROM Pronostico p"),
    @NamedQuery(name = "Pronostico.findBySecPronostico", query = "SELECT p FROM Pronostico p WHERE p.secPronostico = :secPronostico"),
    @NamedQuery(name = "Pronostico.findByDia", query = "SELECT p FROM Pronostico p WHERE p.dia = :dia"),
    @NamedQuery(name = "Pronostico.findByTemplow", query = "SELECT p FROM Pronostico p WHERE p.templow = :templow"),
    @NamedQuery(name = "Pronostico.findByTemphigh", query = "SELECT p FROM Pronostico p WHERE p.temphigh = :temphigh"),
    @NamedQuery(name = "Pronostico.findByCondicion", query = "SELECT p FROM Pronostico p WHERE p.condicion = :condicion"),
    @NamedQuery(name = "Pronostico.findByIconurl", query = "SELECT p FROM Pronostico p WHERE p.iconurl = :iconurl"),
    @NamedQuery(name = "Pronostico.findByFecha", query = "SELECT p FROM Pronostico p WHERE p.fecha = :fecha")})
public class Pronostico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_PRONOSTICO", nullable = false)
    private Integer secPronostico;
    @Column(name = "DIA", length = 10)
    private String dia;
    @Column(name = "TEMPLOW", length = 3)
    private String templow;
    @Column(name = "TEMPHIGH", length = 3)
    private String temphigh;
    @Column(name = "CONDICION", length = 30)
    private String condicion;
    @Column(name = "ICONURL", length = 50)
    private String iconurl;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "SEC_CONDICIONACTUAL", referencedColumnName = "SEC_CONDICIONACTUAL")
    @ManyToOne
    private Condicionactual condicionactual;

    public Pronostico() {
    }

    public Pronostico(Integer secPronostico) {
        this.secPronostico = secPronostico;
    }

    public Integer getSecPronostico() {
        return secPronostico;
    }

    public void setSecPronostico(Integer secPronostico) {
        this.secPronostico = secPronostico;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTemplow() {
        return templow;
    }

    public void setTemplow(String templow) {
        this.templow = templow;
    }

    public String getTemphigh() {
        return temphigh;
    }

    public void setTemphigh(String temphigh) {
        this.temphigh = temphigh;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Condicionactual getCondicionactual() {
        return condicionactual;
    }

    public void setCondicionactual(Condicionactual condicionactual) {
        this.condicionactual = condicionactual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secPronostico != null ? secPronostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pronostico)) {
            return false;
        }
        Pronostico other = (Pronostico) object;
        if ((this.secPronostico == null && other.secPronostico != null) || (this.secPronostico != null && !this.secPronostico.equals(other.secPronostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pronostico[secPronostico=" + secPronostico + "]";
    }

}
