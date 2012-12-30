/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.Caracteristica;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface CaracteristicaFacadeLocal {

    void create(Caracteristica caracteristica);

    void edit(Caracteristica caracteristica);

    void remove(Caracteristica caracteristica);

    Caracteristica find(Object id);

    List<Caracteristica> findAll();

    List<Caracteristica> findRange(int[] range);

    int count();

}
