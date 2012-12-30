/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.TerceraParte;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class TerceraParteFacade extends AbstractFacade<TerceraParte> implements TerceraParteFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TerceraParteFacade() {
        super(TerceraParte.class);
    }

}
