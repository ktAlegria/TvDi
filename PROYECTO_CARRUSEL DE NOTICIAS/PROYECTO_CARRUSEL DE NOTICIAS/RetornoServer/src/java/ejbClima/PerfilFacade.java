/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Perfil;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class PerfilFacade extends AbstractFacade<Perfil> implements PerfilFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Perfil.class);
    }


    public int Actualizar(int id){
        EntityManager entityManager = this.getEntityManager();
        Query createQuery = entityManager.createQuery("UPDATE Perfil c SET c.actualizar = 'true' WHERE c.secPerfil = " + "'" + id + "'");
        int executeUpdate = createQuery.executeUpdate();
        return executeUpdate;
    }

    public int noActualizar(int id){
        EntityManager entityManager = em;
        Query createQuery = entityManager.createQuery("UPDATE Perfil c SET c.actualizar = 'false' WHERE c.secPerfil = " + "'" + id + "'");
        int executeUpdate = createQuery.executeUpdate();
        return 0;
    }
}
