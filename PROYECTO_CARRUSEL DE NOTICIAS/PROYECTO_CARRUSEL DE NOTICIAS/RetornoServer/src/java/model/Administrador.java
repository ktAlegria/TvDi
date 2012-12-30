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
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "administrador")
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a"),
    @NamedQuery(name = "Administrador.findByCedula", query = "SELECT a FROM Administrador a WHERE a.cedula = :cedula"),
    @NamedQuery(name = "Administrador.findByNombre1", query = "SELECT a FROM Administrador a WHERE a.nombre1 = :nombre1"),
    @NamedQuery(name = "Administrador.findByNombre2", query = "SELECT a FROM Administrador a WHERE a.nombre2 = :nombre2"),
    @NamedQuery(name = "Administrador.findByApellido1", query = "SELECT a FROM Administrador a WHERE a.apellido1 = :apellido1"),
    @NamedQuery(name = "Administrador.findBySecAdministrador", query = "SELECT a FROM Administrador a WHERE a.secAdministrador = :secAdministrador"),
    @NamedQuery(name = "Administrador.findByApellido2", query = "SELECT a FROM Administrador a WHERE a.apellido2 = :apellido2"),
    @NamedQuery(name = "Administrador.findByUsername", query = "SELECT a FROM Administrador a WHERE a.username = :username"),
    @NamedQuery(name = "Administrador.findByPassword", query = "SELECT a FROM Administrador a WHERE a.password = :password")})
public class Administrador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "CEDULA", nullable = false)
    private long cedula;
    @Basic(optional = false)
    @Column(name = "NOMBRE1", nullable = false, length = 30)
    private String nombre1;
    @Column(name = "NOMBRE2", length = 30)
    private String nombre2;
    @Basic(optional = false)
    @Column(name = "APELLIDO1", nullable = false, length = 30)
    private String apellido1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_ADMINISTRADOR", nullable = false)
    private Integer secAdministrador;
    @Column(name = "APELLIDO2", length = 30)
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 30)
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;

    public Administrador() {
    }

    public Administrador(Integer secAdministrador) {
        this.secAdministrador = secAdministrador;
    }

    public Administrador(Integer secAdministrador, long cedula, String nombre1, String apellido1, String username, String password) {
        this.secAdministrador = secAdministrador;
        this.cedula = cedula;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.username = username;
        this.password = password;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public Integer getSecAdministrador() {
        return secAdministrador;
    }

    public void setSecAdministrador(Integer secAdministrador) {
        this.secAdministrador = secAdministrador;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secAdministrador != null ? secAdministrador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.secAdministrador == null && other.secAdministrador != null) || (this.secAdministrador != null && !this.secAdministrador.equals(other.secAdministrador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Administrador[secAdministrador=" + secAdministrador + "]";
    }

}
