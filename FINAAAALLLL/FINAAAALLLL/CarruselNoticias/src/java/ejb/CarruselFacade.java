/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Carrusel;
import model.Noticia;

/**
 *
 * @author johanna
 */
@Stateless
public class CarruselFacade extends AbstractFacade<Carrusel> {

    @PersistenceContext(unitName = "CarruselNoticiasPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CarruselFacade() {
        super(Carrusel.class);
    }

    public Integer obtenerNoticia(int idCarrusel) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c.id FROM Carrusel c ");
        sql.append("WHERE c.posicion = '");
        sql.append(idCarrusel);
        sql.append("'");
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery(sql.toString());
        try {
            Integer result = (Integer) query.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
