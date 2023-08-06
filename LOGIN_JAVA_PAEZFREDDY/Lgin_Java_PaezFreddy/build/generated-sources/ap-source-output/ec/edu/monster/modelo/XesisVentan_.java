package ec.edu.monster.modelo;

import ec.edu.monster.modelo.XeopcOpcio;
import ec.edu.monster.modelo.XesisSiste;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-07-27T23:07:39")
@StaticMetamodel(XesisVentan.class)
public class XesisVentan_ { 

    public static volatile SingularAttribute<XesisVentan, String> xevenDescri;
    public static volatile CollectionAttribute<XesisVentan, XeopcOpcio> xeopcOpcioCollection;
    public static volatile SingularAttribute<XesisVentan, String> xevenCodigo;
    public static volatile SingularAttribute<XesisVentan, XesisSiste> xesisCodigo;
    public static volatile SingularAttribute<XesisVentan, String> xevenMensaj;

}