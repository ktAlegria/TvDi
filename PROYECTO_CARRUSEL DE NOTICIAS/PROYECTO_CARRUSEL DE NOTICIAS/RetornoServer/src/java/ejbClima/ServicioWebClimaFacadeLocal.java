/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.ServicioWebClima;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface ServicioWebClimaFacadeLocal {

    void create(ServicioWebClima servicioWebClima);

    void edit(ServicioWebClima servicioWebClima);

    void remove(ServicioWebClima servicioWebClima);

    ServicioWebClima find(Object id);

    List<ServicioWebClima> findAll();

    List<ServicioWebClima> findRange(int[] range);

    int count();

}
