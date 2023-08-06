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
import javax.persistence.OneToOne;
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
@Table(name = "tepro_proyec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeproProyec.findAll", query = "SELECT t FROM TeproProyec t"),
    @NamedQuery(name = "TeproProyec.findByCodproyec", query = "SELECT t FROM TeproProyec t WHERE t.codproyec = :codproyec"),
    @NamedQuery(name = "TeproProyec.findByNumproyecto", query = "SELECT t FROM TeproProyec t WHERE t.numproyecto = :numproyecto"),
    @NamedQuery(name = "TeproProyec.findByNombreproyecto", query = "SELECT t FROM TeproProyec t WHERE t.nombreproyecto = :nombreproyecto")})
public class TeproProyec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODPROYEC")
    private String codproyec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMPROYECTO")
    private int numproyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBREPROYECTO")
    private String nombreproyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codproyec")
    private Collection<TefasFases> tefasFasesCollection;
    @JoinColumn(name = "CODDEPART", referencedColumnName = "CODDEPART")
    @ManyToOne(optional = false)
    private TedepDepart coddepart;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teproProyec")
    private TeparPartic teparPartic;

    public TeproProyec() {
    }

    public TeproProyec(String codproyec) {
        this.codproyec = codproyec;
    }

    public TeproProyec(String codproyec, int numproyecto, String nombreproyecto) {
        this.codproyec = codproyec;
        this.numproyecto = numproyecto;
        this.nombreproyecto = nombreproyecto;
    }

    public String getCodproyec() {
        return codproyec;
    }

    public void setCodproyec(String codproyec) {
        this.codproyec = codproyec;
    }

    public int getNumproyecto() {
        return numproyecto;
    }

    public void setNumproyecto(int numproyecto) {
        this.numproyecto = numproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    @XmlTransient
    public Collection<TefasFases> getTefasFasesCollection() {
        return tefasFasesCollection;
    }

    public void setTefasFasesCollection(Collection<TefasFases> tefasFasesCollection) {
        this.tefasFasesCollection = tefasFasesCollection;
    }

    public TedepDepart getCoddepart() {
        return coddepart;
    }

    public void setCoddepart(TedepDepart coddepart) {
        this.coddepart = coddepart;
    }

    public TeparPartic getTeparPartic() {
        return teparPartic;
    }

    public void setTeparPartic(TeparPartic teparPartic) {
        this.teparPartic = teparPartic;
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
        if (!(object instanceof TeproProyec)) {
            return false;
        }
        TeproProyec other = (TeproProyec) object;
        if ((this.codproyec == null && other.codproyec != null) || (this.codproyec != null && !this.codproyec.equals(other.codproyec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.TeproProyec[ codproyec=" + codproyec + " ]";
    }
    
}
