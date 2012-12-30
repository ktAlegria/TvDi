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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author johanna
 */
@Entity
@Table(name = "administrador")
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByIdadministrador", query = "SELECT a FROM Administrador a WHERE a.idadministrador = :idadministrador"),
    @NamedQuery(name = "Administrador.findByNombresadmin", query = "SELECT a FROM Administrador a WHERE a.nombresadmin = :nombresadmin"),
    @NamedQuery(name = "Administrador.findByApellidosadmin", query = "SELECT a FROM Administrador a WHERE a.apellidosadmin = :apellidosadmin"),
    @NamedQuery(name = "Administrador.findByUsuario", query = "SELECT a FROM Administrador a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "Administrador.findByClaveadministrador", query = "SELECT a FROM Administrador a WHERE a.claveadministrador = :claveadministrador"),
    @NamedQuery(name = "Administrador.findByTelefonoadministrador", query = "SELECT a FROM Administrador a WHERE a.telefonoadministrador = :telefonoadministrador"),
    @NamedQuery(name = "Administrador.findByDireccionadministrador", query = "SELECT a FROM Administrador a WHERE a.direccionadministrador = :direccionadministrador"),
    @NamedQuery(name = "Administrador.findByCedulaadministrador", query = "SELECT a FROM Administrador a WHERE a.cedulaadministrador = :cedulaadministrador"),
    @NamedQuery(name = "Administrador.findByLoginAdministrador", query = "SELECT a.idadministrador FROM Administrador a WHERE (a.usuario = :usuario) and (a.claveadministrador = :claveadministrador)")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDADMINISTRADOR", nullable = false)
    private Integer idadministrador;
    @Basic(optional = false)
    @Column(name = "NOMBRESADMIN", nullable = false, length = 50)
    private String nombresadmin;
    @Basic(optional = false)
    @Column(name = "APELLIDOSADMIN", nullable = false, length = 50)
    private String apellidosadmin;
    @Basic(optional = false)
    @Column(name = "USUARIO", nullable = false, length = 15)
    private String usuario;
    @Basic(optional = false)
    @Column(name = "CLAVEADMINISTRADOR", nullable = false, length = 20)
    private String claveadministrador;
    @Column(name = "TELEFONOADMINISTRADOR", length = 15)
    private String telefonoadministrador;
    @Column(name = "DIRECCIONADMINISTRADOR", length = 50)
    private String direccionadministrador;
    @Column(name = "CEDULAADMINISTRADOR", length = 15)
    private String cedulaadministrador;

    public Administrador() {
    }

    public Administrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Administrador(Integer idadministrador, String nombresadmin, String apellidosadmin, String usuario, String claveadministrador) {
        this.idadministrador = idadministrador;
        this.nombresadmin = nombresadmin;
        this.apellidosadmin = apellidosadmin;
        this.usuario = usuario;
        this.claveadministrador = claveadministrador;
    }

    public Integer getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Integer idadministrador) {
        this.idadministrador = idadministrador;
    }

    public String getNombresadmin() {
        return nombresadmin;
    }

    public void setNombresadmin(String nombresadmin) {
        this.nombresadmin = nombresadmin;
    }

    public String getApellidosadmin() {
        return apellidosadmin;
    }

    public void setApellidosadmin(String apellidosadmin) {
        this.apellidosadmin = apellidosadmin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClaveadministrador() {
        return claveadministrador;
    }

    public void setClaveadministrador(String claveadministrador) {
        this.claveadministrador = claveadministrador;
    }

    public String getTelefonoadministrador() {
        return telefonoadministrador;
    }

    public void setTelefonoadministrador(String telefonoadministrador) {
        this.telefonoadministrador = telefonoadministrador;
    }

    public String getDireccionadministrador() {
        return direccionadministrador;
    }

    public void setDireccionadministrador(String direccionadministrador) {
        this.direccionadministrador = direccionadministrador;
    }

    public String getCedulaadministrador() {
        return cedulaadministrador;
    }

    public void setCedulaadministrador(String cedulaadministrador) {
        this.cedulaadministrador = cedulaadministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadministrador != null ? idadministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.idadministrador == null && other.idadministrador != null) || (this.idadministrador != null && !this.idadministrador.equals(other.idadministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Administrador[idadministrador=" + idadministrador + "]";
    }

}
