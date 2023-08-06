package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.PeparParroq;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PedirDirecc.class)
public class PedirDirecc_ { 

    public static volatile SingularAttribute<PedirDirecc, String> pedirCodigodireccion;
    public static volatile SingularAttribute<PedirDirecc, String> pedirCallesecundaria;
    public static volatile SingularAttribute<PedirDirecc, String> pedirCalleprincipal;
    public static volatile CollectionAttribute<PedirDirecc, PeempEmple> peempEmpleCollection;
    public static volatile SingularAttribute<PedirDirecc, PeparParroq> peparParroq;

}