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
@Table(name = "perfil_caracteristica")
@NamedQueries({
    @NamedQuery(name = "PerfilCaracteristica.findAll", query = "SELECT p FROM PerfilCaracteristica p"),
    @NamedQuery(name = "PerfilCaracteristica.findBySecPerfilCaracteristica", query = "SELECT p FROM PerfilCaracteristica p WHERE p.secPerfilCaracteristica = :secPerfilCaracteristica")})
public class PerfilCaracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_PERFIL_CARACTERISTICA", nullable = false)
    private Integer secPerfilCaracteristica;
    @JoinColumn(name = "SEC_PERFIL", referencedColumnName = "SEC_PERFIL", nullable = false)
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "SEC_CARACTERISTICA", referencedColumnName = "SEC_CARACTERISTICA", nullable = false)
    @ManyToOne(optional = false)
    private Caracteristica caracteristica;

    public PerfilCaracteristica() {
    }

    public PerfilCaracteristica(Integer secPerfilCaracteristica) {
        this.secPerfilCaracteristica = secPerfilCaracteristica;
    }

    public Integer getSecPerfilCaracteristica() {
        return secPerfilCaracteristica;
    }

    public void setSecPerfilCaracteristica(Integer secPerfilCaracteristica) {
        this.secPerfilCaracteristica = secPerfilCaracteristica;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secPerfilCaracteristica != null ? secPerfilCaracteristica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilCaracteristica)) {
            return false;
        }
        PerfilCaracteristica other = (PerfilCaracteristica) object;
        if ((this.secPerfilCaracteristica == null && other.secPerfilCaracteristica != null) || (this.secPerfilCaracteristica != null && !this.secPerfilCaracteristica.equals(other.secPerfilCaracteristica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PerfilCaracteristica[secPerfilCaracteristica=" + secPerfilCaracteristica + "]";
    }

}
