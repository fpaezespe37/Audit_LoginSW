/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian Novoa
 */
@Entity
@Table(name = "xeoxp_opcpe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeoxpOpcpe.findAll", query = "SELECT x FROM XeoxpOpcpe x"),
    @NamedQuery(name = "XeoxpOpcpe.findByXevenCodigo", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xevenCodigo = :xevenCodigo"),
    @NamedQuery(name = "XeoxpOpcpe.findByXeopcCodigo", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeopcCodigo = :xeopcCodigo"),
    @NamedQuery(name = "XeoxpOpcpe.findByXeperCodigo", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeperCodigo = :xeperCodigo"),
    @NamedQuery(name = "XeoxpOpcpe.findByXeoxpOpcper", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpOpcpePK.xeoxpOpcper = :xeoxpOpcper"),
    @NamedQuery(name = "XeoxpOpcpe.findByXeoxpFecasi", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpFecasi = :xeoxpFecasi"),
    @NamedQuery(name = "XeoxpOpcpe.findByXeoxpFecret", query = "SELECT x FROM XeoxpOpcpe x WHERE x.xeoxpFecret = :xeoxpFecret")})
public class XeoxpOpcpe implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected XeoxpOpcpePK xeoxpOpcpePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEOXP_FECASI")
    @Temporal(TemporalType.DATE)
    private Date xeoxpFecasi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "XEOXP_FECRET")
    @Temporal(TemporalType.DATE)
    private Date xeoxpFecret;
    @JoinColumns({
        @JoinColumn(name = "XEVEN_CODIGO", referencedColumnName = "XEVEN_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "XEOPC_CODIGO", referencedColumnName = "XEOPC_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private XeopcOpcio xeopcOpcio;
    @JoinColumn(name = "XEPER_CODIGO", referencedColumnName = "XEPER_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private XeperPerfi xeperPerfi;

    public XeoxpOpcpe() {
    }

    public XeoxpOpcpe(XeoxpOpcpePK xeoxpOpcpePK) {
        this.xeoxpOpcpePK = xeoxpOpcpePK;
    }

    public XeoxpOpcpe(XeoxpOpcpePK xeoxpOpcpePK, Date xeoxpFecasi, Date xeoxpFecret) {
        this.xeoxpOpcpePK = xeoxpOpcpePK;
        this.xeoxpFecasi = xeoxpFecasi;
        this.xeoxpFecret = xeoxpFecret;
    }

    public XeoxpOpcpe(String xevenCodigo, String xeopcCodigo, String xeperCodigo, String xeoxpOpcper) {
        this.xeoxpOpcpePK = new XeoxpOpcpePK(xevenCodigo, xeopcCodigo, xeperCodigo, xeoxpOpcper);
    }

    public XeoxpOpcpePK getXeoxpOpcpePK() {
        return xeoxpOpcpePK;
    }

    public void setXeoxpOpcpePK(XeoxpOpcpePK xeoxpOpcpePK) {
        this.xeoxpOpcpePK = xeoxpOpcpePK;
    }

    public Date getXeoxpFecasi() {
        return xeoxpFecasi;
    }

    public void setXeoxpFecasi(Date xeoxpFecasi) {
        this.xeoxpFecasi = xeoxpFecasi;
    }

    public Date getXeoxpFecret() {
        return xeoxpFecret;
    }

    public void setXeoxpFecret(Date xeoxpFecret) {
        this.xeoxpFecret = xeoxpFecret;
    }

    public XeopcOpcio getXeopcOpcio() {
        return xeopcOpcio;
    }

    public void setXeopcOpcio(XeopcOpcio xeopcOpcio) {
        this.xeopcOpcio = xeopcOpcio;
    }

    public XeperPerfi getXeperPerfi() {
        return xeperPerfi;
    }

    public void setXeperPerfi(XeperPerfi xeperPerfi) {
        this.xeperPerfi = xeperPerfi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeoxpOpcpePK != null ? xeoxpOpcpePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeoxpOpcpe)) {
            return false;
        }
        XeoxpOpcpe other = (XeoxpOpcpe) object;
        if ((this.xeoxpOpcpePK == null && other.xeoxpOpcpePK != null) || (this.xeoxpOpcpePK != null && !this.xeoxpOpcpePK.equals(other.xeoxpOpcpePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeoxpOpcpe[ xeoxpOpcpePK=" + xeoxpOpcpePK + " ]";
    }
    
}
