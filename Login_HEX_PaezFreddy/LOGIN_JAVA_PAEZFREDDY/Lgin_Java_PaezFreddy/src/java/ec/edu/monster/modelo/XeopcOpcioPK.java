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
public class XeopcOpcioPK implements Serializable {

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

    public XeopcOpcioPK() {
    }

    public XeopcOpcioPK(String xevenCodigo, String xeopcCodigo) {
        this.xevenCodigo = xevenCodigo;
        this.xeopcCodigo = xeopcCodigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xevenCodigo != null ? xevenCodigo.hashCode() : 0);
        hash += (xeopcCodigo != null ? xeopcCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeopcOpcioPK)) {
            return false;
        }
        XeopcOpcioPK other = (XeopcOpcioPK) object;
        if ((this.xevenCodigo == null && other.xevenCodigo != null) || (this.xevenCodigo != null && !this.xevenCodigo.equals(other.xevenCodigo))) {
            return false;
        }
        if ((this.xeopcCodigo == null && other.xeopcCodigo != null) || (this.xeopcCodigo != null && !this.xeopcCodigo.equals(other.xeopcCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeopcOpcioPK[ xevenCodigo=" + xevenCodigo + ", xeopcCodigo=" + xeopcCodigo + " ]";
    }
    
}
