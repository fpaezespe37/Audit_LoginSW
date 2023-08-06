package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.PtdirDirigi;
import ec.edu.monster.modelo.TeproProyec;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(TedepDepart.class)
public class TedepDepart_ { 

    public static volatile SingularAttribute<TedepDepart, String> nombredepto;
    public static volatile CollectionAttribute<TedepDepart, TeproProyec> teproProyecCollection;
    public static volatile SingularAttribute<TedepDepart, String> coddepart;
    public static volatile SingularAttribute<TedepDepart, PtdirDirigi> ptdirDirigi;
    public static volatile CollectionAttribute<TedepDepart, PeempEmple> peempEmpleCollection;
    public static volatile SingularAttribute<TedepDepart, Integer> numdepto;

}