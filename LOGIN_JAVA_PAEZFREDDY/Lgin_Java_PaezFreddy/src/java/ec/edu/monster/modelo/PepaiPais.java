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
@Table(name = "pepai_pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PepaiPais.findAll", query = "SELECT p FROM PepaiPais p"),
    @NamedQuery(name = "PepaiPais.findByPepaiCodigo", query = "SELECT p FROM PepaiPais p WHERE p.pepaiCodigo = :pepaiCodigo"),
    @NamedQuery(name = "PepaiPais.findByPepaiDescri", query = "SELECT p FROM PepaiPais p WHERE p.pepaiDescri = :pepaiDescri")})
public class PepaiPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "PEPAI_CODIGO")
    private String pepaiCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPAI_DESCRI")
    private String pepaiDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pepaiPais")
    private Collection<PeproProvin> peproProvinCollection;

    public PepaiPais() {
    }

    public PepaiPais(String pepaiCodigo) {
        this.pepaiCodigo = pepaiCodigo;
    }

    public PepaiPais(String pepaiCodigo, String pepaiDescri) {
        this.pepaiCodigo = pepaiCodigo;
        this.pepaiDescri = pepaiDescri;
    }

    public String getPepaiCodigo() {
        return pepaiCodigo;
    }

    public void setPepaiCodigo(String pepaiCodigo) {
        this.pepaiCodigo = pepaiCodigo;
    }

    public String getPepaiDescri() {
        return pepaiDescri;
    }

    public void setPepaiDescri(String pepaiDescri) {
        this.pepaiDescri = pepaiDescri;
    }

    @XmlTransient
    public Collection<PeproProvin> getPeproProvinCollection() {
        return peproProvinCollection;
    }

    public void setPeproProvinCollection(Collection<PeproProvin> peproProvinCollection) {
        this.peproProvinCollection = peproProvinCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pepaiCodigo != null ? pepaiCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PepaiPais)) {
            return false;
        }
        PepaiPais other = (PepaiPais) object;
        if ((this.pepaiCodigo == null && other.pepaiCodigo != null) || (this.pepaiCodigo != null && !this.pepaiCodigo.equals(other.pepaiCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PepaiPais[ pepaiCodigo=" + pepaiCodigo + " ]";
    }
    
}
