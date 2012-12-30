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
@Table(name = "perfil")
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p"),
    @NamedQuery(name = "Perfil.findBySecPerfil", query = "SELECT p FROM Perfil p WHERE p.secPerfil = :secPerfil"),
    @NamedQuery(name = "Perfil.findByActivo", query = "SELECT p FROM Perfil p WHERE p.activo = :activo"),
    @NamedQuery(name = "Perfil.findByServicioAgregadoUsuario", query = "SELECT p FROM Perfil p WHERE p.servicioAgregadoUsuario = :servicioAgregadoUsuario"),
    @NamedQuery(name = "Perfil.findByLogin", query = "SELECT p FROM Perfil p WHERE p.login = :login"),
    @NamedQuery(name = "Perfil.findByPassword", query = "SELECT p FROM Perfil p WHERE p.password = :password"),
    @NamedQuery(name = "Perfil.findByActualizar", query = "SELECT p FROM Perfil p WHERE p.actualizar = :actualizar")})
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_PERFIL", nullable = false)
    private Integer secPerfil;
    @Basic(optional = false)
    @Column(name = "ACTIVO", nullable = false)
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "SERVICIO_AGREGADO_USUARIO", nullable = false)
    private int servicioAgregadoUsuario;
    @Basic(optional = false)
    @Column(name = "LOGIN", nullable = false, length = 15)
    private String login;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 30)
    private String password;
    @Column(name = "ACTUALIZAR")
    private Boolean actualizar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<PerfilCaracteristica> perfilCaracteristicaList;
    @OneToMany(mappedBy = "perfil")
    private List<Condicionactual> condicionactualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<PerfilCiudad> perfilCiudadList;
    @OneToMany(mappedBy = "perfil")
    private List<Registro> registroList;
    @JoinColumn(name = "SEC_NOTIFICACION", referencedColumnName = "SEC_NOTIFICACION")
    @ManyToOne
    private Notificacion notificacion;

    public Perfil() {
    }

    public Perfil(Integer secPerfil) {
        this.secPerfil = secPerfil;
    }

    public Perfil(Integer secPerfil, boolean activo, int servicioAgregadoUsuario, String login, String password) {
        this.secPerfil = secPerfil;
        this.activo = activo;
        this.servicioAgregadoUsuario = servicioAgregadoUsuario;
        this.login = login;
        this.password = password;
    }

    public Integer getSecPerfil() {
        return secPerfil;
    }

    public void setSecPerfil(Integer secPerfil) {
        this.secPerfil = secPerfil;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getServicioAgregadoUsuario() {
        return servicioAgregadoUsuario;
    }

    public void setServicioAgregadoUsuario(int servicioAgregadoUsuario) {
        this.servicioAgregadoUsuario = servicioAgregadoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public List<PerfilCaracteristica> getPerfilCaracteristicaList() {
        return perfilCaracteristicaList;
    }

    public void setPerfilCaracteristicaList(List<PerfilCaracteristica> perfilCaracteristicaList) {
        this.perfilCaracteristicaList = perfilCaracteristicaList;
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

    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secPerfil != null ? secPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.secPerfil == null && other.secPerfil != null) || (this.secPerfil != null && !this.secPerfil.equals(other.secPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Perfil[secPerfil=" + secPerfil + "]";
    }

}
