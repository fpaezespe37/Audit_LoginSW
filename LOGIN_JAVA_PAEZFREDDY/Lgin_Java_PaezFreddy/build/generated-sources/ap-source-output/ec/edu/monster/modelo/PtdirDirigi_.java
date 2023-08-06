package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.TedepDepart;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PtdirDirigi.class)
public class PtdirDirigi_ { 

    public static volatile SingularAttribute<PtdirDirigi, Date> fecha;
    public static volatile SingularAttribute<PtdirDirigi, TedepDepart> tedepDepart;
    public static volatile SingularAttribute<PtdirDirigi, PeempEmple> peempCurp;
    public static volatile SingularAttribute<PtdirDirigi, String> coddepart;

}