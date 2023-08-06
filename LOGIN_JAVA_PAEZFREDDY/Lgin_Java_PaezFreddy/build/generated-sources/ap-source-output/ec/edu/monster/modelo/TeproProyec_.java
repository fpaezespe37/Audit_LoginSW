package ec.edu.monster.modelo;

import ec.edu.monster.modelo.TedepDepart;
import ec.edu.monster.modelo.TefasFases;
import ec.edu.monster.modelo.TeparPartic;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(TeproProyec.class)
public class TeproProyec_ { 

    public static volatile SingularAttribute<TeproProyec, String> codproyec;
    public static volatile SingularAttribute<TeproProyec, String> nombreproyecto;
    public static volatile SingularAttribute<TeproProyec, TedepDepart> coddepart;
    public static volatile CollectionAttribute<TeproProyec, TefasFases> tefasFasesCollection;
    public static volatile SingularAttribute<TeproProyec, Integer> numproyecto;
    public static volatile SingularAttribute<TeproProyec, TeparPartic> teparPartic;

}