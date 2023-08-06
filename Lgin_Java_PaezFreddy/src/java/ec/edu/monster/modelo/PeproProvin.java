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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pepro_provin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeproProvin.findAll", query = "SELECT p FROM PeproProvin p"),
    @NamedQuery(name = "PeproProvin.findByPepaiCodigo", query = "SELECT p FROM PeproProvin p WHERE p.peproProvinPK.pepaiCodigo = :pepaiCodigo"),
    @NamedQuery(name = "PeproProvin.findByPeproCodigo", query = "SELECT p FROM PeproProvin p WHERE p.peproProvinPK.peproCodigo = :peproCodigo"),
    @NamedQuery(name = "PeproProvin.findByPeproNombre", query = "SELECT p FROM PeproProvin p WHERE p.peproNombre = :peproNombre")})
public class PeproProvin implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeproProvinPK peproProvinPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPRO_NOMBRE")
    private String peproNombre;
    @JoinColumn(name = "PEPAI_CODIGO", referencedColumnName = "PEPAI_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PepaiPais pepaiPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peproProvin")
    private Collection<PecanCanton> pecanCantonCollection;

    public PeproProvin() {
    }

    public PeproProvin(PeproProvinPK peproProvinPK) {
        this.peproProvinPK = peproProvinPK;
    }

    public PeproProvin(PeproProvinPK peproProvinPK, String peproNombre) {
        this.peproProvinPK = peproProvinPK;
        this.peproNombre = peproNombre;
    }

    public PeproProvin(String pepaiCodigo, String peproCodigo) {
        this.peproProvinPK = new PeproProvinPK(pepaiCodigo, peproCodigo);
    }

    public PeproProvinPK getPeproProvinPK() {
        return peproProvinPK;
    }

    public void setPeproProvinPK(PeproProvinPK peproProvinPK) {
        this.peproProvinPK = peproProvinPK;
    }

    public String getPeproNombre() {
        return peproNombre;
    }

    public void setPeproNombre(String peproNombre) {
        this.peproNombre = peproNombre;
    }

    public PepaiPais getPepaiPais() {
        return pepaiPais;
    }

    public void setPepaiPais(PepaiPais pepaiPais) {
        this.pepaiPais = pepaiPais;
    }

    @XmlTransient
    public Collection<PecanCanton> getPecanCantonCollection() {
        return pecanCantonCollection;
    }

    public void setPecanCantonCollection(Collection<PecanCanton> pecanCantonCollection) {
        this.pecanCantonCollection = pecanCantonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peproProvinPK != null ? peproProvinPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeproProvin)) {
            return false;
        }
        PeproProvin other = (PeproProvin) object;
        if ((this.peproProvinPK == null && other.peproProvinPK != null) || (this.peproProvinPK != null && !this.peproProvinPK.equals(other.peproProvinPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PeproProvin[ peproProvinPK=" + peproProvinPK + " ]";
    }
    
}
