package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PecanCanton;
import ec.edu.monster.modelo.PedirDirecc;
import ec.edu.monster.modelo.PeparParroqPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PeparParroq.class)
public class PeparParroq_ { 

    public static volatile CollectionAttribute<PeparParroq, PedirDirecc> pedirDireccCollection;
    public static volatile SingularAttribute<PeparParroq, PecanCanton> pecanCanton;
    public static volatile SingularAttribute<PeparParroq, PeparParroqPK> peparParroqPK;
    public static volatile SingularAttribute<PeparParroq, String> peparDescri;

}