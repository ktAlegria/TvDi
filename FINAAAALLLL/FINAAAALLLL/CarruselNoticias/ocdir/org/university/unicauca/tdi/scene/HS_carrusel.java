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
public class HS_carrusel extends HComponent implements HKeyListener, EscuchadorCom, Escena, HActionListener {

    private XletContext ctx;
    private HScene scene;
    private AppXlet principal;
    // COMPONENTES DE LA ESCENA DE INGRESO
    private static final String LB_SIGNIN_MSG = "Ver Descripcion";
    private static final String LB_SIGNIN_NAME = "Duracion: ";
    private static final String LB_SIGIN_DATE = "Salir";
    private HStaticText lbMsg;
    private HStaticText lbName;
    private HStaticText lbPass;
    private HStaticText lbSiginDate;
    private HStaticText lbLoading;
    private static final String BT_SIGNIN = "Ingresar";
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
        get_resources();
        config_container();


    }

    private void get_resources() {
        image1 = null;
        try {
            MediaTracker mediaTracker = new MediaTracker(scene);
            image1 = Toolkit.getDefaultToolkit().createImage("org/university/unicauca/tdi/res/news.png");
            image2 = Toolkit.getDefaultToolkit().createImage("org/university/unicauca/tdi/res/rojo.jpeg");
            image3 = Toolkit.getDefaultToolkit().createImage("org/university/unicauca/tdi/res/verde.jpg");
            mediaTracker.addImage(image1, 0);
            mediaTracker.addImage(image2, 1);
            mediaTracker.addImage(image3, 2);
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
        scene.setBackgroundMode(HScene.NO_BACKGROUND_FILL);
        
        scene.setBackground(null);
        
        newsImage = new HStaticIcon(image1);
        newsImage.setBounds(400, 20, 80, 80);

        lbMsg = new HStaticText(LB_SIGNIN_MSG, 480, 40, 180, 40);
        lbMsg.setBordersEnabled(false);
        lbMsg.setForeground(Color.white);
        lbMsg.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbMsg.setBackground(new DVBColor(7, 67, 137, 255));
        lbMsg.setFont(new Font("Tiresias", Font.BOLD, 24));

        verdeButton = new HStaticIcon(image3);
        verdeButton.setBounds(660, 40, 40, 40);

        lbSiginDate = new HStaticText(LB_SIGNIN_NAME, 30, 520, 120, 40);
        lbSiginDate.setBordersEnabled(false);
        lbSiginDate.setForeground(Color.blue);
        lbSiginDate.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbSiginDate.setBackground(new DVBColor(7, 67, 137, 255));
        lbSiginDate.setFont(new Font("Tiresias", Font.BOLD, 24));

        rojoButton = new HStaticIcon(image2);
        rojoButton.setBounds(560, 520, 40, 40);

        lbLoading = new HStaticText(LB_SIGIN_DATE, 600, 520, 100, 40);
        lbLoading.setBordersEnabled(false);
        lbLoading.setForeground(Color.blue);
        lbLoading.setBackgroundMode(HStaticText.BACKGROUND_FILL);
        lbLoading.setBackground(new DVBColor(7, 67, 137, 255));
        lbLoading.setFont(new Font("Tiresias", Font.BOLD, 24));



        scene.add(newsImage);
        scene.add(lbMsg);
        scene.add(verdeButton);
        scene.add(lbSiginDate);
        scene.add(lbLoading);
        scene.add(rojoButton);
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
            case 403:// boton rojo f5
                System.out.println("entra");
                try {
                    principal.getContext().notifyDestroyed();
                    principal.destroyXlet(true);
                } catch (XletStateChangeException ex) {
                    ex.printStackTrace();
                }
                break;
            case 404:// boton verde f6
                change_scene("HS_descripcion");
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

    public void listoNoticia(Respuesta respuesta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
