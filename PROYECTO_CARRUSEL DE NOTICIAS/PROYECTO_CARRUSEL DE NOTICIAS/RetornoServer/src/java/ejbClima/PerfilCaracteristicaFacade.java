/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.PerfilCaracteristica;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class PerfilCaracteristicaFacade extends AbstractFacade<PerfilCaracteristica> implements PerfilCaracteristicaFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilCaracteristicaFacade() {
        super(PerfilCaracteristica.class);
    }

}
