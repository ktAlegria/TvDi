/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Carrusel;

/**
 *
 * @author johanna
 */
public abstract class AbstractFacade<T> {

    @EJB
    NoticiaFacade ejbNoticia;

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Carrusel> carruselOrderByPosition() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM carrusel c ORDER BY posicion");
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());
        List<Carrusel> carrusel = new ArrayList<Carrusel>();
        Carrusel carr;
        try {
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                carr = new Carrusel();
                carr.setIdcarrusel(Integer.valueOf(String.valueOf(objects[0])));
                carr.setPosicion(Integer.valueOf(String.valueOf(objects[1])));
                carr.setNoticia(ejbNoticia.find(objects[2]));
                carr.setBrvideo(String.valueOf(objects[3]));
                carr.setBraudio(String.valueOf(objects[4]));
                carr.setNombrevideo(String.valueOf(objects[5]));
                carr.setNombreaudio(String.valueOf(objects[6]));
                carrusel.add(carr);
            }
            return carrusel;
        } catch (Exception e) {
            System.out.println("exepcion; " + e);
            return null;
        }
    }

    public Integer obtenerPerfil(String login, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("select p.idAdministrador from administrador p ");
        sql.append("where p.usuario = '");
        sql.append(login);
        sql.append("'");
        sql.append(" and p.claveAdministrador = '");
        sql.append(password);
        sql.append("'");
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());
        try {
            Integer result = (Integer) query.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
