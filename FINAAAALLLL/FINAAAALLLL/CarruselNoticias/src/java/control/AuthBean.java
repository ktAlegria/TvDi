/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.UserBean;
import ejb.AdministradorFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Administrador;

/**
 *
 * @author johanna
 */
@ManagedBean(name = "beanSesion")
@RequestScoped
public class AuthBean {

    public final static String USER_KEY = "auth_user";
    private String user;
    private String password;
    private String message;
    @EJB
    AdministradorFacade usuariosFacade;
    private String nomUsuario;
    private String mensajeAlerta;
    private boolean banderaSesion;

    /** Creates a new instance of AuthBean */
    public AuthBean() {
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @param e
     * @throws IOException
     */
    public void doLogin(ActionEvent e) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = "";
        if (isAdmin(user, password)) {
            url = extContext.encodeActionURL(
                    context.getApplication().getViewHandler().getActionURL(context, "/index.xhtml"));
            extContext.getSessionMap().put(USER_KEY, new UserBean(user, "admin"));
            extContext.redirect(url);
            return;

        }
        message = "Usuario y/o clave invalida";
    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isAdmin(String user, String password) {
       /* System.out.println("prueba: " + user +"clave: "+password );
        return user.equals("admin") && password.equals("admin");*/
        
        Integer result = 0;
        try {
            result = usuariosFacade.obtenerPerfil(user, password);
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
        nomUsuario = new String();
        if (result == null) {
            mensajeAlerta = "Verifique sus Datos, Usuario No Registrado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: ", mensajeAlerta));
            return false;
        } else {

            return true;

        }


    }

    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isSuperAdmin(String user, String password) {
        Integer result = 0;
        try {
            //result = usuariosFacade.obtenerPerfilAdministrador(user, password);
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
        nomUsuario = new String();
        if (result == null) {
            mensajeAlerta = "Verifique sus Datos, Usuario No Registrado";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error: ", mensajeAlerta));
            return false;
        } else {

            return true;

        }

    }

    /**
     * 
     * @return
     */
    public String getLoggedUserName() throws IOException {
        try {
            String test = ((UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY)).toString();
            System.out.println("test " + test);
            if(test != null){
                return test;
            }else{
                FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            extContext.getSessionMap().remove(USER_KEY);
            String url = extContext.encodeActionURL(
                    context.getApplication().getViewHandler().getActionURL(context, "/Restringido.xhtml"));
            extContext.redirect(url);
            return null;
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            extContext.getSessionMap().remove(USER_KEY);
            String url = extContext.encodeActionURL(
                    context.getApplication().getViewHandler().getActionURL(context, "/Restringido.xhtml"));
            extContext.redirect(url);
            return null;
        }
    }

    /**
     * 
     * @throws IOException
     */
    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
        String url = extContext.encodeActionURL(
                context.getApplication().getViewHandler().getActionURL(context, "/InicioSesion.xhtml"));
        extContext.redirect(url);
        session.removeAttribute(USER_KEY);
        session.invalidate();
    }
}
