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

@Stateless
public class XeusuUsuarFacade extends AbstractFacade<XeusuUsuar> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XeusuUsuarFacade() {
        super(XeusuUsuar.class);
    }

    public XeusuUsuar acceder(XeusuUsuar u) {
        XeusuUsuar user = null;
        String consulta = "";
        try {
            consulta = "SELECT x FROM XeusuUsuar x,PeempEmple p WHERE p.peempCurp = x.peempCurp.peempCurp AND p.peempEmail = ?1 AND x.xeusuPaswd = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, u.getPeempEmple().getPeempEmail());
            query.setParameter(2, u.getXeusuPaswd());
            List<XeusuUsuar> lista = query.getResultList();
            if (!lista.isEmpty()) {
                user = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    public XeusuUsuar accederMail(XeusuUsuar u) {
        XeusuUsuar user = null;
        String consulta = "";
        try {
            consulta = "SELECT x FROM XeusuUsuar x,PeempEmple p WHERE p.peempCurp = x.peempCurp.peempCurp AND p.peempEmail = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, u.getPeempEmple().getPeempEmail());
            List<XeusuUsuar> lista = query.getResultList();
            if (!lista.isEmpty()) {
                user = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    public int crearCodigo() {
        String consulta = "";
        int maximoCodigo = 0;
        try {
            consulta = "SELECT MAX(x.xeusuCodigo) FROM XeusuUsuar x";
            Query query = em.createQuery(consulta);
            Object result = query.getSingleResult();
            if (result != null) {
                maximoCodigo = Integer.parseInt(result.toString())+1;
            }
        } catch (Exception e) {
            throw e;
        }
        return maximoCodigo;
    }

    public List<XeusuUsuar> getUsuariosNotRelated() {
        return getEntityManager().createNativeQuery("SELECT * FROM xeusu_usuar x WHERE x.XEUSU_CODIGO NOT IN (SELECT up.XEUSU_CODIGO from xeuxp_usupe up);", XeusuUsuar.class)
                .getResultList();

    }

}
