/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import java.util.Date;
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
public class PeempEmpleFacade extends AbstractFacade<PeempEmple> {

    @PersistenceContext(unitName = "LOGIN_JAVA_GRUPO6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeempEmpleFacade() {
        super(PeempEmple.class);
    }
    
    public List<PeempEmple> obtenerEmpleados(){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
    public String crearCodigo(){
        String consulta = "";
        int maximoCodigo = 0;
        try {
            consulta = "SELECT MAX(p.peempCurp) FROM PeempEmple p";
            Query query = em.createQuery(consulta);
            Object result = query.getSingleResult();
            if (result != null) {
                maximoCodigo = Integer.parseInt(result.toString())+1;
            }
        } catch (Exception e) {
            throw e;
        }
        return maximoCodigo+"";
    }
    
    public PedirDirecc consultarDireccion(String cod){
        PedirDirecc pedir = null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PedirDirecc p WHERE p.pedirCodigodireccion = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, cod);
            query.setMaxResults(1);
            pedir = (PedirDirecc) query.getSingleResult();
       }catch(Exception e){
            throw e;
        }
        return pedir;
    }
    
    public List<PeempEmple> obtenerEmpleadosGeneros(String idGenero){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pesexCodigo.pesexCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idGenero);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    public List<PeempEmple> obtenerEmpleadosPorNombre(String nombre){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pesexCodigo.pesexCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nombre);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
    public List<PeempEmple> obtenerEmpleadosDepartamentos(String tedepCodigo){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.coddepart.coddepart = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, tedepCodigo);
            lista = query.getResultList();
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
    public List<PeempEmple> obtenerEmpleadosFechas(Date fecha){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.peempFecnacimiento >= ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, fecha);
            lista = query.getResultList();
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
    public List<PeempEmple> obtenerEmpleadosPais(String codPais){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pedirCodigodireccion.peparParroq.peparParroqPK.pepaiCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codPais);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    public List<PeempEmple> obtenerEmpleadosProvin(String codProvin){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pedirCodigodireccion.peparParroq.peparParroqPK.peproCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codProvin);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    
    public List<PeempEmple> obtenerEmpleadosCanton(String codCanton){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pedirCodigodireccion.peparParroq.peparParroqPK.pecanCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codCanton);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
    public List<PeempEmple> obtenerEmpleadosParroq(String codParr){
        List<PeempEmple> lista= null;
        String consulta="";
        try{
            consulta ="SELECT p FROM PeempEmple p WHERE p.pedirCodigodireccion.peparParroq.peparParroqPK.peparCodigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codParr);
            lista = query.getResultList();
            
       }catch(Exception e){
            throw e;
        }
        return lista;
    }
}
