/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import ec.edu.monster.modelo.XeusuUsuar;
import ec.edu.monster.modelo.XeuxpUsupe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


/**
 *
 * @author Christian Novoa
 */
@Stateless
public class XeuxpUsupeFacade extends AbstractFacade<XeuxpUsupe> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeuxpUsupeFacade() {
        super(XeuxpUsupe.class);
    }
    
    public XeuxpUsupe buscarPerfil(String idUsuario){
        XeuxpUsupe user = null;
        String consulta="";
        try{
            consulta = "SELECT x FROM XeuxpUsupe x WHERE x.xeuxpUsupePK.xeusuCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idUsuario);
            List<XeuxpUsupe> lista = query.getResultList();
            if(!lista.isEmpty()){
                user=lista.get(0);
                System.out.println(user.getXeusuUsuar().getXeusuPaswd());
            }
            
                
       }catch(Exception e){
            throw e;
        }
        return user;
    }
    
    @Transactional
    public void insertWithQuery(String codigoUsuario, String codigoPerfil, String email) {
        // Date date=new Date();
        java.util.Date fecha = new java.util.Date();
         String[] split=codigoPerfil.split("-");
            System.out.println("El código del perfil es: "+codigoPerfil);
        getEntityManager().createNativeQuery("INSERT INTO xeuxp_usupe (XEUSU_CODIGO, XEPER_CODIGO,XEUXP_CODUSU,XEUXP_FECASI,XEUXP_FECRET) VALUES (?,?,?,?,?)").setParameter(1, codigoUsuario)
                .setParameter(2,split[0]).setParameter(3, "2").setParameter(4, fecha).setParameter(5, fecha).executeUpdate();
    }
    
        public List<XeuxpUsupe> getUsuarioPerfilP(String codPerfil) {
            String[] split=codPerfil.split("-");
        TypedQuery<XeuxpUsupe> query
                = getEntityManager().createNamedQuery("XeuxpUsupe.findByXeperCodigo", XeuxpUsupe.class);
        return query.setParameter("xeperCodigo", split[0]).getResultList();
    }
        
            @Transactional
    public void removeAssignation(String id_persona) {
 
        getEntityManager().createNativeQuery("DELETE FROM xeuxp_usupe WHERE XEUSU_CODIGO like ?").setParameter(1, id_persona)
                .executeUpdate();
    }

    
    public List<XeusuUsuar> findUsuarios(String codusu) {
 
        return getEntityManager().createNativeQuery("SELECT u.* "
                + "FROM xeuxp_usupe up, xeusu_usuar u "
                + "WHERE up.XEUSU_CODIGO = u.XEUSU_CODIGO AND up.XEPER_CODIGO like ? ", XeusuUsuar.class)
                .setParameter(1, codusu).getResultList();
    }
}
