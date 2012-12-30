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
@Table(name = "pais")
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findBySecPais", query = "SELECT p FROM Pais p WHERE p.secPais = :secPais"),
    @NamedQuery(name = "Pais.findByNombrePais", query = "SELECT p FROM Pais p WHERE p.nombrePais = :nombrePais")})
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_PAIS", nullable = false)
    private Integer secPais;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PAIS", nullable = false, length = 30)
    private String nombrePais;
    @OneToMany(mappedBy = "pais")
    private List<Departamento> departamentoList;

    public Pais() {
    }

    public Pais(Integer secPais) {
        this.secPais = secPais;
    }

    public Pais(Integer secPais, String nombrePais) {
        this.secPais = secPais;
        this.nombrePais = nombrePais;
    }

    public Integer getSecPais() {
        return secPais;
    }

    public void setSecPais(Integer secPais) {
        this.secPais = secPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secPais != null ? secPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.secPais == null && other.secPais != null) || (this.secPais != null && !this.secPais.equals(other.secPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pais[secPais=" + secPais + "]";
    }

}
