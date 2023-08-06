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

/**
 *
 * @author Christian Novoa
 */
@Stateless
public class XeopcOpcioFacade extends AbstractFacade<XeopcOpcio> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeopcOpcioFacade() {
        super(XeopcOpcio.class);
    }
    
    public List<XeopcOpcio> getOpciones(String Codigo) {
          
        TypedQuery<XeopcOpcio> query
                = getEntityManager().createNamedQuery("XeopcOpcio.findByXevenCodigo", XeopcOpcio.class);
        return query.setParameter("xevenCodigo",Codigo).getResultList();
    }
    
    public List<XeopcOpcio> getOpcionesTotal() {
          
        TypedQuery<XeopcOpcio> query
                = getEntityManager().createNamedQuery("XeopcOpcio.findAll", XeopcOpcio.class);
        return query.getResultList();
    }
    
}
