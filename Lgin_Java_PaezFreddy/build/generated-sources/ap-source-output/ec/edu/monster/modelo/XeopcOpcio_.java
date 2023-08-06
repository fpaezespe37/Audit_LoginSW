package ec.edu.monster.modelo;

import ec.edu.monster.modelo.XeopcOpcioPK;
import ec.edu.monster.modelo.XeoxpOpcpe;
import ec.edu.monster.modelo.XesisVentan;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(XeopcOpcio.class)
public class XeopcOpcio_ { 

    public static volatile CollectionAttribute<XeopcOpcio, XeoxpOpcpe> xeoxpOpcpeCollection;
    public static volatile SingularAttribute<XeopcOpcio, XeopcOpcioPK> xeopcOpcioPK;
    public static volatile SingularAttribute<XeopcOpcio, XesisVentan> xesisVentan;
    public static volatile SingularAttribute<XeopcOpcio, String> xeopcDescri;
    public static volatile SingularAttribute<XeopcOpcio, String> xeopcUrl;

}