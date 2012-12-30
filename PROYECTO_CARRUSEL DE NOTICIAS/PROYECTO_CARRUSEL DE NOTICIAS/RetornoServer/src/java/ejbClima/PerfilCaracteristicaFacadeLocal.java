/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.PerfilCaracteristica;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface PerfilCaracteristicaFacadeLocal {

    void create(PerfilCaracteristica perfilCaracteristica);

    void edit(PerfilCaracteristica perfilCaracteristica);

    void remove(PerfilCaracteristica perfilCaracteristica);

    PerfilCaracteristica find(Object id);

    List<PerfilCaracteristica> findAll();

    List<PerfilCaracteristica> findRange(int[] range);

    int count();

}
