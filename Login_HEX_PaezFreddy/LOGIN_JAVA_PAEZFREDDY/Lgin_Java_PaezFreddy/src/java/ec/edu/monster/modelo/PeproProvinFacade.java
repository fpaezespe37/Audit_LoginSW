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
public class PeproProvinFacade extends AbstractFacade<PeproProvin> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeproProvinFacade() {
        super(PeproProvin.class);
    }
    
    public List<PeproProvin> obtenerProvinciasPais(String PEPAI_CODIGO){
        List<PeproProvin> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeproProvin p WHERE p.peproProvinPK.pepaiCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, PEPAI_CODIGO);
            lista = query.getResultList();
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
}
