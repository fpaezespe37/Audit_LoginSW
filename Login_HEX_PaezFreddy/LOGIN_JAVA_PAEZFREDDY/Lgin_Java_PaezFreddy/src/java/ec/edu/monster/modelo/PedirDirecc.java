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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "pedir_direcc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedirDirecc.findAll", query = "SELECT p FROM PedirDirecc p"),
    @NamedQuery(name = "PedirDirecc.findByPedirCodigodireccion", query = "SELECT p FROM PedirDirecc p WHERE p.pedirCodigodireccion = :pedirCodigodireccion"),
    @NamedQuery(name = "PedirDirecc.findByPedirCalleprincipal", query = "SELECT p FROM PedirDirecc p WHERE p.pedirCalleprincipal = :pedirCalleprincipal"),
    @NamedQuery(name = "PedirDirecc.findByPedirCallesecundaria", query = "SELECT p FROM PedirDirecc p WHERE p.pedirCallesecundaria = :pedirCallesecundaria")})
public class PedirDirecc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PEDIR_CODIGODIRECCION")
    private String pedirCodigodireccion;
    @Size(max = 50)
    @Column(name = "PEDIR_CALLEPRINCIPAL")
    private String pedirCalleprincipal;
    @Size(max = 50)
    @Column(name = "PEDIR_CALLESECUNDARIA")
    private String pedirCallesecundaria;
    @JoinColumns({
        @JoinColumn(name = "PEPAI_CODIGO", referencedColumnName = "PEPAI_CODIGO"),
        @JoinColumn(name = "PEPRO_CODIGO", referencedColumnName = "PEPRO_CODIGO"),
        @JoinColumn(name = "PECAN_CODIGO", referencedColumnName = "PECAN_CODIGO"),
        @JoinColumn(name = "PEPAR_CODIGO", referencedColumnName = "PEPAR_CODIGO")})
    @ManyToOne(optional = false)
    private PeparParroq peparParroq;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedirCodigodireccion")
    private Collection<PeempEmple> peempEmpleCollection;

    public PedirDirecc() {
    }

    public PedirDirecc(String pedirCodigodireccion) {
        this.pedirCodigodireccion = pedirCodigodireccion;
    }

    public String getPedirCodigodireccion() {
        return pedirCodigodireccion;
    }

    public void setPedirCodigodireccion(String pedirCodigodireccion) {
        this.pedirCodigodireccion = pedirCodigodireccion;
    }

    public String getPedirCalleprincipal() {
        return pedirCalleprincipal;
    }

    public void setPedirCalleprincipal(String pedirCalleprincipal) {
        this.pedirCalleprincipal = pedirCalleprincipal;
    }

    public String getPedirCallesecundaria() {
        return pedirCallesecundaria;
    }

    public void setPedirCallesecundaria(String pedirCallesecundaria) {
        this.pedirCallesecundaria = pedirCallesecundaria;
    }

    public PeparParroq getPeparParroq() {
        return peparParroq;
    }

    public void setPeparParroq(PeparParroq peparParroq) {
        this.peparParroq = peparParroq;
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
        hash += (pedirCodigodireccion != null ? pedirCodigodireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedirDirecc)) {
            return false;
        }
        PedirDirecc other = (PedirDirecc) object;
        if ((this.pedirCodigodireccion == null && other.pedirCodigodireccion != null) || (this.pedirCodigodireccion != null && !this.pedirCodigodireccion.equals(other.pedirCodigodireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.PedirDirecc[ pedirCodigodireccion=" + pedirCodigodireccion + " ]";
    }
    
}
