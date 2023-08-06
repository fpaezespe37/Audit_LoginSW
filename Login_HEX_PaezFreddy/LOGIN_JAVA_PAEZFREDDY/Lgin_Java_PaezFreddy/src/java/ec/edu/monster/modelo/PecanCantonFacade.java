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
public class PecanCantonFacade extends AbstractFacade<PecanCanton> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PecanCantonFacade() {
        super(PecanCanton.class);
    }
    
    public List<PecanCanton> obtenerCantonesProvincia(String peproCodigo){
        List<PecanCanton> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PecanCanton p WHERE p.pecanCantonPK.peproCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, peproCodigo);
            lista = query.getResultList();
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
}
