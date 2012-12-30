/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Pronostico;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class PronosticoFacade extends AbstractFacade<Pronostico> implements PronosticoFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PronosticoFacade() {
        super(Pronostico.class);
    }

}
