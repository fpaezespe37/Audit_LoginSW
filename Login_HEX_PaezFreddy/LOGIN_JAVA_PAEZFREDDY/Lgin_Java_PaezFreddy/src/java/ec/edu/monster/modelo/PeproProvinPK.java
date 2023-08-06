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
public class PeproProvinPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "PEPAI_CODIGO")
    private String pepaiCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "PEPRO_CODIGO")
    private String peproCodigo;

    public PeproProvinPK() {
    }

    public PeproProvinPK(String pepaiCodigo, String peproCodigo) {
        this.pepaiCodigo = pepaiCodigo;
        this.peproCodigo = peproCodigo;
    }

    public String getPepaiCodigo() {
        return pepaiCodigo;
    }

    public void setPepaiCodigo(String pepaiCodigo) {
        this.pepaiCodigo = pepaiCodigo;
    }

    public String getPeproCodigo() {
        return peproCodigo;
    }

    public void setPeproCodigo(String peproCodigo) {
        this.peproCodigo = peproCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pepaiCodigo != null ? pepaiCodigo.hashCode() : 0);
        hash += (peproCodigo != null ? peproCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeproProvinPK)) {
            return false;
        }
        PeproProvinPK other = (PeproProvinPK) object;
        if ((this.pepaiCodigo == null && other.pepaiCodigo != null) || (this.pepaiCodigo != null && !this.pepaiCodigo.equals(other.pepaiCodigo))) {
            return false;
        }
        if ((this.peproCodigo == null && other.peproCodigo != null) || (this.peproCodigo != null && !this.peproCodigo.equals(other.peproCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PeproProvinPK[ pepaiCodigo=" + pepaiCodigo + ", peproCodigo=" + peproCodigo + " ]";
    }
    
}
