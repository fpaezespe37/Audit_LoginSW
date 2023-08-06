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
@Table(name = "pesex_sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PesexSexo.findAll", query = "SELECT p FROM PesexSexo p"),
    @NamedQuery(name = "PesexSexo.findByPesexCodigo", query = "SELECT p FROM PesexSexo p WHERE p.pesexCodigo = :pesexCodigo"),
    @NamedQuery(name = "PesexSexo.findByPesexNomb", query = "SELECT p FROM PesexSexo p WHERE p.pesexNomb = :pesexNomb"),
    @NamedQuery(name = "PesexSexo.findByPesexDescri", query = "SELECT p FROM PesexSexo p WHERE p.pesexDescri = :pesexDescri")})
public class PesexSexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PESEX_CODIGO")
    private String pesexCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "PESEX_NOMB")
    private String pesexNomb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PESEX_DESCRI")
    private String pesexDescri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesexCodigo")
    private Collection<PeempEmple> peempEmpleCollection;

    public PesexSexo() {
    }

    public PesexSexo(String pesexCodigo) {
        this.pesexCodigo = pesexCodigo;
    }

    public PesexSexo(String pesexCodigo, String pesexNomb, String pesexDescri) {
        this.pesexCodigo = pesexCodigo;
        this.pesexNomb = pesexNomb;
        this.pesexDescri = pesexDescri;
    }

    public String getPesexCodigo() {
        return pesexCodigo;
    }

    public void setPesexCodigo(String pesexCodigo) {
        this.pesexCodigo = pesexCodigo;
    }

    public String getPesexNomb() {
        return pesexNomb;
    }

    public void setPesexNomb(String pesexNomb) {
        this.pesexNomb = pesexNomb;
    }

    public String getPesexDescri() {
        return pesexDescri;
    }

    public void setPesexDescri(String pesexDescri) {
        this.pesexDescri = pesexDescri;
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
        hash += (pesexCodigo != null ? pesexCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PesexSexo)) {
            return false;
        }
        PesexSexo other = (PesexSexo) object;
        if ((this.pesexCodigo == null && other.pesexCodigo != null) || (this.pesexCodigo != null && !this.pesexCodigo.equals(other.pesexCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pesexNomb;
    }
    
}
