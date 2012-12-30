/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.unicauca.tdi.scene;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.dvb.ui.DVBColor;
import org.havi.ui.*;
import org.havi.ui.HComponent;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HKeyListener;
import org.university.unicauca.tdi.app.AppXlet;
import org.university.unicauca.tdi.communication.Comunicacion;
import org.university.unicauca.tdi.communication.EscuchadorCom;
import org.university.unicauca.tdi.model.Escena;
import org.university.unicauca.tdi.model.vo.Respuesta;

/**
 *
 * @author Administrador
 */
public class HS_descripcion extends HComponent implements HKeyListener, EscuchadorCom, Escena, HActionListener {

    private XletContext ctx;
    private HScene scene;
    private AppXlet principal;
    // COMPONENTES DE LA ESCENA DE INGRESO
    private static final String LB_SIGNIN_MSG = "Titulo Noticia";
    private static final String LB_SIGNIN_NAME = "Version 1.0";
    private static final String LB_SIGIN_DESC = "Google anuncio que desde esta semana los vehiculos de Street "
            + "View recorreran las principales ciudades del pais, con el objetivo de recolectar imagenes."
            + "el proyecto pretende mejorar los mapas que se visualizaran a traves de google maps y que con el "
            + "servicio de recorridos callejeros. ofreceran vistas de 360 grados.";
    private static final String LB_SIGNIN_DATE = "Martes 26 de Julio de 2012";
    private HStaticText lbMsg;
    private HStaticText lbName;
    private HStaticText lbPass;
    private HStaticText lbSiginDate;
    private HStaticText lbLoading;
    private static final String BT_SIGNIN = "Ingresar";
    private HMultilineEntry lbDescription;
    private HSinglelineEntry txName;
    private HSinglelineEntry txPass;
    private HTextButton btSignin;
    private Respuesta respuesta;
    private boolean textBox;//true:login,false:password    
    private HStaticIcon newsImage;
    private Image image1 = null;
    private HStaticIcon rojoButton;
    private Image image2 = null;
    private HStaticIcon verdeButton;
    private Image image3 = null;
    
    private static final String idNoticia = "2";

    public void cleaner() {
        scene.removeKeyListener(this);
        scene.removeAll();
        scene.repaint();
    }

    public void initializer(AppXlet principal) {
        textBox = true;
        this.ctx = principal.getContext();
        this.scene = principal.getScene();
        this.principal = principal;
        Comunicacion com = new Comunicacion(EscuchadorCom.FUCN_NOTICIA + "&id=" + idNoticia, this);
        com.getNoticia();

    }

    private void get_resources() {
        image1 = null;
        try {
            MediaTracker mediaTracker = new MediaTracker(scene);
            image1 = Toolkit.getDefaultToolkit().createImage(respuesta.getImagen());
            mediaTracker.addImage(image1, 0);
            mediaTracker.waitForAll();
        } catch (InterruptedException ex) {
            System.out.println("ERROR CARGANDO IMAGEN");
            ex.printStackTrace();
        }
    }

    private void config_container() {
        // Configurando la informacion del Componente, es importante para poder
        // acceder al metodo nativo repaint
        Rectangle rect = scene.getBounds();
        setBounds(rect);
        setVisible(true);
        // A�adiendo el componente a la escena
        scene.add(this);
        // a�adiendo propiedades la escena
        scene.requestFocus();
        add_components();
        scene.repaint();
        scene.setVisible(true);

    }

    private void add_components() {
        
        scene.setBackgroundMode(HScene.BACKGROUND_FILL);
        scene.setBackground(new DVBColor(180, 195, 204, 240));
        
        lbMsg = new HStaticText(respuesta.getTitulo(), 180, 80, 360, 40);
        lbMsg.setBordersEnabled(false);
        lbMsg.setForeground(new DVBColor(255, 255, 255, 255));
        lbMsg.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbMsg.setBackground(new DVBColor(0, 0, 0, 255));
        lbMsg.setFont(new Font("Tiresias", Font.ITALIC, 30));
        
        
        lbName = new HStaticText("Versiòn " + respuesta.getVersion(), 580, 30, 120, 40);
        lbName.setBordersEnabled(false);
        lbName.setForeground(new DVBColor(255, 255, 255, 100));
        lbName.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbName.setBackground(new DVBColor(153, 8, 8, 255));
        lbName.setFont(new Font("Tiresias", Font.BOLD, 20));
        
        
        
        newsImage = new HStaticIcon(image1);
        newsImage.setBounds(30, 100, 370, 380);
        
        

        lbPass = new HStaticText(formatText(respuesta.getDescripcion(), 34), 410, 140, 300, 300);
        lbPass.setBordersEnabled(false);
        lbPass.setForeground(Color.white);
        lbPass.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbPass.setBackground(new DVBColor(138, 110, 215, 200));
        lbPass.setFont(new Font("Tiresias", Font.BOLD, 20));
        

        
        lbSiginDate = new HStaticText(respuesta.getFecha(), 200, 500, 300, 40);
        lbSiginDate.setBordersEnabled(false);
        lbSiginDate.setForeground(new DVBColor(255, 255, 255, 255));
        lbSiginDate.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbSiginDate.setBackground(new DVBColor(0, 0, 0, 255));
        lbSiginDate.setFont(new Font("Tiresias", Font.BOLD, 24));



        scene.add(lbMsg);
        scene.add(lbName);
        scene.add(newsImage);
        scene.add(lbPass);
        scene.add(lbSiginDate);
        scene.addKeyListener(this);

    }

    private void change_scene(String escena) {
        principal.setNomEscena(escena);
        cleaner();
        principal.getScene().removeAll();
        principal.pauseXlet();
        try {
            principal.startXlet();
        } catch (XletStateChangeException e) {
            e.printStackTrace();
        }
    }

    public void setLogMsg(String msg) {
        System.out.println("LOG SCENE(" + principal.getNomEscena() + "): "
                + msg);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent key) {
        System.out.println("key " + key.getKeyCode());
        switch (key.getKeyCode()) {                    
            case 403:// boton rojo
                System.out.println("entra");
                try {
                    principal.getContext().notifyDestroyed();
                    principal.destroyXlet(true);
                } catch (XletStateChangeException ex) {
                    ex.printStackTrace();
                }
                break;
            case 404:// boton verde
                
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void listoFechaServer(Respuesta respuesta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void isValid(Respuesta respuesta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public String formatText(String cadena,int incremento){
        int index;
        String cadenaFor = "";
        for(index = 0; index < cadena.length() ; index = index + incremento){
            if((index + incremento) < cadena.length()){
                String isSpace = cadena.substring(index, index + incremento);
                if(isSpace.equals(" ")){
                    cadenaFor = cadenaFor.concat(isSpace).concat("\n");
                }else{
                    cadenaFor = cadenaFor.concat(isSpace).concat("-\n");
                }
            }else{
                cadenaFor = cadenaFor.concat(cadena.substring(index));
            }
        }
        
        System.out.println("return " + cadenaFor);
        return cadenaFor;
    }

    public void listoNoticia(Respuesta respuesta) {
        this.respuesta = respuesta;
        get_resources();
        config_container();
    }
}
