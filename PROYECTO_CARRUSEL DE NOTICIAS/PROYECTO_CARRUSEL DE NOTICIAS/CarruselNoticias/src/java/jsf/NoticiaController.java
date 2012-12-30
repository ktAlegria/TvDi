package jsf;

import model.Noticia;
import jsf.util.JsfUtil;
import jsf.util.PaginationHelper;
import ejb.NoticiaFacade;
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
import model.Carrusel;

@ManagedBean (name="noticiaController")
@SessionScoped
public class NoticiaController {

    private Noticia current;
    private DataModel items = null;
    @EJB private ejb.NoticiaFacade ejbFacade;
    @EJB private ejb.CarruselFacade ejbFacadeC;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private int count;
    private Noticia selectNoticia;

    public NoticiaController() {
    }

    public Noticia getSelectNoticia() {
        return selectNoticia;
    }

    public void setSelectNoticia(Noticia selectNoticia) {
        this.selectNoticia = selectNoticia;
    }

     public int getCount() {
        count = ejbFacadeC.count() + 1;
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Noticia getSelected() {
        if (current == null) {
            current = new Noticia();
            selectedItemIndex = -1;
        }
        return current;
    }

    private NoticiaFacade getFacade() {
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
        current = (Noticia)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Noticia();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NoticiaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Noticia)getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }


    public void loadNews(ActionEvent aEv){
        FacesMessage msg = null;
        if(count < 1 || count > ejbFacadeC.count() + 1){
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Posicion Incorrecta", "La posicion indicada esta fuera del carrusel");
        }else if(count == ejbFacadeC.count() + 1){
            Carrusel carrusel = new Carrusel();
            carrusel.setNoticia(selectNoticia);
            carrusel.setPosicion(count);
            carrusel.setId(selectNoticia.getId());
            ejbFacadeC.create(carrusel);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Posicion Correcta", "Cargada Satisfactoriamente");
        }else{
            List<Carrusel> carrusel = ejbFacadeC.findAll();
            for (Carrusel carrusel1 : carrusel) {
                if(carrusel1.getPosicion() >= count){
                    carrusel1.setPosicion(carrusel1.getPosicion() + 1);
                    ejbFacadeC.edit(carrusel1);
                }
            }
            Carrusel carrusel2 = new Carrusel();
            carrusel2.setNoticia(selectNoticia);
            carrusel2.setPosicion(count);
            carrusel2.setId(selectNoticia.getId());
            ejbFacadeC.create(carrusel2);
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Posicion Correcta", "Cargada Satisfactoriamente");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NoticiaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Noticia)getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NoticiaDeleted"));
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

    @FacesConverter(forClass=Noticia.class)
    public static class NoticiaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoticiaController controller = (NoticiaController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noticiaController");
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
            if (object instanceof Noticia) {
                Noticia o = (Noticia) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: "+NoticiaController.class.getName());
            }
        }

    }

}
