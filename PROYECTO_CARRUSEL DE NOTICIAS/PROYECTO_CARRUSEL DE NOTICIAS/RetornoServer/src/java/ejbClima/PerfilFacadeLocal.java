/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbClima;

import java.util.List;
import javax.ejb.Local;
import model.Perfil;

/**
 *
 * @author GUSTAVO_AL
 */
@Local
public interface PerfilFacadeLocal {

    void create(Perfil perfil);

    void edit(Perfil perfil);

    void remove(Perfil perfil);

    Perfil find(Object id);

    List<Perfil> findAll();

    List<Perfil> findRange(int[] range);

    int count();

    public int Actualizar(int id);

    public int noActualizar(int id);

    public String updatePassword(String id, String password);

    public String valUser(String login, String password);

    public Perfil findForId(String id);

}
