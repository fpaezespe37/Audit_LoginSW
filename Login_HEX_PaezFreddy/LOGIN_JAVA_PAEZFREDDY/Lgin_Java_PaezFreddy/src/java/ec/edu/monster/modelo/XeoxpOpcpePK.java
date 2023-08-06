/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Christian Novoa
 */
@Embeddable
public class XeoxpOpcpePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "XEVEN_CODIGO")
    private String xevenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "XEOPC_CODIGO")
    private String xeopcCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEPER_CODIGO")
    private String xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "XEOXP_OPCPER")
    private String xeoxpOpcper;

    public XeoxpOpcpePK() {
    }

    public XeoxpOpcpePK(String xevenCodigo, String xeopcCodigo, String xeperCodigo, String xeoxpOpcper) {
        this.xevenCodigo = xevenCodigo;
        this.xeopcCodigo = xeopcCodigo;
        this.xeperCodigo = xeperCodigo;
        this.xeoxpOpcper = xeoxpOpcper;
    }

    public String getXevenCodigo() {
        return xevenCodigo;
    }

    public void setXevenCodigo(String xevenCodigo) {
        this.xevenCodigo = xevenCodigo;
    }

    public String getXeopcCodigo() {
        return xeopcCodigo;
    }

    public void setXeopcCodigo(String xeopcCodigo) {
        this.xeopcCodigo = xeopcCodigo;
    }

    public String getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public String getXeoxpOpcper() {
        return xeoxpOpcper;
    }

    public void setXeoxpOpcper(String xeoxpOpcper) {
        this.xeoxpOpcper = xeoxpOpcper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xevenCodigo != null ? xevenCodigo.hashCode() : 0);
        hash += (xeopcCodigo != null ? xeopcCodigo.hashCode() : 0);
        hash += (xeperCodigo != null ? xeperCodigo.hashCode() : 0);
        hash += (xeoxpOpcper != null ? xeoxpOpcper.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeoxpOpcpePK)) {
            return false;
        }
        XeoxpOpcpePK other = (XeoxpOpcpePK) object;
        if ((this.xevenCodigo == null && other.xevenCodigo != null) || (this.xevenCodigo != null && !this.xevenCodigo.equals(other.xevenCodigo))) {
            return false;
        }
        if ((this.xeopcCodigo == null && other.xeopcCodigo != null) || (this.xeopcCodigo != null && !this.xeopcCodigo.equals(other.xeopcCodigo))) {
            return false;
        }
        if ((this.xeperCodigo == null && other.xeperCodigo != null) || (this.xeperCodigo != null && !this.xeperCodigo.equals(other.xeperCodigo))) {
            return false;
        }
        if ((this.xeoxpOpcper == null && other.xeoxpOpcper != null) || (this.xeoxpOpcper != null && !this.xeoxpOpcper.equals(other.xeoxpOpcper))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeoxpOpcpePK[ xevenCodigo=" + xevenCodigo + ", xeopcCodigo=" + xeopcCodigo + ", xeperCodigo=" + xeperCodigo + ", xeoxpOpcper=" + xeoxpOpcper + " ]";
    }
    
}
