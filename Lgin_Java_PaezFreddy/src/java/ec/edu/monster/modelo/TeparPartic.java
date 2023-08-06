/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Christian Novoa
 */
@Entity
@Table(name = "tepar_partic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeparPartic.findAll", query = "SELECT t FROM TeparPartic t"),
    @NamedQuery(name = "TeparPartic.findByCodproyec", query = "SELECT t FROM TeparPartic t WHERE t.codproyec = :codproyec"),
    @NamedQuery(name = "TeparPartic.findByHoras", query = "SELECT t FROM TeparPartic t WHERE t.horas = :horas")})
public class TeparPartic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODPROYEC")
    private String codproyec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS")
    private int horas;
    @JoinColumn(name = "PEEMP_CURP", referencedColumnName = "PEEMP_CURP")
    @ManyToOne(optional = false)
    private PeempEmple peempCurp;
    @JoinColumn(name = "CODPROYEC", referencedColumnName = "CODPROYEC", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TeproProyec teproProyec;

    public TeparPartic() {
    }

    public TeparPartic(String codproyec) {
        this.codproyec = codproyec;
    }

    public TeparPartic(String codproyec, int horas) {
        this.codproyec = codproyec;
        this.horas = horas;
    }

    public String getCodproyec() {
        return codproyec;
    }

    public void setCodproyec(String codproyec) {
        this.codproyec = codproyec;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public PeempEmple getPeempCurp() {
        return peempCurp;
    }

    public void setPeempCurp(PeempEmple peempCurp) {
        this.peempCurp = peempCurp;
    }

    public TeproProyec getTeproProyec() {
        return teproProyec;
    }

    public void setTeproProyec(TeproProyec teproProyec) {
        this.teproProyec = teproProyec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codproyec != null ? codproyec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeparPartic)) {
            return false;
        }
        TeparPartic other = (TeparPartic) object;
        if ((this.codproyec == null && other.codproyec != null) || (this.codproyec != null && !this.codproyec.equals(other.codproyec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.TeparPartic[ codproyec=" + codproyec + " ]";
    }
    
}
