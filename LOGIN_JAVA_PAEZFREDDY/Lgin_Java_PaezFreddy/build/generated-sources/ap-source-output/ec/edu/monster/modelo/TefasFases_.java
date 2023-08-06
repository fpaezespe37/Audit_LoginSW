package ec.edu.monster.modelo;

import ec.edu.monster.modelo.TeproProyec;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(TefasFases.class)
public class TefasFases_ { 

    public static volatile SingularAttribute<TefasFases, Date> fechainicio;
    public static volatile SingularAttribute<TefasFases, String> objetivo;
    public static volatile SingularAttribute<TefasFases, TeproProyec> codproyec;
    public static volatile SingularAttribute<TefasFases, Integer> presupuesto;
    public static volatile SingularAttribute<TefasFases, String> codfase;
    public static volatile SingularAttribute<TefasFases, Integer> numfase;
    public static volatile SingularAttribute<TefasFases, Integer> duracion;
    public static volatile SingularAttribute<TefasFases, String> alcance;

}