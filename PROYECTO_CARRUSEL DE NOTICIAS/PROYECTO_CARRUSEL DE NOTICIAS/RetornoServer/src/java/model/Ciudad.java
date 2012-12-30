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
@Table(name = "ciudad")
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
    @NamedQuery(name = "Ciudad.findBySecCiudad", query = "SELECT c FROM Ciudad c WHERE c.secCiudad = :secCiudad"),
    @NamedQuery(name = "Ciudad.findByNombreCiudad", query = "SELECT c FROM Ciudad c WHERE c.nombreCiudad = :nombreCiudad"),
    @NamedQuery(name = "Ciudad.findByCodigoPostal", query = "SELECT c FROM Ciudad c WHERE c.codigoPostal = :codigoPostal")})
public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_CIUDAD", nullable = false)
    private Integer secCiudad;
    @Column(name = "NOMBRE_CIUDAD", length = 30)
    private String nombreCiudad;
    @Column(name = "CODIGO_POSTAL", length = 20)
    private String codigoPostal;
    @JoinColumn(name = "SEC_DEPARTAMENTO", referencedColumnName = "SEC_DEPARTAMENTO")
    @ManyToOne
    private Departamento departamento;
    @OneToMany(mappedBy = "ciudad")
    private List<Condicionactual> condicionactualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudad")
    private List<PerfilCiudad> perfilCiudadList;

    public Ciudad() {
    }

    public Ciudad(Integer secCiudad) {
        this.secCiudad = secCiudad;
    }

    public Integer getSecCiudad() {
        return secCiudad;
    }

    public void setSecCiudad(Integer secCiudad) {
        this.secCiudad = secCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Condicionactual> getCondicionactualList() {
        return condicionactualList;
    }

    public void setCondicionactualList(List<Condicionactual> condicionactualList) {
        this.condicionactualList = condicionactualList;
    }

    public List<PerfilCiudad> getPerfilCiudadList() {
        return perfilCiudadList;
    }

    public void setPerfilCiudadList(List<PerfilCiudad> perfilCiudadList) {
        this.perfilCiudadList = perfilCiudadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secCiudad != null ? secCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.secCiudad == null && other.secCiudad != null) || (this.secCiudad != null && !this.secCiudad.equals(other.secCiudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ciudad[secCiudad=" + secCiudad + "]";
    }

}
