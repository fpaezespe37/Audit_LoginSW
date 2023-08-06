package ec.edu.monster.modelo;

import ec.edu.monster.modelo.PeempEmple;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(PenacNacion.class)
public class PenacNacion_ { 

    public static volatile SingularAttribute<PenacNacion, String> codnacion;
    public static volatile SingularAttribute<PenacNacion, String> descrinac;
    public static volatile CollectionAttribute<PenacNacion, PeempEmple> peempEmpleCollection;

}