/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Condicionactual;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class CondicionactualFacade extends AbstractFacade<Condicionactual> implements CondicionactualFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CondicionactualFacade() {
        super(Condicionactual.class);
    }

}
