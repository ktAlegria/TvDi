/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbClima;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import model.Perfil;

/**
 *
 * @author GUSTAVO_AL
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

    public String updatePassword(String id, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE perfil p SET p.password = ");
        sql.append("'");
        sql.append(password);
        sql.append("'");
        sql.append(" WHERE p.sec_perfil = '");
        sql.append(id);
        sql.append("'");

        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());
        try {
            String result = String.valueOf(query.executeUpdate());
            return result;
        } catch (Exception e) {
            return null;
        }

    }

    public String valUser(String login, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.sec_perfil FROM perfil p ");
        sql.append("WHERE p.login = '");
        sql.append(login);
        sql.append("'");
        sql.append(" and p.password = '");
        sql.append(password);
        sql.append("'");

        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());
        try {
            String result = String.valueOf(query.getSingleResult());
            return result;
        } catch (Exception e) {
            return "0";
        }

    }

    public Perfil findForId(String id){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.password FROM perfil p ");
        sql.append("WHERE p.sec_perfil = '");
        sql.append(id);
        sql.append("'");
        System.out.println("sql " + sql);
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());

        try {
            String pass = (String) query.getSingleResult();
            Perfil per = new Perfil();
            per.setSecPerfil(Integer.valueOf(id));
            per.setPassword(pass);
            System.out.println("perfil " + per);
            return per;
        } catch (Exception e) {
            System.out.println("excepcion " + e);
            return null;
        }
    }
}
