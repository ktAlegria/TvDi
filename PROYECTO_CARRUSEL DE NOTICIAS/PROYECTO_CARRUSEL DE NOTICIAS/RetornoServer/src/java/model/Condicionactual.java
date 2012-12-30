/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GUSTAVO_AL
 */
@Entity
@Table(name = "condicionactual")
@NamedQueries({
    @NamedQuery(name = "Condicionactual.findAll", query = "SELECT c FROM Condicionactual c"),
    @NamedQuery(name = "Condicionactual.findBySecCondicionactual", query = "SELECT c FROM Condicionactual c WHERE c.secCondicionactual = :secCondicionactual"),
    @NamedQuery(name = "Condicionactual.findByNombrecondicion", query = "SELECT c FROM Condicionactual c WHERE c.nombrecondicion = :nombrecondicion"),
    @NamedQuery(name = "Condicionactual.findByTemperaturaf", query = "SELECT c FROM Condicionactual c WHERE c.temperaturaf = :temperaturaf"),
    @NamedQuery(name = "Condicionactual.findByTemperaturac", query = "SELECT c FROM Condicionactual c WHERE c.temperaturac = :temperaturac"),
    @NamedQuery(name = "Condicionactual.findByHumedad", query = "SELECT c FROM Condicionactual c WHERE c.humedad = :humedad"),
    @NamedQuery(name = "Condicionactual.findByUrlicon", query = "SELECT c FROM Condicionactual c WHERE c.urlicon = :urlicon"),
    @NamedQuery(name = "Condicionactual.findByViento", query = "SELECT c FROM Condicionactual c WHERE c.viento = :viento"),
    @NamedQuery(name = "Condicionactual.findByFecha", query = "SELECT c FROM Condicionactual c WHERE c.fecha = :fecha")})
public class Condicionactual implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SEC_CONDICIONACTUAL", nullable = false)
    private Integer secCondicionactual;
    @Column(name = "NOMBRECONDICION", length = 30)
    private String nombrecondicion;
    @Column(name = "TEMPERATURAF", length = 15)
    private String temperaturaf;
    @Column(name = "TEMPERATURAC", length = 15)
    private String temperaturac;
    @Column(name = "HUMEDAD", length = 15)
    private String humedad;
    @Column(name = "URLICON", length = 50)
    private String urlicon;
    @Column(name = "VIENTO", length = 30)
    private String viento;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "SEC_PERFIL", referencedColumnName = "SEC_PERFIL")
    @ManyToOne
    private Perfil perfil;
    @JoinColumn(name = "SEC_CIUDAD", referencedColumnName = "SEC_CIUDAD")
    @ManyToOne
    private Ciudad ciudad;
    @OneToMany(mappedBy = "condicionactual")
    private List<Pronostico> pronosticoList;

    public Condicionactual() {
    }

    public Condicionactual(Integer secCondicionactual) {
        this.secCondicionactual = secCondicionactual;
    }

    public Integer getSecCondicionactual() {
        return secCondicionactual;
    }

    public void setSecCondicionactual(Integer secCondicionactual) {
        this.secCondicionactual = secCondicionactual;
    }

    public String getNombrecondicion() {
        return nombrecondicion;
    }

    public void setNombrecondicion(String nombrecondicion) {
        this.nombrecondicion = nombrecondicion;
    }

    public String getTemperaturaf() {
        return temperaturaf;
    }

    public void setTemperaturaf(String temperaturaf) {
        this.temperaturaf = temperaturaf;
    }

    public String getTemperaturac() {
        return temperaturac;
    }

    public void setTemperaturac(String temperaturac) {
        this.temperaturac = temperaturac;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getUrlicon() {
        return urlicon;
    }

    public void setUrlicon(String urlicon) {
        this.urlicon = urlicon;
    }

    public String getViento() {
        return viento;
    }

    public void setViento(String viento) {
        this.viento = viento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public List<Pronostico> getPronosticoList() {
        return pronosticoList;
    }

    public void setPronosticoList(List<Pronostico> pronosticoList) {
        this.pronosticoList = pronosticoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (secCondicionactual != null ? secCondicionactual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicionactual)) {
            return false;
        }
        Condicionactual other = (Condicionactual) object;
        if ((this.secCondicionactual == null && other.secCondicionactual != null) || (this.secCondicionactual != null && !this.secCondicionactual.equals(other.secCondicionactual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Condicionactual[secCondicionactual=" + secCondicionactual + "]";
    }

}
