/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.Pronostico;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface PronosticoFacadeLocal {

    void create(Pronostico pronostico);

    void edit(Pronostico pronostico);

    void remove(Pronostico pronostico);

    Pronostico find(Object id);

    List<Pronostico> findAll();

    List<Pronostico> findRange(int[] range);

    int count();

}
