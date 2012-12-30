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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "codigo")
@NamedQueries({
    @NamedQuery(name = "Codigo.findAll", query = "SELECT c FROM Codigo c"),
    @NamedQuery(name = "Codigo.findByIdcodigo", query = "SELECT c FROM Codigo c WHERE c.idcodigo = :idcodigo"),
    @NamedQuery(name = "Codigo.findByClave", query = "SELECT c FROM Codigo c WHERE c.clave = :clave"),
    @NamedQuery(name = "Codigo.findByEstadocodigo", query = "SELECT c FROM Codigo c WHERE c.estadocodigo = :estadocodigo")})
public class Codigo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCODIGO", nullable = false)
    private Integer idcodigo;
    @Basic(optional = false)
    @Column(name = "CLAVE", nullable = false, length = 6)
    private String clave;
    @Basic(optional = false)
    @Column(name = "ESTADOCODIGO", nullable = false)
    private boolean estadocodigo;
    @Lob
    @Column(name = "MAC", length = 65535)
    private String mac;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne
    private Usuario usuario;

    public Codigo() {
    }

    public Codigo(Integer idcodigo) {
        this.idcodigo = idcodigo;
    }

    public Codigo(Integer idcodigo, String clave, boolean estadocodigo) {
        this.idcodigo = idcodigo;
        this.clave = clave;
        this.estadocodigo = estadocodigo;
    }

    public Integer getIdcodigo() {
        return idcodigo;
    }

    public void setIdcodigo(Integer idcodigo) {
        this.idcodigo = idcodigo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getEstadocodigo() {
        return estadocodigo;
    }

    public void setEstadocodigo(boolean estadocodigo) {
        this.estadocodigo = estadocodigo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcodigo != null ? idcodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codigo)) {
            return false;
        }
        Codigo other = (Codigo) object;
        if ((this.idcodigo == null && other.idcodigo != null) || (this.idcodigo != null && !this.idcodigo.equals(other.idcodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Codigo[idcodigo=" + idcodigo + "]";
    }

}
