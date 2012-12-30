/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.ServicioWebClima;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class ServicioWebClimaFacade extends AbstractFacade<ServicioWebClima> implements ServicioWebClimaFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicioWebClimaFacade() {
        super(ServicioWebClima.class);
    }

}
