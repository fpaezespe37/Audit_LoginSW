package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PedirDirecc;
import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.PeestEstciv;
import ec.edu.monster.modelo.PefamFamili;
import ec.edu.monster.modelo.PegenGener;
import ec.edu.monster.modelo.PenacNacion;
import ec.edu.monster.modelo.PesexSexo;
import ec.edu.monster.modelo.PtdirDirigi;
import ec.edu.monster.modelo.TedepDepart;
import ec.edu.monster.modelo.TeparPartic;
import ec.edu.monster.modelo.XeusuUsuar;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PeempEmple.class)
public class PeempEmple_ { 

    public static volatile SingularAttribute<PeempEmple, Date> peempFecnacimiento;
    public static volatile SingularAttribute<PeempEmple, PesexSexo> pesexCodigo;
    public static volatile SingularAttribute<PeempEmple, String> peempCurp;
    public static volatile SingularAttribute<PeempEmple, PegenGener> codgenero;
    public static volatile SingularAttribute<PeempEmple, PedirDirecc> pedirCodigodireccion;
    public static volatile SingularAttribute<PeempEmple, String> peempApellpaterno;
    public static volatile SingularAttribute<PeempEmple, PeestEstciv> peescCodigo;
    public static volatile SingularAttribute<PeempEmple, String> peempCedula;
    public static volatile CollectionAttribute<PeempEmple, PtdirDirigi> ptdirDirigiCollection;
    public static volatile SingularAttribute<PeempEmple, PenacNacion> codnacion;
    public static volatile SingularAttribute<PeempEmple, PefamFamili> pefamFamili;
    public static volatile SingularAttribute<PeempEmple, String> peempEmail;
    public static volatile CollectionAttribute<PeempEmple, TeparPartic> teparParticCollection;
    public static volatile SingularAttribute<PeempEmple, PeempEmple> peePeempCurp;
    public static volatile SingularAttribute<PeempEmple, String> peempNombre;
    public static volatile SingularAttribute<PeempEmple, String> peempApellmaterno;
    public static volatile SingularAttribute<PeempEmple, String> peempTelefono;
    public static volatile CollectionAttribute<PeempEmple, XeusuUsuar> xeusuUsuarCollection;
    public static volatile SingularAttribute<PeempEmple, BigDecimal> peempSalario;
    public static volatile SingularAttribute<PeempEmple, TedepDepart> coddepart;
    public static volatile CollectionAttribute<PeempEmple, PeempEmple> peempEmpleCollection;
    public static volatile SingularAttribute<PeempEmple, String> peempFoto;

}