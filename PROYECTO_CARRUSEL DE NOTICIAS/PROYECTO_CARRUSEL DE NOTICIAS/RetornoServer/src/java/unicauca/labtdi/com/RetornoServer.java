/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unicauca.labtdi.com;

import ejbClima.AccesoBD;
import ejbClima.AccionFacadeLocal;
import ejbClima.CaracteristicaFacadeLocal;
import ejbClima.CondicionactualFacadeLocal;
import ejbClima.PerfilCaracteristicaFacadeLocal;
import ejbClima.PerfilCiudadFacadeLocal;
import ejbClima.PerfilFacadeLocal;
import ejbClima.PronosticoFacadeLocal;
import ejbClima.RegistroFacadeLocal;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lectorclima.InformacionClimatica;
import model.Ciudad;
import model.Condicionactual;
import model.Perfil;
import model.Pronostico;
import model.Registro;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "RetornoServer", urlPatterns = {"/RetornoServer"})
public class RetornoServer extends HttpServlet {

    @EJB
    PerfilFacadeLocal perfilEJB;
    @EJB
    CondicionactualFacadeLocal condicionActualEJB;
    @EJB
    PronosticoFacadeLocal pronosticoEJB;
    @EJB
    PerfilCiudadFacadeLocal ejbPerfiCiudad;
    @EJB
    PerfilCaracteristicaFacadeLocal ejbPerfiCaracteristica;
    @EJB
    PronosticoFacadeLocal ejbPronostico;
    @EJB
    CaracteristicaFacadeLocal ejbCaracteristica;
    @EJB
    RegistroFacadeLocal ejbRegistro;
    @EJB
    AccionFacadeLocal ejbAccion;
    Perfil perfil = new Perfil();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static final String FUNC_FECHA = "fecha";
    public static final String FUNC_IMAGEN = "imagen";
    public static final String ACTUALIZAR_INFO = "act";
    public static final String VALIDAR_USUARIO = "val";
    public static final String INFORMACION_CLIMA = "info";
    public static final String UPDATE_PERFIL = "perfil";
    public static final String CHANGE_PASS = "changePass";
    public static final String CERRAR_SESION = "cerrar";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String parametro = request.getParameter("func");
            System.out.println("entra peticion de: " + parametro);
            if (parametro.equalsIgnoreCase(FUNC_FECHA)) {

                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeLong(System.currentTimeMillis());
                output.close();
            } else if (parametro.equalsIgnoreCase(FUNC_IMAGEN)) {
                String urlImage = request.getParameter("url");

                //prueba de obtener imagen
                String destinationFile = "image.gif";

                URL url = new URL(urlImage);
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destinationFile);
                byte[] b = new byte[2048];
                int length;

                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                os.close();



                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                FileInputStream fileio = new FileInputStream(destinationFile);
                int data = 0;
                List<Integer> bytes = new ArrayList<Integer>();
                while ((data = fileio.read()) != -1) {
                    bytes.add(new Integer(data));
                }
                if (bytes != null && !bytes.isEmpty()) {
                    output.writeInt(bytes.size());
                    for (Integer valor : bytes) {
                        output.writeByte(valor);
                    }
                    output.write(data);
                } else {
                    output.writeInt(0);
                }
                fileio.close();
                output.close();
            } else if (parametro.equalsIgnoreCase(ACTUALIZAR_INFO)) {
                perfil = new Perfil();
                String idPerfil = request.getParameter("id");
                perfil = perfilEJB.find(Integer.valueOf(idPerfil));
                perfilEJB.Actualizar(perfil.getSecPerfil());
                System.out.println("perfil modificado");
                perfil = perfilEJB.find(Integer.valueOf(idPerfil));

                //aqui va la logica de actualizar la informacion del clima
                InformacionClimatica info = new InformacionClimatica();
                System.out.println("login perfil: " + perfil.getLogin());
                info.actualizarInfoClima(perfil, perfilEJB, condicionActualEJB, pronosticoEJB, ejbPerfiCiudad, ejbPerfiCaracteristica);

                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeChars("perfil:" + idPerfil + "actualizado");
                output.close();
            } else if (parametro.equalsIgnoreCase(VALIDAR_USUARIO)) {
                perfil = new Perfil();
                String login = request.getParameter("login");
                String password = request.getParameter("pass");
                AccesoBD access = new AccesoBD();
                int validaUsuario = Integer.parseInt(perfilEJB.valUser(login, password));
                System.out.println("user id " + validaUsuario);
                if (validaUsuario != 0) {
                    Date date = new Date();
                    perfil = perfilEJB.find(validaUsuario);
                    Registro registro = new Registro();
                    registro.setAccion(ejbAccion.find(1));
                    registro.setPerfil(perfil);
                    registro.setFecha(date);
                    registro.setHora(date);
                    registro.setInformacionAdicional("el usuario " + login + " inicio sesion");
                    ejbRegistro.create(registro);
                }
                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeByte(validaUsuario);
                output.close();
            } else if (parametro.equalsIgnoreCase(INFORMACION_CLIMA)) {
                String id = request.getParameter("id");
                AccesoBD access = new AccesoBD();
                //consulto las ciudades del perfil
                Perfil per = access.perfilById(Integer.valueOf(id), perfilEJB);
                List<Ciudad> citys = access.consultarCiudadesPerfil(per, ejbPerfiCiudad);
                List<Pronostico> pronosticos = new ArrayList<Pronostico>();
                System.out.println("ciudades: " + citys);
                int size = citys.size();
                int numeroPronosticos = access.getNumeroPronosticos(ejbPerfiCaracteristica, per);



                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeUTF(String.valueOf(size));
                output.writeUTF(String.valueOf(numeroPronosticos));
                String datos = "";
                String datos2 = "";

                for (Ciudad ciudad : citys) {

                    Condicionactual condicionActual = access.getCondicionActual(perfilEJB, condicionActualEJB, ciudad);
                    pronosticos = access.consultarPronosticosPerfil(condicionActual, ejbPronostico);
                    System.out.println("user id obtener currentCondi " + id);

                    if (condicionActual.getNombrecondicion() == null) {
                        condicionActual.setNombrecondicion("null");
                    } else if (condicionActual.getNombrecondicion().equals("")) {
                        condicionActual.setNombrecondicion("Vacio");
                    }

                    if (condicionActual.getTemperaturaf() == null) {
                        condicionActual.setTemperaturaf("null");
                    } else if (condicionActual.getTemperaturaf().equals("")) {
                        condicionActual.setTemperaturaf("Vacio");
                    }
                    if (condicionActual.getTemperaturac() == null) {
                        condicionActual.setTemperaturac("null");
                    } else if (condicionActual.getTemperaturac().equals("")) {
                        condicionActual.setTemperaturac("Vacio");
                    }
                    if (condicionActual.getHumedad() == null) {
                        condicionActual.setHumedad("null");
                    } else if (condicionActual.getHumedad().equals("")) {
                        condicionActual.setHumedad("Vacio");
                    }
                    if (condicionActual.getUrlicon() == null) {
                        condicionActual.setUrlicon("null");
                    } else if (condicionActual.getUrlicon().equals("")) {
                        condicionActual.setUrlicon("Vacio");
                    }
                    if (condicionActual.getViento() == null) {
                        condicionActual.setViento("null");
                    } else if (condicionActual.getViento().equals("")) {
                        condicionActual.setViento("Vacio");
                    }

                    //cadena a enviar
                    datos = new String();//falta agregarle el icono condicionActual.getUrlicon() + "&"
                    datos = ciudad.getNombreCiudad() + "&" + condicionActual.getNombrecondicion() + "&" + condicionActual.getTemperaturaf() + "&"
                            + condicionActual.getTemperaturac() + "&" + condicionActual.getHumedad() + "&" + condicionActual.getViento();


                    output.writeUTF(datos);
                    for (Pronostico pronostico : pronosticos) {
                        System.out.println("entra a recorrer pronosticos");
                        datos2 = pronostico.getDia() + "&" + pronostico.getTemplow() + "&" + pronostico.getTemphigh() + "&" + pronostico.getCondicion() + "&" + pronostico.getIconurl();
                        output.writeUTF(datos2);
                    }

                }
                output.close();


            } else if (parametro.equalsIgnoreCase(UPDATE_PERFIL)) {
                perfil = new Perfil();
                String id = request.getParameter("id");
                String condicion = request.getParameter("condicion");
                String tempF = request.getParameter("tempF");
                System.out.println("tempF" + tempF);
                String tempC = request.getParameter("tempC");
                String humedad = request.getParameter("humedad");
                System.out.println("humedad " + humedad);
                String viento = request.getParameter("viento");
                String dias = request.getParameter("dias");
                AccesoBD access = new AccesoBD();
                String validaUsuario = access.updatePerfil(perfilEJB, ejbPerfiCaracteristica, ejbCaracteristica, id, condicion, tempF, tempC, humedad, viento, dias);
                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeUTF(validaUsuario);
                output.close();
            } else if (parametro.equalsIgnoreCase(CHANGE_PASS)) {
                perfil = new Perfil();
                String id = request.getParameter("id");
                String passP = request.getParameter("passPrevius");
                String pass = request.getParameter("pass");
                AccesoBD access = new AccesoBD();
                perfil = perfilEJB.findForId(id);
                if (perfil != null) {
                    String validaUsuario = access.changePass(perfilEJB, perfil, passP, pass);
                    DataOutputStream output = new DataOutputStream(response.getOutputStream());
                    System.out.println("respuesta change pass " + validaUsuario);
                    output.writeUTF(validaUsuario);
                    output.close();
                } else {
                    DataOutputStream output = new DataOutputStream(response.getOutputStream());
                    System.out.println("respuesta change pass " + "perfil no encontrado");
                    output.writeUTF("id de perfil incorrecto");
                    output.close();
                }
            } else if (parametro.equalsIgnoreCase(CERRAR_SESION)) {
                perfil = new Perfil();
                String id = request.getParameter("id");
                perfil = perfilEJB.find(Integer.parseInt(id));
                Date date = new Date();
                Registro registro = new Registro();
                registro.setAccion(ejbAccion.find(2));
                registro.setPerfil(perfil);
                registro.setFecha(date);
                registro.setHora(date);
                registro.setInformacionAdicional("el usuario " + perfil.getLogin() + " cerro sesion");
                ejbRegistro.create(registro);
                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeUTF("SE CERRO SESION CORRECTAMENTE");
                output.close();
            }
        } finally {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
