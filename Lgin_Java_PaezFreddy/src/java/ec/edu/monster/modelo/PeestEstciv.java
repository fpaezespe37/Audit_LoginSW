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
@Table(name = "peest_estciv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeestEstciv.findAll", query = "SELECT p FROM PeestEstciv p"),
    @NamedQuery(name = "PeestEstciv.findByPeescCodigo", query = "SELECT p FROM PeestEstciv p WHERE p.peescCodigo = :peescCodigo"),
    @NamedQuery(name = "PeestEstciv.findByPeescNomb", query = "SELECT p FROM PeestEstciv p WHERE p.peescNomb = :peescNomb"),
    @NamedQuery(name = "PeestEstciv.findByPeescDescri", query = "SELECT p FROM PeestEstciv p WHERE p.peescDescri = :peescDescri")})
public class PeestEstciv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PEESC_CODIGO")
    private String peescCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PEESC_NOMB")
    private String peescNomb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PEESC_DESCRI")
    private String peescDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peescCodigo")
    private Collection<PeempEmple> peempEmpleCollection;

    public PeestEstciv() {
    }

    public PeestEstciv(String peescCodigo) {
        this.peescCodigo = peescCodigo;
    }

    public PeestEstciv(String peescCodigo, String peescNomb, String peescDescri) {
        this.peescCodigo = peescCodigo;
        this.peescNomb = peescNomb;
        this.peescDescri = peescDescri;
    }

    public String getPeescCodigo() {
        return peescCodigo;
    }

    public void setPeescCodigo(String peescCodigo) {
        this.peescCodigo = peescCodigo;
    }

    public String getPeescNomb() {
        return peescNomb;
    }

    public void setPeescNomb(String peescNomb) {
        this.peescNomb = peescNomb;
    }

    public String getPeescDescri() {
        return peescDescri;
    }

    public void setPeescDescri(String peescDescri) {
        this.peescDescri = peescDescri;
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
        hash += (peescCodigo != null ? peescCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeestEstciv)) {
            return false;
        }
        PeestEstciv other = (PeestEstciv) object;
        if ((this.peescCodigo == null && other.peescCodigo != null) || (this.peescCodigo != null && !this.peescCodigo.equals(other.peescCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return peescNomb;
    }
    
}
