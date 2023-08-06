/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Christian Novoa
 */
@Stateless
public class XeoxpOpcpeFacade extends AbstractFacade<XeoxpOpcpe> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeoxpOpcpeFacade() {
        super(XeoxpOpcpe.class);
    }
    
    @Transactional
    public void insertWithQuery(String codigoUsuario, String codigoPerfil, String email) {
        // Date date=new Date();
        java.util.Date fecha = new java.util.Date();
        String[] split=codigoPerfil.split("-");
        System.out.println("El codigo delperfil es: "+codigoPerfil);
        getEntityManager().createNativeQuery("INSERT INTO xeoxp_opcpe (XEOPC_CODIGO, XEPER_CODIGO,XEOXP_OPCPER,XEOXP_FECASI,XEOXP_FECRET) VALUES (?,?,?,?,?)").setParameter(1, codigoUsuario)
                .setParameter(2,split[0]).setParameter(3, "").setParameter(4, fecha).setParameter(5, fecha).executeUpdate();
    }
    
     public List<XeoxpOpcpe> getUsuarioPerfilP(String codPerfil) {
            String[] split=codPerfil.split("-");
        TypedQuery<XeoxpOpcpe> query
                = getEntityManager().createNamedQuery("XeuxpUsupe.findByXeperCodigo", XeoxpOpcpe.class);
        return query.setParameter("xeperCodigo", split[0]).getResultList();
    }
    
     @Transactional
    public void removeAssignation(String id_persona) {
 
        getEntityManager().createNativeQuery("DELETE FROM xeoxp_opcpe WHERE XEOPC_CODIGO like ?").setParameter(1, id_persona)
                .executeUpdate();
    }
    
    public List<XeopcOpcio> findUsuarios(String codusu) {
 
        return getEntityManager().createNativeQuery("SELECT u.* "
                + "FROM xeoxp_opcpe up, xeopc_opcio u "
                + "WHERE up.XEOPC_CODIGO = u.XEOPC_CODIGO AND up.XEPER_CODIGO like ? ", XeopcOpcio.class)
                .setParameter(1, codusu).getResultList();
    }
    
    public List<XeoxpOpcpe> getOpcionesPoPerfil(String codPerfil) {
     
                TypedQuery<XeoxpOpcpe> query
                        = getEntityManager().createNamedQuery("XeoxpOpcpe.findByXeperCodigo", XeoxpOpcpe.class);
                return query.setParameter("xeperCodigo", codPerfil).getResultList();
            
    }
 
    public XeoxpOpcpe getElement(String perfil, String op, String ven) {
        return (XeoxpOpcpe) em.createNativeQuery("select * from xeoxp_opcpe where XEPER_CODIGO like ? AND XEOPC_CODIGO like ? AND XEVEN_CODIGO like ?",
                XeoxpOpcpe.class).setParameter(1, perfil).setParameter(2, op).setParameter(3, ven).getSingleResult();
    }
    public List<XeoxpOpcpe> getElements(String perfil, String op, String ven) {
        return em.createNativeQuery("select * from xeoxp_opcpe where XEPER_CODIGO like ? AND XEOPC_CODIGO like ? AND XEVEN_CODIGO like ?",
                XeoxpOpcpe.class).setParameter(1, perfil).setParameter(2, op).setParameter(3, ven).getResultList();
    }
    
    
    public List<XeoxpOpcpe> getOpciones( ) {
            
            
        TypedQuery<XeoxpOpcpe> query
                = getEntityManager().createNamedQuery("XeoxpOpcpe.findAll", XeoxpOpcpe.class);
        return query.getResultList();
    }
    
    public List<XeoxpOpcpe> getOpPer(String perfil) {
        
        System.out.println(perfil);
        return em.createNativeQuery("select * from xeoxp_opcpe where XEPER_CODIGO like ?", XeoxpOpcpe.class).setParameter(1, perfil).getResultList();
    }
    
     public List<XeoxpOpcpe> getOpcionPerfilP(String codPerfil) {
            
            System.out.println("Elcodigo del perfil es:"+codPerfil);
            
            if(codPerfil!=null){
                String [] s= codPerfil.split("-");
                TypedQuery<XeoxpOpcpe> query
                = getEntityManager().createNamedQuery("XeoxpOpcpe.findByXeperCodigo", XeoxpOpcpe.class);
                return query.setParameter("xeperCodigo",s[0]).getResultList();
            }else{
            
                TypedQuery<XeoxpOpcpe> query
                        = getEntityManager().createNamedQuery("XeoxpOpcpe.findByXeperCodigo", XeoxpOpcpe.class);
                return query.setParameter("xeperCodigo", codPerfil).getResultList();
            }
    }
}

