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
public class XeuxpUsupePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "XEUSU_CODIGO")
    private String xeusuCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "XEPER_CODIGO")
    private String xeperCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "XEUXP_CODUSU")
    private String xeuxpCodusu;

    public XeuxpUsupePK() {
    }

    public XeuxpUsupePK(String xeusuCodigo, String xeperCodigo, String xeuxpCodusu) {
        this.xeusuCodigo = xeusuCodigo;
        this.xeperCodigo = xeperCodigo;
        this.xeuxpCodusu = xeuxpCodusu;
    }

    public String getXeusuCodigo() {
        return xeusuCodigo;
    }

    public void setXeusuCodigo(String xeusuCodigo) {
        this.xeusuCodigo = xeusuCodigo;
    }

    public String getXeperCodigo() {
        return xeperCodigo;
    }

    public void setXeperCodigo(String xeperCodigo) {
        this.xeperCodigo = xeperCodigo;
    }

    public String getXeuxpCodusu() {
        return xeuxpCodusu;
    }

    public void setXeuxpCodusu(String xeuxpCodusu) {
        this.xeuxpCodusu = xeuxpCodusu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xeusuCodigo != null ? xeusuCodigo.hashCode() : 0);
        hash += (xeperCodigo != null ? xeperCodigo.hashCode() : 0);
        hash += (xeuxpCodusu != null ? xeuxpCodusu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XeuxpUsupePK)) {
            return false;
        }
        XeuxpUsupePK other = (XeuxpUsupePK) object;
        if ((this.xeusuCodigo == null && other.xeusuCodigo != null) || (this.xeusuCodigo != null && !this.xeusuCodigo.equals(other.xeusuCodigo))) {
            return false;
        }
        if ((this.xeperCodigo == null && other.xeperCodigo != null) || (this.xeperCodigo != null && !this.xeperCodigo.equals(other.xeperCodigo))) {
            return false;
        }
        if ((this.xeuxpCodusu == null && other.xeuxpCodusu != null) || (this.xeuxpCodusu != null && !this.xeuxpCodusu.equals(other.xeuxpCodusu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.XeuxpUsupePK[ xeusuCodigo=" + xeusuCodigo + ", xeperCodigo=" + xeperCodigo + ", xeuxpCodusu=" + xeuxpCodusu + " ]";
    }
    
}
