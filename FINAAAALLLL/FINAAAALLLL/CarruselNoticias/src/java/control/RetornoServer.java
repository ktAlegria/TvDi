/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ejb.CarruselFacade;
import ejb.CodigoFacade;
import ejb.NoticiaFacade;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import model.Codigo;
import model.Noticia;

/**
 *
 * @author johanna
 */
@WebServlet(name = "RetornoServer", urlPatterns = {"/RetornoServer"})
public class RetornoServer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    CodigoFacade ejbCodigo;
    @EJB
    NoticiaFacade ejbNoticia;
    @EJB
    CarruselFacade ejbCarruse;
    public static final String FUNC_FECHA = "fecha";
    public static final String FUNC_NOTICIA = "noticia";
    public static final String VALIDAR_USUARIO = "val";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String parametro = request.getParameter("func");
            String mac;

            String user = "ninguno";
            System.out.println("entra peticion de: " + parametro);

            if (parametro.equalsIgnoreCase(FUNC_FECHA)) {
                mac = request.getParameter("mac");
                List<Codigo> codigos = ejbCodigo.findAll();
                for (Codigo codigo : codigos) {
                    if (codigo.getMac().equals(mac)) {
                        user = codigo.getUsuario().getIdusuario().toString();
                    }
                }
                if (!user.equals("ninguno")) {
                    DataOutputStream output = new DataOutputStream(response.getOutputStream());
                    output.writeUTF(user);
                    output.close();
                } else {
                    DataOutputStream output = new DataOutputStream(response.getOutputStream());
                    output.writeUTF(new Date().toString());
                    output.close();
                }
            } else if (parametro.equalsIgnoreCase(VALIDAR_USUARIO)) {
                int respons = 1;
                String clave = request.getParameter("clave");
                String mac2 = request.getParameter("mac");
                System.out.println("mac " + mac2);
                List<Codigo> codigos = ejbCodigo.findAll();
                for (Codigo codigo : codigos) {
                    if (codigo.getClave().equals(clave) && codigo.getEstadocodigo() == false) {
                        codigo.setMac(mac2);
                        codigo.setEstadocodigo(true);
                        ejbCodigo.edit(codigo);
                        respons = codigo.getIdcodigo();
                    }
                }


                DataOutputStream output = new DataOutputStream(response.getOutputStream());
                output.writeByte(respons);
                output.close();
            } else if (parametro.equalsIgnoreCase(FUNC_NOTICIA)) {
                String idNoticia = request.getParameter("id");
                int position = 0;
                try {
                    //Entrada
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/glassfishv3/glassfish/domains/domain2/Position"));
                    //Buscar si existe una palabra
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        position = Integer.parseInt(line);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("position " + position);
                Integer obtenerNoticia = ejbCarruse.obtenerNoticia(position);
                System.out.println("obtener noticia " + obtenerNoticia);
                Noticia noticia = ejbNoticia.find(obtenerNoticia);

                DataOutputStream output = new DataOutputStream(response.getOutputStream());

                String titulo = noticia.getTitulo();
                output.writeUTF(titulo);

                String version = noticia.getVersion().toString();
                output.writeUTF(version);

                


                String urlimagen = noticia.getUrlimagen();
                //prueba de obtener imagen
                String destinationFile = "image.gif";
                URL url = new URL(urlimagen);
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destinationFile);
                byte[] b = new byte[2048];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                os.close();
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

                String descripcion = noticia.getDescripcion();
                output.writeUTF(descripcion);

                String fecha = noticia.getFechapublicacion().toString();
                output.writeUTF(fecha);


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
