package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PecanCantonPK;
import ec.edu.monster.modelo.PeparParroq;
import ec.edu.monster.modelo.PeproProvin;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PecanCanton.class)
public class PecanCanton_ { 

    public static volatile SingularAttribute<PecanCanton, PecanCantonPK> pecanCantonPK;
    public static volatile SingularAttribute<PecanCanton, PeproProvin> peproProvin;
    public static volatile CollectionAttribute<PecanCanton, PeparParroq> peparParroqCollection;
    public static volatile SingularAttribute<PecanCanton, String> pecanDescri;

}