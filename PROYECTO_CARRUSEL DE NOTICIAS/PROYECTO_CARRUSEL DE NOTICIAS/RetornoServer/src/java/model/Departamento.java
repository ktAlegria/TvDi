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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "departamento")
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findBySecDepartamento", query = "SELECT d FROM Departamento d WHERE d.secDepartamento = :secDepartamento"),
    @NamedQuery(name = "Departamento.findByNombreDepartamento", query = "SELECT d FROM Departamento d WHERE d.nombreDepartamento = :nombreDepartamento")})
public class Departamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_DEPARTAMENTO", nullable = false)
    private Integer secDepartamento;
    @Basic(optional = false)
    @Column(name = "NOMBRE_DEPARTAMENTO", nullable = false, length = 30)
    private String nombreDepartamento;
    @OneToMany(mappedBy = "departamento")
    private List<Ciudad> ciudadList;
    @JoinColumn(name = "SEC_PAIS", referencedColumnName = "SEC_PAIS")
    @ManyToOne
    private Pais pais;

    public Departamento() {
    }

    public Departamento(Integer secDepartamento) {
        this.secDepartamento = secDepartamento;
    }

    public Departamento(Integer secDepartamento, String nombreDepartamento) {
        this.secDepartamento = secDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getSecDepartamento() {
        return secDepartamento;
    }

    public void setSecDepartamento(Integer secDepartamento) {
        this.secDepartamento = secDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secDepartamento != null ? secDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.secDepartamento == null && other.secDepartamento != null) || (this.secDepartamento != null && !this.secDepartamento.equals(other.secDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Departamento[secDepartamento=" + secDepartamento + "]";
    }

}
