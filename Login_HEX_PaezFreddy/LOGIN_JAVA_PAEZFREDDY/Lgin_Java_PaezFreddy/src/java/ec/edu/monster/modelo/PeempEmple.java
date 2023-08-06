/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Christian Novoa
 */
@Entity
@Table(name = "peemp_emple")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeempEmple.findAll", query = "SELECT p FROM PeempEmple p"),
    @NamedQuery(name = "PeempEmple.findByPeempCurp", query = "SELECT p FROM PeempEmple p WHERE p.peempCurp = :peempCurp"),
    @NamedQuery(name = "PeempEmple.findByPeempNombre", query = "SELECT p FROM PeempEmple p WHERE p.peempNombre = :peempNombre"),
    @NamedQuery(name = "PeempEmple.findByPeempApellpaterno", query = "SELECT p FROM PeempEmple p WHERE p.peempApellpaterno = :peempApellpaterno"),
    @NamedQuery(name = "PeempEmple.findByPeempApellmaterno", query = "SELECT p FROM PeempEmple p WHERE p.peempApellmaterno = :peempApellmaterno"),
    @NamedQuery(name = "PeempEmple.findByPeempSalario", query = "SELECT p FROM PeempEmple p WHERE p.peempSalario = :peempSalario"),
    @NamedQuery(name = "PeempEmple.findByPeempFecnacimiento", query = "SELECT p FROM PeempEmple p WHERE p.peempFecnacimiento = :peempFecnacimiento"),
    @NamedQuery(name = "PeempEmple.findByPeempCedula", query = "SELECT p FROM PeempEmple p WHERE p.peempCedula = :peempCedula"),
    @NamedQuery(name = "PeempEmple.findByPeempFoto", query = "SELECT p FROM PeempEmple p WHERE p.peempFoto = :peempFoto"),
    @NamedQuery(name = "PeempEmple.findByPeempEmail", query = "SELECT p FROM PeempEmple p WHERE p.peempEmail = :peempEmail"),
    @NamedQuery(name = "PeempEmple.findByPeempTelefono", query = "SELECT p FROM PeempEmple p WHERE p.peempTelefono = :peempTelefono")})
public class PeempEmple implements Serializable {

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
    @Column(name = "PEEMP_NOMBRE")
    private String peempNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "PEEMP_APELLPATERNO")
    private String peempApellpaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "PEEMP_APELLMATERNO")
    private String peempApellmaterno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEEMP_SALARIO")
    private BigDecimal peempSalario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEEMP_FECNACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date peempFecnacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PEEMP_CEDULA")
    private String peempCedula;
    @Size(max = 100)
    @Column(name = "PEEMP_FOTO")
    private String peempFoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PEEMP_EMAIL")
    private String peempEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PEEMP_TELEFONO")
    private String peempTelefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peempCurp")
    private Collection<XeusuUsuar> xeusuUsuarCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peempCurp")
    private Collection<PtdirDirigi> ptdirDirigiCollection;
    @JoinColumn(name = "PEDIR_CODIGODIRECCION", referencedColumnName = "PEDIR_CODIGODIRECCION")
    @ManyToOne(optional = false)
    private PedirDirecc pedirCodigodireccion;
    @OneToMany(mappedBy = "peePeempCurp")
    private Collection<PeempEmple> peempEmpleCollection;
    @JoinColumn(name = "PEE_PEEMP_CURP", referencedColumnName = "PEEMP_CURP")
    @ManyToOne
    private PeempEmple peePeempCurp;
    @JoinColumn(name = "PEESC_CODIGO", referencedColumnName = "PEESC_CODIGO")
    @ManyToOne(optional = false)
    private PeestEstciv peescCodigo;
    @JoinColumn(name = "CODGENERO", referencedColumnName = "CODGENERO")
    @ManyToOne(optional = false)
    private PegenGener codgenero;
    @JoinColumn(name = "CODNACION", referencedColumnName = "CODNACION")
    @ManyToOne(optional = false)
    private PenacNacion codnacion;
    @JoinColumn(name = "PESEX_CODIGO", referencedColumnName = "PESEX_CODIGO")
    @ManyToOne(optional = false)
    private PesexSexo pesexCodigo;
    @JoinColumn(name = "CODDEPART", referencedColumnName = "CODDEPART")
    @ManyToOne(optional = false)
    private TedepDepart coddepart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peempCurp")
    private Collection<TeparPartic> teparParticCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "peempEmple")
    private PefamFamili pefamFamili;

    public PeempEmple() {
    }

    public PeempEmple(String peempCurp) {
        this.peempCurp = peempCurp;
    }

    public PeempEmple(String peempCurp, String peempNombre, String peempApellpaterno, String peempApellmaterno, BigDecimal peempSalario, Date peempFecnacimiento, String peempCedula, String peempEmail, String peempTelefono) {
        this.peempCurp = peempCurp;
        this.peempNombre = peempNombre;
        this.peempApellpaterno = peempApellpaterno;
        this.peempApellmaterno = peempApellmaterno;
        this.peempSalario = peempSalario;
        this.peempFecnacimiento = peempFecnacimiento;
        this.peempCedula = peempCedula;
        this.peempEmail = peempEmail;
        this.peempTelefono = peempTelefono;
    }

    public String getPeempCurp() {
        return peempCurp;
    }

    public void setPeempCurp(String peempCurp) {
        this.peempCurp = peempCurp;
    }

    public String getPeempNombre() {
        return peempNombre;
    }

    public void setPeempNombre(String peempNombre) {
        this.peempNombre = peempNombre;
    }

    public String getPeempApellpaterno() {
        return peempApellpaterno;
    }

    public void setPeempApellpaterno(String peempApellpaterno) {
        this.peempApellpaterno = peempApellpaterno;
    }

    public String getPeempApellmaterno() {
        return peempApellmaterno;
    }

    public void setPeempApellmaterno(String peempApellmaterno) {
        this.peempApellmaterno = peempApellmaterno;
    }

    public BigDecimal getPeempSalario() {
        return peempSalario;
    }

    public void setPeempSalario(BigDecimal peempSalario) {
        this.peempSalario = peempSalario;
    }

    public Date getPeempFecnacimiento() {
        return peempFecnacimiento;
    }

    public void setPeempFecnacimiento(Date peempFecnacimiento) {
        this.peempFecnacimiento = peempFecnacimiento;
    }

    public String getPeempCedula() {
        return peempCedula;
    }

    public void setPeempCedula(String peempCedula) {
        this.peempCedula = peempCedula;
    }

    public String getPeempFoto() {
        return peempFoto;
    }

    public void setPeempFoto(String peempFoto) {
        this.peempFoto = peempFoto;
    }

    public String getPeempEmail() {
        return peempEmail;
    }

    public void setPeempEmail(String peempEmail) {
        this.peempEmail = peempEmail;
    }

    public String getPeempTelefono() {
        return peempTelefono;
    }

    public void setPeempTelefono(String peempTelefono) {
        this.peempTelefono = peempTelefono;
    }

    @XmlTransient
    public Collection<XeusuUsuar> getXeusuUsuarCollection() {
        return xeusuUsuarCollection;
    }

    public void setXeusuUsuarCollection(Collection<XeusuUsuar> xeusuUsuarCollection) {
        this.xeusuUsuarCollection = xeusuUsuarCollection;
    }

    @XmlTransient
    public Collection<PtdirDirigi> getPtdirDirigiCollection() {
        return ptdirDirigiCollection;
    }

    public void setPtdirDirigiCollection(Collection<PtdirDirigi> ptdirDirigiCollection) {
        this.ptdirDirigiCollection = ptdirDirigiCollection;
    }

    public PedirDirecc getPedirCodigodireccion() {
        return pedirCodigodireccion;
    }

    public void setPedirCodigodireccion(PedirDirecc pedirCodigodireccion) {
        this.pedirCodigodireccion = pedirCodigodireccion;
    }

    @XmlTransient
    public Collection<PeempEmple> getPeempEmpleCollection() {
        return peempEmpleCollection;
    }

    public void setPeempEmpleCollection(Collection<PeempEmple> peempEmpleCollection) {
        this.peempEmpleCollection = peempEmpleCollection;
    }

    public PeempEmple getPeePeempCurp() {
        return peePeempCurp;
    }

    public void setPeePeempCurp(PeempEmple peePeempCurp) {
        this.peePeempCurp = peePeempCurp;
    }

    public PeestEstciv getPeescCodigo() {
        return peescCodigo;
    }

    public void setPeescCodigo(PeestEstciv peescCodigo) {
        this.peescCodigo = peescCodigo;
    }

    public PegenGener getCodgenero() {
        return codgenero;
    }

    public void setCodgenero(PegenGener codgenero) {
        this.codgenero = codgenero;
    }

    public PenacNacion getCodnacion() {
        return codnacion;
    }

    public void setCodnacion(PenacNacion codnacion) {
        this.codnacion = codnacion;
    }

    public PesexSexo getPesexCodigo() {
        return pesexCodigo;
    }

    public void setPesexCodigo(PesexSexo pesexCodigo) {
        this.pesexCodigo = pesexCodigo;
    }

    public TedepDepart getCoddepart() {
        return coddepart;
    }

    public void setCoddepart(TedepDepart coddepart) {
        this.coddepart = coddepart;
    }

    @XmlTransient
    public Collection<TeparPartic> getTeparParticCollection() {
        return teparParticCollection;
    }

    public void setTeparParticCollection(Collection<TeparPartic> teparParticCollection) {
        this.teparParticCollection = teparParticCollection;
    }

    public PefamFamili getPefamFamili() {
        return pefamFamili;
    }

    public void setPefamFamili(PefamFamili pefamFamili) {
        this.pefamFamili = pefamFamili;
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
        if (!(object instanceof PeempEmple)) {
            return false;
        }
        PeempEmple other = (PeempEmple) object;
        if ((this.peempCurp == null && other.peempCurp != null) || (this.peempCurp != null && !this.peempCurp.equals(other.peempCurp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return peempCurp;
    }
    
    public String generarContraseña() {
        Random rdn = new Random();
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int longitud = caracteres.length();
        char letra;
        int longitudContrasenia = 16;
        String contraseniaAleatoria = "";
        for (int i = 0; i < longitudContrasenia; i++) {
            letra = caracteres.charAt(rdn.nextInt(longitud));
            contraseniaAleatoria=contraseniaAleatoria+letra;
        }
        
        return contraseniaAleatoria;
    }
    
}
