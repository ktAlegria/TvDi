/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Registro;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class RegistroFacade extends AbstractFacade<Registro> implements RegistroFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroFacade() {
        super(Registro.class);
    }

}
