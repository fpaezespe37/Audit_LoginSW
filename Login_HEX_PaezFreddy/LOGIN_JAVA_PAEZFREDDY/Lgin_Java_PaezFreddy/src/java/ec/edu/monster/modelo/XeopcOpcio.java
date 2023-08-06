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
@Table(name = "xeopc_opcio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeopcOpcio.findAll", query = "SELECT x FROM XeopcOpcio x"),
    @NamedQuery(name = "XeopcOpcio.findByXevenCodigo", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcOpcioPK.xevenCodigo = :xevenCodigo"),
    @NamedQuery(name = "XeopcOpcio.findByXeopcCodigo", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcOpcioPK.xeopcCodigo = :xeopcCodigo"),
    @NamedQuery(name = "XeopcOpcio.findByXeopcDescri", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcDescri = :xeopcDescri"),
    @NamedQuery(name = "XeopcOpcio.findByXeopcUrl", query = "SELECT x FROM XeopcOpcio x WHERE x.xeopcUrl = :xeopcUrl")})
public class XeopcOpcio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected XeopcOpcioPK xeopcOpcioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEOPC_DESCRI")
    private String xeopcDescri;
    @Size(max = 100)
    @Column(name = "XEOPC_URL")
    private String xeopcUrl;
    @JoinColumn(name = "XEVEN_CODIGO", referencedColumnName = "XEVEN_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private XesisVentan xesisVentan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeopcOpcio")
    private Collection<XeoxpOpcpe> xeoxpOpcpeCollection;

    public XeopcOpcio() {
    }

    public XeopcOpcio(XeopcOpcioPK xeopcOpcioPK) {
        this.xeopcOpcioPK = xeopcOpcioPK;
    }

    public XeopcOpcio(XeopcOpcioPK xeopcOpcioPK, String xeopcDescri) {
        this.xeopcOpcioPK = xeopcOpcioPK;
        this.xeopcDescri = xeopcDescri;
    }

    public XeopcOpcio(String xevenCodigo, String xeopcCodigo) {
        this.xeopcOpcioPK = new XeopcOpcioPK(xevenCodigo, xeopcCodigo);
    }

    public XeopcOpcioPK getXeopcOpcioPK() {
        return xeopcOpcioPK;
    }

    public void setXeopcOpcioPK(XeopcOpcioPK xeopcOpcioPK) {
        this.xeopcOpcioPK = xeopcOpcioPK;
    }

    public String getXeopcDescri() {
        return xeopcDescri;
    }

    public void setXeopcDescri(String xeopcDescri) {
        this.xeopcDescri = xeopcDescri;
    }

    public String getXeopcUrl() {
        return xeopcUrl;
    }

    public void setXeopcUrl(String xeopcUrl) {
        this.xeopcUrl = xeopcUrl;
    }

    public XesisVentan getXesisVentan() {
        return xesisVentan;
    }

    public void setXesisVentan(XesisVentan xesisVentan) {
        this.xesisVentan = xesisVentan;
    }

    @XmlTransient
    public Collection<XeoxpOpcpe> getXeoxpOpcpeCollection() {
        return xeoxpOpcpeCollection;
    }

    public void setXeoxpOpcpeCollection(Collection<XeoxpOpcpe> xeoxpOpcpeCollection) {
        this.xeoxpOpcpeCollection = xeoxpOpcpeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeopcOpcioPK != null ? xeopcOpcioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeopcOpcio)) {
            return false;
        }
        XeopcOpcio other = (XeopcOpcio) object;
        if ((this.xeopcOpcioPK == null && other.xeopcOpcioPK != null) || (this.xeopcOpcioPK != null && !this.xeopcOpcioPK.equals(other.xeopcOpcioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeopcOpcio[ xeopcOpcioPK=" + xeopcOpcioPK + " ]";
    }
    
}
