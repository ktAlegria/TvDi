/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.Condicionactual;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface CondicionactualFacadeLocal {

    void create(Condicionactual condicionactual);

    void edit(Condicionactual condicionactual);

    void remove(Condicionactual condicionactual);

    Condicionactual find(Object id);

    List<Condicionactual> findAll();

    List<Condicionactual> findRange(int[] range);

    int count();

}
