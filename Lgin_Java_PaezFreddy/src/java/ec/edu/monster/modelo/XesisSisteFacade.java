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
public class XesisSisteFacade extends AbstractFacade<XesisSiste> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XesisSisteFacade() {
        super(XesisSiste.class);
    }
    
    public List<XesisSiste> getSistemas() {
          
        TypedQuery<XesisSiste> query
                = getEntityManager().createNamedQuery("XesisSiste.findAll", XesisSiste.class);
        return query.getResultList();
    }
}
