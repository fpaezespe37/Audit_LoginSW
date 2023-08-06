package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import ec.edu.monster.modelo.PefamFamili;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PegenGener.class)
public class PegenGener_ { 

    public static volatile SingularAttribute<PegenGener, String> descripcion;
    public static volatile SingularAttribute<PegenGener, Integer> codgenero;
    public static volatile CollectionAttribute<PegenGener, PefamFamili> pefamFamiliCollection;
    public static volatile CollectionAttribute<PegenGener, PeempEmple> peempEmpleCollection;

}