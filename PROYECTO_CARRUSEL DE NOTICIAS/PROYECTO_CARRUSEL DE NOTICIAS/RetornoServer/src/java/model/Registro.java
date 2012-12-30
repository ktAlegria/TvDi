/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "registro")
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r"),
    @NamedQuery(name = "Registro.findBySecRegistro", query = "SELECT r FROM Registro r WHERE r.secRegistro = :secRegistro"),
    @NamedQuery(name = "Registro.findByFecha", query = "SELECT r FROM Registro r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Registro.findByHora", query = "SELECT r FROM Registro r WHERE r.hora = :hora"),
    @NamedQuery(name = "Registro.findByInformacionAdicional", query = "SELECT r FROM Registro r WHERE r.informacionAdicional = :informacionAdicional")})
public class Registro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_REGISTRO", nullable = false)
    private Integer secRegistro;
    @Basic(optional = false)
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "HORA", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "INFORMACION_ADICIONAL", length = 100)
    private String informacionAdicional;
    @JoinColumn(name = "SEC_ACCION", referencedColumnName = "SEC_ACCION")
    @ManyToOne
    private Accion accion;
    @JoinColumn(name = "SEC_PERFIL", referencedColumnName = "SEC_PERFIL")
    @ManyToOne
    private Perfil perfil;

    public Registro() {
    }

    public Registro(Integer secRegistro) {
        this.secRegistro = secRegistro;
    }

    public Registro(Integer secRegistro, Date fecha, Date hora) {
        this.secRegistro = secRegistro;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getSecRegistro() {
        return secRegistro;
    }

    public void setSecRegistro(Integer secRegistro) {
        this.secRegistro = secRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secRegistro != null ? secRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.secRegistro == null && other.secRegistro != null) || (this.secRegistro != null && !this.secRegistro.equals(other.secRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Registro[secRegistro=" + secRegistro + "]";
    }

}
