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
public class XesisVentanFacade extends AbstractFacade<XesisVentan> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XesisVentanFacade() {
        super(XesisVentan.class);
    }
    
    public List<XesisVentan> getSistemas(XesisSiste Codigo) {
          
        TypedQuery<XesisVentan> query
                = getEntityManager().createNamedQuery("XesisVentan.findByXevenSiste", XesisVentan.class);
        return query.setParameter("xesisCodigo",Codigo).getResultList();
    }
    
}
