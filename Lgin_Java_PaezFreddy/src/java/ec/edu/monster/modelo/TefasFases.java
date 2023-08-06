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
@Table(name = "tefas_fases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TefasFases.findAll", query = "SELECT t FROM TefasFases t"),
    @NamedQuery(name = "TefasFases.findByCodfase", query = "SELECT t FROM TefasFases t WHERE t.codfase = :codfase"),
    @NamedQuery(name = "TefasFases.findByNumfase", query = "SELECT t FROM TefasFases t WHERE t.numfase = :numfase"),
    @NamedQuery(name = "TefasFases.findByDuracion", query = "SELECT t FROM TefasFases t WHERE t.duracion = :duracion"),
    @NamedQuery(name = "TefasFases.findByFechainicio", query = "SELECT t FROM TefasFases t WHERE t.fechainicio = :fechainicio"),
    @NamedQuery(name = "TefasFases.findByPresupuesto", query = "SELECT t FROM TefasFases t WHERE t.presupuesto = :presupuesto"),
    @NamedQuery(name = "TefasFases.findByObjetivo", query = "SELECT t FROM TefasFases t WHERE t.objetivo = :objetivo"),
    @NamedQuery(name = "TefasFases.findByAlcance", query = "SELECT t FROM TefasFases t WHERE t.alcance = :alcance")})
public class TefasFases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CODFASE")
    private String codfase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMFASE")
    private int numfase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DURACION")
    private int duracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRESUPUESTO")
    private int presupuesto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBJETIVO")
    private String objetivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ALCANCE")
    private String alcance;
    @JoinColumn(name = "CODPROYEC", referencedColumnName = "CODPROYEC")
    @ManyToOne(optional = false)
    private TeproProyec codproyec;

    public TefasFases() {
    }

    public TefasFases(String codfase) {
        this.codfase = codfase;
    }

    public TefasFases(String codfase, int numfase, int duracion, Date fechainicio, int presupuesto, String objetivo, String alcance) {
        this.codfase = codfase;
        this.numfase = numfase;
        this.duracion = duracion;
        this.fechainicio = fechainicio;
        this.presupuesto = presupuesto;
        this.objetivo = objetivo;
        this.alcance = alcance;
    }

    public String getCodfase() {
        return codfase;
    }

    public void setCodfase(String codfase) {
        this.codfase = codfase;
    }

    public int getNumfase() {
        return numfase;
    }

    public void setNumfase(int numfase) {
        this.numfase = numfase;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public TeproProyec getCodproyec() {
        return codproyec;
    }

    public void setCodproyec(TeproProyec codproyec) {
        this.codproyec = codproyec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfase != null ? codfase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TefasFases)) {
            return false;
        }
        TefasFases other = (TefasFases) object;
        if ((this.codfase == null && other.codfase != null) || (this.codfase != null && !this.codfase.equals(other.codfase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.monster.modelo.TefasFases[ codfase=" + codfase + " ]";
    }
    
}
