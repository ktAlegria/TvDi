/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.Administrador;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface AdministradorFacadeLocal {

    void create(Administrador administrador);

    void edit(Administrador administrador);

    void remove(Administrador administrador);

    Administrador find(Object id);

    List<Administrador> findAll();

    List<Administrador> findRange(int[] range);

    int count();

}
