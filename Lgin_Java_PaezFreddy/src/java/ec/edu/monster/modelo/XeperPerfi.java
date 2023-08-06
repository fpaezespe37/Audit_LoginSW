/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "xeper_perfi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "XeperPerfi.findAll", query = "SELECT x FROM XeperPerfi x")
    , @NamedQuery(name = "XeperPerfi.findByXeperCodigo", query = "SELECT x FROM XeperPerfi x WHERE x.xeperCodigo = :xeperCodigo")
    , @NamedQuery(name = "XeperPerfi.findByXeperDescri", query = "SELECT x FROM XeperPerfi x WHERE x.xeperDescri = :xeperDescri")})
public class XeperPerfi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEPER_CODIGO")
    private String xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "XEPER_DESCRI")
    private String xeperDescri;
    @Lob
    @Size(max = 65535)
    @Column(name = "XEPER_OBSER")
    private String xeperObser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeperPerfi")
    private List<XeuxpUsupe> xeuxpUsupeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xeperPerfi")
    private List<XeoxpOpcpe> xeoxpOpcpeList;

    public XeperPerfi() {
    }

    public XeperPerfi(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public XeperPerfi(String xeperCodigo, String xeperDescri) {
        this.xeperCodigo = xeperCodigo;
        this.xeperDescri = xeperDescri;
    }

    public String getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public String getXeperDescri() {
        return xeperDescri;
    }

    public void setXeperDescri(String xeperDescri) {
        this.xeperDescri = xeperDescri;
    }

    public String getXeperObser() {
        return xeperObser;
    }

    public void setXeperObser(String xeperObser) {
        this.xeperObser = xeperObser;
    }

    @XmlTransient
    public List<XeuxpUsupe> getXeuxpUsupeList() {
        return xeuxpUsupeList;
    }

    public void setXeuxpUsupeList(List<XeuxpUsupe> xeuxpUsupeList) {
        this.xeuxpUsupeList = xeuxpUsupeList;
    }

    @XmlTransient
    public List<XeoxpOpcpe> getXeoxpOpcpeList() {
        return xeoxpOpcpeList;
    }

    public void setXeoxpOpcpeList(List<XeoxpOpcpe> xeoxpOpcpeList) {
        this.xeoxpOpcpeList = xeoxpOpcpeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeperCodigo != null ? xeperCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeperPerfi)) {
            return false;
        }
        XeperPerfi other = (XeperPerfi) object;
        if ((this.xeperCodigo == null && other.xeperCodigo != null) || (this.xeperCodigo != null && !this.xeperCodigo.equals(other.xeperCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "ec.edu.monster.jpa.XeperPerfi[ xeperCodigo=" + xeperCodigo + " ]";
        return xeperCodigo+"-"+xeperDescri;
    }
    
}
