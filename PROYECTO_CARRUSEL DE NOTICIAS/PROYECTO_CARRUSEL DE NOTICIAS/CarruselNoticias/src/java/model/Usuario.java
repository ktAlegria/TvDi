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
 * @author user
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByNombresadmin", query = "SELECT u FROM Usuario u WHERE u.nombresadmin = :nombresadmin"),
    @NamedQuery(name = "Usuario.findByApellidosadmin", query = "SELECT u FROM Usuario u WHERE u.apellidosadmin = :apellidosadmin"),
    @NamedQuery(name = "Usuario.findByCedulaadministrador", query = "SELECT u FROM Usuario u WHERE u.cedulaadministrador = :cedulaadministrador"),
    @NamedQuery(name = "Usuario.findByDireccionadministrador", query = "SELECT u FROM Usuario u WHERE u.direccionadministrador = :direccionadministrador"),
    @NamedQuery(name = "Usuario.findByTelefonoadministrador", query = "SELECT u FROM Usuario u WHERE u.telefonoadministrador = :telefonoadministrador"),
    @NamedQuery(name = "Usuario.findByEstadocodigo", query = "SELECT u FROM Usuario u WHERE u.estadocodigo = :estadocodigo")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO", nullable = false)
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "NOMBRESADMIN", nullable = false, length = 50)
    private String nombresadmin;
    @Basic(optional = false)
    @Column(name = "APELLIDOSADMIN", nullable = false, length = 50)
    private String apellidosadmin;
    @Basic(optional = false)
    @Column(name = "CEDULAADMINISTRADOR", nullable = false, length = 15)
    private String cedulaadministrador;
    @Column(name = "DIRECCIONADMINISTRADOR", length = 50)
    private String direccionadministrador;
    @Column(name = "TELEFONOADMINISTRADOR", length = 15)
    private String telefonoadministrador;
    @Basic(optional = false)
    @Column(name = "ESTADOCODIGO", nullable = false)
    private boolean estadocodigo;
    @OneToMany(mappedBy = "usuario")
    private List<Codigo> codigoList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nombresadmin, String apellidosadmin, String cedulaadministrador, boolean estadocodigo) {
        this.idusuario = idusuario;
        this.nombresadmin = nombresadmin;
        this.apellidosadmin = apellidosadmin;
        this.cedulaadministrador = cedulaadministrador;
        this.estadocodigo = estadocodigo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
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

    public String getCedulaadministrador() {
        return cedulaadministrador;
    }

    public void setCedulaadministrador(String cedulaadministrador) {
        this.cedulaadministrador = cedulaadministrador;
    }

    public String getDireccionadministrador() {
        return direccionadministrador;
    }

    public void setDireccionadministrador(String direccionadministrador) {
        this.direccionadministrador = direccionadministrador;
    }

    public String getTelefonoadministrador() {
        return telefonoadministrador;
    }

    public void setTelefonoadministrador(String telefonoadministrador) {
        this.telefonoadministrador = telefonoadministrador;
    }

    public boolean getEstadocodigo() {
        return estadocodigo;
    }

    public void setEstadocodigo(boolean estadocodigo) {
        this.estadocodigo = estadocodigo;
    }

    public List<Codigo> getCodigoList() {
        return codigoList;
    }

    public void setCodigoList(List<Codigo> codigoList) {
        this.codigoList = codigoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuario[idusuario=" + idusuario + "]";
    }

}
