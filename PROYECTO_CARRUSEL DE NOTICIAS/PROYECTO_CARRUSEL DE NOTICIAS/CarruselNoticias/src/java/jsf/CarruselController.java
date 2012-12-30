package jsf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carrusel;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import ejb.CarruselFacade;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.dialog.Dialog;
import org.primefaces.context.RequestContext;

@ManagedBean (name="carruselController")
@SessionScoped
public class CarruselController {

    private Carrusel current;
    private DataModel items = null;
    @EJB private ejb.CarruselFacade ejbFacade;
    @EJB private ejb.NoticiaFacade ejbFacadeN;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    List<model.Carrusel> listCarrusel =  new ArrayList<Carrusel>();
    Carrusel carruselSelected;

    public CarruselController() {
    }

    public void removeNoticia(ActionEvent ae){
        int posicion = carruselSelected.getPosicion();
        int count = ejbFacade.count();
        if(count > posicion){
            List<Carrusel> findAll = ejbFacade.findAll();
            for (Carrusel carrusel : findAll) {
                if(carrusel.getPosicion() > posicion){
                    carrusel.setPosicion(carrusel.getPosicion() - 1);
                    ejbFacade.edit(carrusel);
                }
            }
        }
        ejbFacade.remove(carruselSelected);
    }

    public String updatePosition(ActionEvent aEvent){
        System.out.println("entra a cambiar posicion");
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Carrusel carruselUpdate = ejbFacade.find(carruselSelected.getId());
        if(carruselUpdate.getPosicion() != carruselSelected.getPosicion()){
            int count = ejbFacade.count();
            if(carruselSelected.getPosicion() > count || carruselSelected.getPosicion() < 1){
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Posicion Incorrecta", "La posicion indicada esta fuera del carrusel");
            }else if (carruselSelected.getPosicion() > carruselUpdate.getPosicion()){
                for (Carrusel carrusel : listCarrusel) {
                    if(carrusel.getPosicion() <= carruselSelected.getPosicion() & carrusel.getPosicion() > carruselUpdate.getPosicion()){
                        carrusel.setPosicion(carrusel.getPosicion() - 1);
                        ejbFacade.edit(carrusel);
                    }
                }
                ejbFacade.edit(carruselSelected);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BIEN HECHO", "se movio hacia arriba en el carrusel");
            }else if (carruselSelected.getPosicion() < carruselUpdate.getPosicion()){
                for (Carrusel carrusel : listCarrusel) {
                    if(carrusel.getPosicion() >= carruselSelected.getPosicion() & carrusel.getPosicion() < carruselUpdate.getPosicion()){
                        carrusel.setPosicion(carrusel.getPosicion() + 1);
                        ejbFacade.edit(carrusel);
                    }
                }
                ejbFacade.edit(carruselSelected);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "BIEN HECHO", "Se movio hacia abajo en el carrusel");
            }

        }else{
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "No cambio posicion", "Escriba una posicion diferente");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "reload";
    }

    public Carrusel getCarruselSelected() {
        return carruselSelected;
    }

    public void setCarruselSelected(Carrusel carruselSelected) {
        this.carruselSelected = carruselSelected;
    }

    public List<Carrusel> getListCarrusel() {
        listCarrusel = ejbFacade.carruselOrderByPosition();
        for (Carrusel carrusel : listCarrusel) {
            carrusel.setNoticia(ejbFacadeN.find(carrusel.getId()));
        }
        return listCarrusel;
    }

    public void setListCarrusel(List<Carrusel> listCarrusel) {
        this.listCarrusel = listCarrusel;
    }

    public Carrusel getSelected() {
        if (current == null) {
            current = new Carrusel();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CarruselFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem()+getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Carrusel)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Carrusel();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarruselCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Carrusel)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarruselUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Carrusel)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarruselDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count-1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex+1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass=Carrusel.class)
    public static class CarruselControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarruselController controller = (CarruselController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carruselController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Carrusel) {
                Carrusel o = (Carrusel) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+CarruselController.class.getName());
            }
        }

    }

}
