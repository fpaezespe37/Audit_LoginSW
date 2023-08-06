/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Christian Novoa
 */
@Stateless
public class PefamFamiliFacade extends AbstractFacade<PefamFamili> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PefamFamiliFacade() {
        super(PefamFamili.class);
    }
    
}
