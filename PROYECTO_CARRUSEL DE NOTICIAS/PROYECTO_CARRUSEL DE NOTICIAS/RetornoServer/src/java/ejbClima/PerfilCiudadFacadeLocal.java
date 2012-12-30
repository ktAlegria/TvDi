/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.PerfilCiudad;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface PerfilCiudadFacadeLocal {

    void create(PerfilCiudad perfilCiudad);

    void edit(PerfilCiudad perfilCiudad);

    void remove(PerfilCiudad perfilCiudad);

    PerfilCiudad find(Object id);

    List<PerfilCiudad> findAll();

    List<PerfilCiudad> findRange(int[] range);

    int count();

}
