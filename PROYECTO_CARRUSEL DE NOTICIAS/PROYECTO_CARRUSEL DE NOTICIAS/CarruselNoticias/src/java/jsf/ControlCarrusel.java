/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import ejb.CarruselFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Carrusel;

/**
 *
 * @author GUSTAVO_AL
 */
@ManagedBean
@SessionScoped
public class ControlCarrusel {
    @EJB
    private ejb.CarruselFacade ejbFacade;
    List<model.Carrusel> listCarrusel =  new ArrayList<Carrusel>();
    Carrusel carruselSelected;
    /** Creates a new instance of ControlCarrusel */
    public ControlCarrusel() {
        try {
            System.out.println("facade " + ejbFacade);
            listCarrusel = ejbFacade.findAll();

        } catch (Exception e) {
            System.out.println("exception " + e);
        }
        carruselSelected = new Carrusel();
    }

    public List<Carrusel> getListCarrusel() {
        return listCarrusel;
    }

    public void setListCarrusel(List<Carrusel> listCarrusel) {
        this.listCarrusel = listCarrusel;
    }

    public Carrusel getCarruselSelected() {
        return carruselSelected;
    }

    public void setCarruselSelected(Carrusel carruselSelected) {
        this.carruselSelected = carruselSelected;
    }

}
