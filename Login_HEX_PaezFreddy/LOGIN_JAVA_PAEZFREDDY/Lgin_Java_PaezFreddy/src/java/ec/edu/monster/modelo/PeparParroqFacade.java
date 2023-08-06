/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Christian Novoa
 */
@Stateless
public class PeparParroqFacade extends AbstractFacade<PeparParroq> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeparParroqFacade() {
        super(PeparParroq.class);
    }
    
    public List<PeparParroq> obtenerParrquiasCantones(String pecanCodigo){
        List<PeparParroq> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeparParroq p WHERE p.peparParroqPK.pecanCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, pecanCodigo);
            lista = query.getResultList();
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
}
