package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PecanCanton;
import ec.edu.monster.modelo.PepaiPais;
import ec.edu.monster.modelo.PeproProvinPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PeproProvin.class)
public class PeproProvin_ { 

    public static volatile SingularAttribute<PeproProvin, PeproProvinPK> peproProvinPK;
    public static volatile CollectionAttribute<PeproProvin, PecanCanton> pecanCantonCollection;
    public static volatile SingularAttribute<PeproProvin, String> peproNombre;
    public static volatile SingularAttribute<PeproProvin, PepaiPais> pepaiPais;

}