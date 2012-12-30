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
@Table(name = "accion")
@NamedQueries({
    @NamedQuery(name = "Accion.findAll", query = "SELECT a FROM Accion a"),
    @NamedQuery(name = "Accion.findBySecAccion", query = "SELECT a FROM Accion a WHERE a.secAccion = :secAccion"),
    @NamedQuery(name = "Accion.findByNombreAccion", query = "SELECT a FROM Accion a WHERE a.nombreAccion = :nombreAccion")})
public class Accion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_ACCION", nullable = false)
    private Integer secAccion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_ACCION", nullable = false, length = 30)
    private String nombreAccion;
    @OneToMany(mappedBy = "accion")
    private List<Registro> registroList;

    public Accion() {
    }

    public Accion(Integer secAccion) {
        this.secAccion = secAccion;
    }

    public Accion(Integer secAccion, String nombreAccion) {
        this.secAccion = secAccion;
        this.nombreAccion = nombreAccion;
    }

    public Integer getSecAccion() {
        return secAccion;
    }

    public void setSecAccion(Integer secAccion) {
        this.secAccion = secAccion;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secAccion != null ? secAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accion)) {
            return false;
        }
        Accion other = (Accion) object;
        if ((this.secAccion == null && other.secAccion != null) || (this.secAccion != null && !this.secAccion.equals(other.secAccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Accion[secAccion=" + secAccion + "]";
    }

}
