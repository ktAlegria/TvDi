/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.TerceraParteServicio;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface TerceraParteServicioFacadeLocal {

    void create(TerceraParteServicio terceraParteServicio);

    void edit(TerceraParteServicio terceraParteServicio);

    void remove(TerceraParteServicio terceraParteServicio);

    TerceraParteServicio find(Object id);

    List<TerceraParteServicio> findAll();

    List<TerceraParteServicio> findRange(int[] range);

    int count();

}
