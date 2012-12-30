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
@Table(name = "perfil_ciudad")
@NamedQueries({
    @NamedQuery(name = "PerfilCiudad.findAll", query = "SELECT p FROM PerfilCiudad p"),
    @NamedQuery(name = "PerfilCiudad.findBySecPerfilCiudad", query = "SELECT p FROM PerfilCiudad p WHERE p.secPerfilCiudad = :secPerfilCiudad")})
public class PerfilCiudad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_PERFIL_CIUDAD", nullable = false)
    private Integer secPerfilCiudad;
    @JoinColumn(name = "SEC_PERFIL", referencedColumnName = "SEC_PERFIL", nullable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "SEC_CIUDAD", referencedColumnName = "SEC_CIUDAD", nullable = false)
    @ManyToOne(optional = false)
    private Ciudad ciudad;

    public PerfilCiudad() {
    }

    public PerfilCiudad(Integer secPerfilCiudad) {
        this.secPerfilCiudad = secPerfilCiudad;
    }

    public Integer getSecPerfilCiudad() {
        return secPerfilCiudad;
    }

    public void setSecPerfilCiudad(Integer secPerfilCiudad) {
        this.secPerfilCiudad = secPerfilCiudad;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secPerfilCiudad != null ? secPerfilCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilCiudad)) {
            return false;
        }
        PerfilCiudad other = (PerfilCiudad) object;
        if ((this.secPerfilCiudad == null && other.secPerfilCiudad != null) || (this.secPerfilCiudad != null && !this.secPerfilCiudad.equals(other.secPerfilCiudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PerfilCiudad[secPerfilCiudad=" + secPerfilCiudad + "]";
    }

}
