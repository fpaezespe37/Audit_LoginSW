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
import javax.persistence.JoinColumns;
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
@Table(name = "pepar_parroq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeparParroq.findAll", query = "SELECT p FROM PeparParroq p"),
    @NamedQuery(name = "PeparParroq.findByPepaiCodigo", query = "SELECT p FROM PeparParroq p WHERE p.peparParroqPK.pepaiCodigo = :pepaiCodigo"),
    @NamedQuery(name = "PeparParroq.findByPeproCodigo", query = "SELECT p FROM PeparParroq p WHERE p.peparParroqPK.peproCodigo = :peproCodigo"),
    @NamedQuery(name = "PeparParroq.findByPecanCodigo", query = "SELECT p FROM PeparParroq p WHERE p.peparParroqPK.pecanCodigo = :pecanCodigo"),
    @NamedQuery(name = "PeparParroq.findByPeparCodigo", query = "SELECT p FROM PeparParroq p WHERE p.peparParroqPK.peparCodigo = :peparCodigo"),
    @NamedQuery(name = "PeparParroq.findByPeparDescri", query = "SELECT p FROM PeparParroq p WHERE p.peparDescri = :peparDescri")})
public class PeparParroq implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeparParroqPK peparParroqPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEPAR_DESCRI")
    private String peparDescri;
    @JoinColumns({
        @JoinColumn(name = "PEPAI_CODIGO", referencedColumnName = "PEPAI_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "PEPRO_CODIGO", referencedColumnName = "PEPRO_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "PECAN_CODIGO", referencedColumnName = "PECAN_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PecanCanton pecanCanton;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peparParroq")
    private Collection<PedirDirecc> pedirDireccCollection;

    public PeparParroq() {
    }

    public PeparParroq(PeparParroqPK peparParroqPK) {
        this.peparParroqPK = peparParroqPK;
    }

    public PeparParroq(PeparParroqPK peparParroqPK, String peparDescri) {
        this.peparParroqPK = peparParroqPK;
        this.peparDescri = peparDescri;
    }

    public PeparParroq(String pepaiCodigo, String peproCodigo, String pecanCodigo, String peparCodigo) {
        this.peparParroqPK = new PeparParroqPK(pepaiCodigo, peproCodigo, pecanCodigo, peparCodigo);
    }

    public PeparParroqPK getPeparParroqPK() {
        return peparParroqPK;
    }

    public void setPeparParroqPK(PeparParroqPK peparParroqPK) {
        this.peparParroqPK = peparParroqPK;
    }

    public String getPeparDescri() {
        return peparDescri;
    }

    public void setPeparDescri(String peparDescri) {
        this.peparDescri = peparDescri;
    }

    public PecanCanton getPecanCanton() {
        return pecanCanton;
    }

    public void setPecanCanton(PecanCanton pecanCanton) {
        this.pecanCanton = pecanCanton;
    }

    @XmlTransient
    public Collection<PedirDirecc> getPedirDireccCollection() {
        return pedirDireccCollection;
    }

    public void setPedirDireccCollection(Collection<PedirDirecc> pedirDireccCollection) {
        this.pedirDireccCollection = pedirDireccCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peparParroqPK != null ? peparParroqPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeparParroq)) {
            return false;
        }
        PeparParroq other = (PeparParroq) object;
        if ((this.peparParroqPK == null && other.peparParroqPK != null) || (this.peparParroqPK != null && !this.peparParroqPK.equals(other.peparParroqPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pecanCanton.getPeproProvin().getPepaiPais().getPepaiDescri()+" > "+pecanCanton.getPeproProvin().getPeproNombre()+" > "+pecanCanton.getPecanDescri()+" > "+peparDescri;
    }
    
}
