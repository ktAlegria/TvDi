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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "notificacion")
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findBySecNotificacion", query = "SELECT n FROM Notificacion n WHERE n.secNotificacion = :secNotificacion")})
public class Notificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_NOTIFICACION", nullable = false)
    private Integer secNotificacion;
    @Lob
    @Column(name = "NOMBRE", length = 65535)
    private String nombre;
    @OneToMany(mappedBy = "notificacion")
    private List<Perfil> perfilList;

    public Notificacion() {
    }

    public Notificacion(Integer secNotificacion) {
        this.secNotificacion = secNotificacion;
    }

    public Integer getSecNotificacion() {
        return secNotificacion;
    }

    public void setSecNotificacion(Integer secNotificacion) {
        this.secNotificacion = secNotificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secNotificacion != null ? secNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.secNotificacion == null && other.secNotificacion != null) || (this.secNotificacion != null && !this.secNotificacion.equals(other.secNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Notificacion[secNotificacion=" + secNotificacion + "]";
    }

}
