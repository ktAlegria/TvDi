/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "caracteristica")
@NamedQueries({
    @NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c"),
    @NamedQuery(name = "Caracteristica.findBySecCaracteristica", query = "SELECT c FROM Caracteristica c WHERE c.secCaracteristica = :secCaracteristica"),
    @NamedQuery(name = "Caracteristica.findByNombreCaracteristica", query = "SELECT c FROM Caracteristica c WHERE c.nombreCaracteristica = :nombreCaracteristica")})
public class Caracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_CARACTERISTICA", nullable = false)
    private Integer secCaracteristica;
    @Basic(optional = false)
    @Column(name = "NOMBRE_CARACTERISTICA", nullable = false, length = 20)
    private String nombreCaracteristica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caracteristica")
    private List<PerfilCaracteristica> perfilCaracteristicaList;

    public Caracteristica() {
    }

    public Caracteristica(Integer secCaracteristica) {
        this.secCaracteristica = secCaracteristica;
    }

    public Caracteristica(Integer secCaracteristica, String nombreCaracteristica) {
        this.secCaracteristica = secCaracteristica;
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public Integer getSecCaracteristica() {
        return secCaracteristica;
    }

    public void setSecCaracteristica(Integer secCaracteristica) {
        this.secCaracteristica = secCaracteristica;
    }

    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public List<PerfilCaracteristica> getPerfilCaracteristicaList() {
        return perfilCaracteristicaList;
    }

    public void setPerfilCaracteristicaList(List<PerfilCaracteristica> perfilCaracteristicaList) {
        this.perfilCaracteristicaList = perfilCaracteristicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secCaracteristica != null ? secCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caracteristica)) {
            return false;
        }
        Caracteristica other = (Caracteristica) object;
        if ((this.secCaracteristica == null && other.secCaracteristica != null) || (this.secCaracteristica != null && !this.secCaracteristica.equals(other.secCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Caracteristica[secCaracteristica=" + secCaracteristica + "]";
    }

}
