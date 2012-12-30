/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Noticia;

/**
 *
 * @author GUSTAVO_AL
 */
@Stateless
public class NoticiaFacade extends AbstractFacade<Noticia> {
    @PersistenceContext(unitName = "CarruselNoticiasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public NoticiaFacade() {
        super(Noticia.class);
    }

}
