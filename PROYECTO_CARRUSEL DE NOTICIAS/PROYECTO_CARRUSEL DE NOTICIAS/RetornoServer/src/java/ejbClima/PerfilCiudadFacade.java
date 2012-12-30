/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.PerfilCiudad;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class PerfilCiudadFacade extends AbstractFacade<PerfilCiudad> implements PerfilCiudadFacadeLocal {
    @PersistenceContext(unitName = "RetornoServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilCiudadFacade() {
        super(PerfilCiudad.class);
    }

}
