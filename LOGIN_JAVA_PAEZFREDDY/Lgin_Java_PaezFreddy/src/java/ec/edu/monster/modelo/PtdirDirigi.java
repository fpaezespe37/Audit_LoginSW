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
@Table(name = "ptdir_dirigi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtdirDirigi.findAll", query = "SELECT p FROM PtdirDirigi p"),
    @NamedQuery(name = "PtdirDirigi.findByCoddepart", query = "SELECT p FROM PtdirDirigi p WHERE p.coddepart = :coddepart"),
    @NamedQuery(name = "PtdirDirigi.findByFecha", query = "SELECT p FROM PtdirDirigi p WHERE p.fecha = :fecha")})
public class PtdirDirigi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODDEPART")
    private String coddepart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "PEEMP_CURP", referencedColumnName = "PEEMP_CURP")
    @ManyToOne(optional = false)
    private PeempEmple peempCurp;
    @JoinColumn(name = "CODDEPART", referencedColumnName = "CODDEPART", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TedepDepart tedepDepart;

    public PtdirDirigi() {
    }

    public PtdirDirigi(String coddepart) {
        this.coddepart = coddepart;
    }

    public PtdirDirigi(String coddepart, Date fecha) {
        this.coddepart = coddepart;
        this.fecha = fecha;
    }

    public String getCoddepart() {
        return coddepart;
    }

    public void setCoddepart(String coddepart) {
        this.coddepart = coddepart;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public PeempEmple getPeempCurp() {
        return peempCurp;
    }

    public void setPeempCurp(PeempEmple peempCurp) {
        this.peempCurp = peempCurp;
    }

    public TedepDepart getTedepDepart() {
        return tedepDepart;
    }

    public void setTedepDepart(TedepDepart tedepDepart) {
        this.tedepDepart = tedepDepart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddepart != null ? coddepart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtdirDirigi)) {
            return false;
        }
        PtdirDirigi other = (PtdirDirigi) object;
        if ((this.coddepart == null && other.coddepart != null) || (this.coddepart != null && !this.coddepart.equals(other.coddepart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PtdirDirigi[ coddepart=" + coddepart + " ]";
    }
    
}
