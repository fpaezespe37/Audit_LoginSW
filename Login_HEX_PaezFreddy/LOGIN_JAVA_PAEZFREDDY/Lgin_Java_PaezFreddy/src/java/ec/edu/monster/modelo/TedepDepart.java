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
@Table(name = "tedep_depart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TedepDepart.findAll", query = "SELECT t FROM TedepDepart t"),
    @NamedQuery(name = "TedepDepart.findByCoddepart", query = "SELECT t FROM TedepDepart t WHERE t.coddepart = :coddepart"),
    @NamedQuery(name = "TedepDepart.findByNumdepto", query = "SELECT t FROM TedepDepart t WHERE t.numdepto = :numdepto"),
    @NamedQuery(name = "TedepDepart.findByNombredepto", query = "SELECT t FROM TedepDepart t WHERE t.nombredepto = :nombredepto")})
public class TedepDepart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODDEPART")
    private String coddepart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMDEPTO")
    private int numdepto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBREDEPTO")
    private String nombredepto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tedepDepart")
    private PtdirDirigi ptdirDirigi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coddepart")
    private Collection<TeproProyec> teproProyecCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coddepart")
    private Collection<PeempEmple> peempEmpleCollection;

    public TedepDepart() {
    }

    public TedepDepart(String coddepart) {
        this.coddepart = coddepart;
    }

    public TedepDepart(String coddepart, int numdepto, String nombredepto) {
        this.coddepart = coddepart;
        this.numdepto = numdepto;
        this.nombredepto = nombredepto;
    }

    public String getCoddepart() {
        return coddepart;
    }

    public void setCoddepart(String coddepart) {
        this.coddepart = coddepart;
    }

    public int getNumdepto() {
        return numdepto;
    }

    public void setNumdepto(int numdepto) {
        this.numdepto = numdepto;
    }

    public String getNombredepto() {
        return nombredepto;
    }

    public void setNombredepto(String nombredepto) {
        this.nombredepto = nombredepto;
    }

    public PtdirDirigi getPtdirDirigi() {
        return ptdirDirigi;
    }

    public void setPtdirDirigi(PtdirDirigi ptdirDirigi) {
        this.ptdirDirigi = ptdirDirigi;
    }

    @XmlTransient
    public Collection<TeproProyec> getTeproProyecCollection() {
        return teproProyecCollection;
    }

    public void setTeproProyecCollection(Collection<TeproProyec> teproProyecCollection) {
        this.teproProyecCollection = teproProyecCollection;
    }

    @XmlTransient
    public Collection<PeempEmple> getPeempEmpleCollection() {
        return peempEmpleCollection;
    }

    public void setPeempEmpleCollection(Collection<PeempEmple> peempEmpleCollection) {
        this.peempEmpleCollection = peempEmpleCollection;
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
        if (!(object instanceof TedepDepart)) {
            return false;
        }
        TedepDepart other = (TedepDepart) object;
        if ((this.coddepart == null && other.coddepart != null) || (this.coddepart != null && !this.coddepart.equals(other.coddepart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombredepto;
    }
    
}
