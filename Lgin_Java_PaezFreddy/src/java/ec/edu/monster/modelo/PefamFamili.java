/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian Novoa
 */
@Entity
@Table(name = "pefam_famili")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PefamFamili.findAll", query = "SELECT p FROM PefamFamili p"),
    @NamedQuery(name = "PefamFamili.findByPeempCurp", query = "SELECT p FROM PefamFamili p WHERE p.peempCurp = :peempCurp"),
    @NamedQuery(name = "PefamFamili.findByPefamFamnombre", query = "SELECT p FROM PefamFamili p WHERE p.pefamFamnombre = :pefamFamnombre"),
    @NamedQuery(name = "PefamFamili.findByPefamFamapellpaterno", query = "SELECT p FROM PefamFamili p WHERE p.pefamFamapellpaterno = :pefamFamapellpaterno"),
    @NamedQuery(name = "PefamFamili.findByPefamFamapellmaterno", query = "SELECT p FROM PefamFamili p WHERE p.pefamFamapellmaterno = :pefamFamapellmaterno"),
    @NamedQuery(name = "PefamFamili.findByPefamFamfecnacimiento", query = "SELECT p FROM PefamFamili p WHERE p.pefamFamfecnacimiento = :pefamFamfecnacimiento"),
    @NamedQuery(name = "PefamFamili.findByPefamParentesco", query = "SELECT p FROM PefamFamili p WHERE p.pefamParentesco = :pefamParentesco")})
public class PefamFamili implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "PEEMP_CURP")
    private String peempCurp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "PEFAM_FAMNOMBRE")
    private String pefamFamnombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "PEFAM_FAMAPELLPATERNO")
    private String pefamFamapellpaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "PEFAM_FAMAPELLMATERNO")
    private String pefamFamapellmaterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEFAM_FAMFECNACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date pefamFamfecnacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PEFAM_PARENTESCO")
    private String pefamParentesco;
    @JoinColumn(name = "PEEMP_CURP", referencedColumnName = "PEEMP_CURP", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PeempEmple peempEmple;
    @JoinColumn(name = "CODGENERO", referencedColumnName = "CODGENERO")
    @ManyToOne(optional = false)
    private PegenGener codgenero;

    public PefamFamili() {
    }

    public PefamFamili(String peempCurp) {
        this.peempCurp = peempCurp;
    }

    public PefamFamili(String peempCurp, String pefamFamnombre, String pefamFamapellpaterno, String pefamFamapellmaterno, Date pefamFamfecnacimiento, String pefamParentesco) {
        this.peempCurp = peempCurp;
        this.pefamFamnombre = pefamFamnombre;
        this.pefamFamapellpaterno = pefamFamapellpaterno;
        this.pefamFamapellmaterno = pefamFamapellmaterno;
        this.pefamFamfecnacimiento = pefamFamfecnacimiento;
        this.pefamParentesco = pefamParentesco;
    }

    public String getPeempCurp() {
        return peempCurp;
    }

    public void setPeempCurp(String peempCurp) {
        this.peempCurp = peempCurp;
    }

    public String getPefamFamnombre() {
        return pefamFamnombre;
    }

    public void setPefamFamnombre(String pefamFamnombre) {
        this.pefamFamnombre = pefamFamnombre;
    }

    public String getPefamFamapellpaterno() {
        return pefamFamapellpaterno;
    }

    public void setPefamFamapellpaterno(String pefamFamapellpaterno) {
        this.pefamFamapellpaterno = pefamFamapellpaterno;
    }

    public String getPefamFamapellmaterno() {
        return pefamFamapellmaterno;
    }

    public void setPefamFamapellmaterno(String pefamFamapellmaterno) {
        this.pefamFamapellmaterno = pefamFamapellmaterno;
    }

    public Date getPefamFamfecnacimiento() {
        return pefamFamfecnacimiento;
    }

    public void setPefamFamfecnacimiento(Date pefamFamfecnacimiento) {
        this.pefamFamfecnacimiento = pefamFamfecnacimiento;
    }

    public String getPefamParentesco() {
        return pefamParentesco;
    }

    public void setPefamParentesco(String pefamParentesco) {
        this.pefamParentesco = pefamParentesco;
    }

    public PeempEmple getPeempEmple() {
        return peempEmple;
    }

    public void setPeempEmple(PeempEmple peempEmple) {
        this.peempEmple = peempEmple;
    }

    public PegenGener getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(PegenGener codgenero) {
        this.codgenero = codgenero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peempCurp != null ? peempCurp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PefamFamili)) {
            return false;
        }
        PefamFamili other = (PefamFamili) object;
        if ((this.peempCurp == null && other.peempCurp != null) || (this.peempCurp != null && !this.peempCurp.equals(other.peempCurp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PefamFamili[ peempCurp=" + peempCurp + " ]";
    }
    
}
