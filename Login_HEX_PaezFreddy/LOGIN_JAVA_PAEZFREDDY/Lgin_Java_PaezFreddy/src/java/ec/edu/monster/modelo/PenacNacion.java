/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Christian Novoa
 */
@Entity
@Table(name = "penac_nacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PenacNacion.findAll", query = "SELECT p FROM PenacNacion p"),
    @NamedQuery(name = "PenacNacion.findByCodnacion", query = "SELECT p FROM PenacNacion p WHERE p.codnacion = :codnacion"),
    @NamedQuery(name = "PenacNacion.findByDescrinac", query = "SELECT p FROM PenacNacion p WHERE p.descrinac = :descrinac")})
public class PenacNacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODNACION")
    private String codnacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRINAC")
    private String descrinac;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codnacion")
    private Collection<PeempEmple> peempEmpleCollection;

    public PenacNacion() {
    }

    public PenacNacion(String codnacion) {
        this.codnacion = codnacion;
    }

    public PenacNacion(String codnacion, String descrinac) {
        this.codnacion = codnacion;
        this.descrinac = descrinac;
    }

    public String getCodnacion() {
        return codnacion;
    }

    public void setCodnacion(String codnacion) {
        this.codnacion = codnacion;
    }

    public String getDescrinac() {
        return descrinac;
    }

    public void setDescrinac(String descrinac) {
        this.descrinac = descrinac;
    }

    @XmlTransient
    public Collection<PeempEmple> getPeempEmpleCollection() {
        return peempEmpleCollection;
    }

    public void setPeempEmpleCollection(Collection<PeempEmple> peempEmpleCollection) {
        this.peempEmpleCollection = peempEmpleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codnacion != null ? codnacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PenacNacion)) {
            return false;
        }
        PenacNacion other = (PenacNacion) object;
        if ((this.codnacion == null && other.codnacion != null) || (this.codnacion != null && !this.codnacion.equals(other.codnacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descrinac;
    }
    
}
