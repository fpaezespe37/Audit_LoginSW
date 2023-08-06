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
@Table(name = "xesis_ventan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XesisVentan.findAll", query = "SELECT x FROM XesisVentan x"),
    @NamedQuery(name = "XesisVentan.findByXevenCodigo", query = "SELECT x FROM XesisVentan x WHERE x.xevenCodigo = :xevenCodigo"),
    @NamedQuery(name = "XesisVentan.findByXevenDescri", query = "SELECT x FROM XesisVentan x WHERE x.xevenDescri = :xevenDescri"),
    @NamedQuery(name = "XesisVentan.findByXevenMensaj", query = "SELECT x FROM XesisVentan x WHERE x.xevenMensaj = :xevenMensaj"),
    @NamedQuery(name = "XesisVentan.findByXevenSiste", query = "SELECT x FROM XesisVentan x WHERE x.xesisCodigo = :xesisCodigo")})
public class XesisVentan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "XEVEN_CODIGO")
    private String xevenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEVEN_DESCRI")
    private String xevenDescri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEVEN_MENSAJ")
    private String xevenMensaj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xesisVentan")
    private Collection<XeopcOpcio> xeopcOpcioCollection;
    @JoinColumn(name = "XESIS_CODIGO", referencedColumnName = "XESIS_CODIGO")
    @ManyToOne(optional = false)
    private XesisSiste xesisCodigo;

    public XesisVentan() {
    }

    public XesisVentan(String xevenCodigo) {
        this.xevenCodigo = xevenCodigo;
    }

    public XesisVentan(String xevenCodigo, String xevenDescri, String xevenMensaj) {
        this.xevenCodigo = xevenCodigo;
        this.xevenDescri = xevenDescri;
        this.xevenMensaj = xevenMensaj;
    }

    public String getXevenCodigo() {
        return xevenCodigo;
    }

    public void setXevenCodigo(String xevenCodigo) {
        this.xevenCodigo = xevenCodigo;
    }

    public String getXevenDescri() {
        return xevenDescri;
    }

    public void setXevenDescri(String xevenDescri) {
        this.xevenDescri = xevenDescri;
    }

    public String getXevenMensaj() {
        return xevenMensaj;
    }

    public void setXevenMensaj(String xevenMensaj) {
        this.xevenMensaj = xevenMensaj;
    }

    public XesisSiste getXesisCodigo() {
        return xesisCodigo;
    }

    public void setXesisCodigo(XesisSiste xesisCodigo) {
        this.xesisCodigo = xesisCodigo;
    }

    @XmlTransient
    public Collection<XeopcOpcio> getXeopcOpcioCollection() {
        return xeopcOpcioCollection;
    }

    public void setXeopcOpcioCollection(Collection<XeopcOpcio> xeopcOpcioCollection) {
        this.xeopcOpcioCollection = xeopcOpcioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xevenCodigo != null ? xevenCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XesisVentan)) {
            return false;
        }
        XesisVentan other = (XesisVentan) object;
        if ((this.xevenCodigo == null && other.xevenCodigo != null) || (this.xevenCodigo != null && !this.xevenCodigo.equals(other.xevenCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return xevenMensaj;
    }
    
}
