/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.TerceraParte;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface TerceraParteFacadeLocal {

    void create(TerceraParte terceraParte);

    void edit(TerceraParte terceraParte);

    void remove(TerceraParte terceraParte);

    TerceraParte find(Object id);

    List<TerceraParte> findAll();

    List<TerceraParte> findRange(int[] range);

    int count();

}
