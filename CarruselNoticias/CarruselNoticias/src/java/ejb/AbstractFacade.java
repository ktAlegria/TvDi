/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Carrusel;

/**
 *
 * @author JOHANNA
 */
public abstract class AbstractFacade<T> {

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
                carr.setId(Integer.valueOf(String.valueOf(objects[0])));
                carr.setPosicion(Integer.valueOf(String.valueOf(objects[1])));
                carrusel.add(carr);
            }
            return carrusel;
        } catch (Exception e) {
            System.out.println("exepcion; " + e);
            return null;
        }
    }
}
