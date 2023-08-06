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
@Table(name = "pegen_gener")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PegenGener.findAll", query = "SELECT p FROM PegenGener p"),
    @NamedQuery(name = "PegenGener.findByCodgenero", query = "SELECT p FROM PegenGener p WHERE p.codgenero = :codgenero"),
    @NamedQuery(name = "PegenGener.findByDescripcion", query = "SELECT p FROM PegenGener p WHERE p.descripcion = :descripcion")})
public class PegenGener implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODGENERO")
    private Integer codgenero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codgenero")
    private Collection<PeempEmple> peempEmpleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codgenero")
    private Collection<PefamFamili> pefamFamiliCollection;

    public PegenGener() {
    }

    public PegenGener(Integer codgenero) {
        this.codgenero = codgenero;
    }

    public PegenGener(Integer codgenero, String descripcion) {
        this.codgenero = codgenero;
        this.descripcion = descripcion;
    }

    public Integer getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(Integer codgenero) {
        this.codgenero = codgenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<PeempEmple> getPeempEmpleCollection() {
        return peempEmpleCollection;
    }

    public void setPeempEmpleCollection(Collection<PeempEmple> peempEmpleCollection) {
        this.peempEmpleCollection = peempEmpleCollection;
    }

    @XmlTransient
    public Collection<PefamFamili> getPefamFamiliCollection() {
        return pefamFamiliCollection;
    }

    public void setPefamFamiliCollection(Collection<PefamFamili> pefamFamiliCollection) {
        this.pefamFamiliCollection = pefamFamiliCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codgenero != null ? codgenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PegenGener)) {
            return false;
        }
        PegenGener other = (PegenGener) object;
        if ((this.codgenero == null && other.codgenero != null) || (this.codgenero != null && !this.codgenero.equals(other.codgenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
