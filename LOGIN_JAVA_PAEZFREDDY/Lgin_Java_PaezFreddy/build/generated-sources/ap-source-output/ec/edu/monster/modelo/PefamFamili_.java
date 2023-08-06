package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.PegenGener;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PefamFamili.class)
public class PefamFamili_ { 

    public static volatile SingularAttribute<PefamFamili, Date> pefamFamfecnacimiento;
    public static volatile SingularAttribute<PefamFamili, PeempEmple> peempEmple;
    public static volatile SingularAttribute<PefamFamili, String> peempCurp;
    public static volatile SingularAttribute<PefamFamili, PegenGener> codgenero;
    public static volatile SingularAttribute<PefamFamili, String> pefamParentesco;
    public static volatile SingularAttribute<PefamFamili, String> pefamFamapellpaterno;
    public static volatile SingularAttribute<PefamFamili, String> pefamFamapellmaterno;
    public static volatile SingularAttribute<PefamFamili, String> pefamFamnombre;

}