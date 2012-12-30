package org.university.unicauca.tdi.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;

import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSinglelineEntry;
import org.havi.ui.HStaticIcon;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HKeyListener;
import org.university.unicauca.tdi.app.AppXlet;
import org.university.unicauca.tdi.communication.Comunicacion;
import org.university.unicauca.tdi.communication.DireccionMac;
import org.university.unicauca.tdi.communication.EscuchadorCom;
import org.university.unicauca.tdi.model.Escena;
import org.university.unicauca.tdi.model.vo.Respuesta;

public class HS_welcome extends HComponent implements Escena, HKeyListener,
        HActionListener, EscuchadorCom {

    private XletContext ctx;
    private HScene scene;
    private AppXlet principal;
    // COMPONENTES DE LA ESCENA DE INGRESO
    private static final String LB_SIGNIN_MSG = "Informaciòn de Suscripcion";
    private static final String LB_SIGNIN_NAME = "Serial: ";
    private static final String LB_SIGIN_DATE = "Fecha de ingreso: ";
    private static final String LB_SIGIN_LOADING = "Cargando.... ";
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
    private String mac;

    public void cleaner() {
        btSignin.removeHActionListener(this);
        txName.removeHKeyListener(this);
        scene.removeKeyListener(this);
        scene.removeAll();
        scene.repaint();
    }

    public void initializer(AppXlet principal) {
        textBox = true;
        this.ctx = principal.getContext();
        this.scene = principal.getScene();
        this.principal = principal;
        DireccionMac dir = new DireccionMac();
        mac = "";
        try {
            mac = dir.getMacAddress();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Comunicacion com = new Comunicacion(EscuchadorCom.FUNC_FECHA + "&mac=" + mac, this);
        get_resources();
        com.getFechaServer();



    }

    private void get_resources() {
        // nothing...
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
        scene.setBackground(Color.black);

        lbMsg = new HStaticText(LB_SIGNIN_MSG, 30, 260, 580, 40);
        lbMsg.setBordersEnabled(false);
        lbMsg.setForeground(Color.white);
        lbMsg.setFont(new Font("Tiresias", Font.BOLD, 24));

        lbSiginDate = new HStaticText(LB_SIGIN_DATE + respuesta.getFechaServer(), 30, 530, 580, 40);
        lbSiginDate.setBordersEnabled(false);
        lbSiginDate.setForeground(Color.blue);
        lbSiginDate.setFont(new Font("Tiresias", Font.BOLD, 24));

        lbName = new HStaticText(LB_SIGNIN_NAME, 80, 280, 580, 70);
        lbName.setBordersEnabled(false);
        lbName.setForeground(Color.white);
        lbName.setFont(new Font("Tiresias", Font.BOLD, 24));

        txName = new HSinglelineEntry();
        txName.setBounds(220, 330, 300, 40);
        txName.setEnabled(true);
        txName.setTextContent("", HVisible.NORMAL_STATE);
        txName.setBordersEnabled(true);
        txName.setForeground(Color.blue);
        txName.setBackground(Color.white);
        txName.setBackgroundMode(HVisible.BACKGROUND_FILL);
        txName.setFont(new Font("Tiresias", Font.BOLD, 24));
        txName.setMaxChars(30);
        txName.setEditMode(true);
        txName.setHorizontalAlignment(HVisible.HALIGN_LEFT);

        btSignin = new HTextButton(BT_SIGNIN, 320, 470, 100, 40);
        btSignin.setTextContent(BT_SIGNIN, HVisible.FOCUSED_STATE);
        btSignin.setBordersEnabled(true);
        btSignin.setForeground(Color.white);
        btSignin.setBackground(Color.lightGray);
        btSignin.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btSignin.setFont(new Font("Tiresias", Font.BOLD, 24));

        scene.add(lbMsg);
        scene.add(lbSiginDate);
        scene.add(lbName);
        scene.add(txName);
        scene.add(btSignin);
        scene.addKeyListener(this);

        btSignin.addHActionListener(this);
        txName.addHKeyListener(this);
        txName.requestFocus();
    }

    private void focusTextBox() {
        if (textBox) {
            //txPass.removeHKeyListener(this);
            //txPass.setFocusable(false);
            txName.addHKeyListener(this);
            txName.requestFocus();
        } else {
            //txName.removeHKeyListener(this);
            //txName.setFocusable(false);
            txPass.addHKeyListener(this);
            txPass.requestFocus();
        }

    }

    private void change_scene(String escena) {
        principal.setNomEscena(escena);
        principal.getScene().removeAll();
        principal.pauseXlet();
        try {
            principal.startXlet();
        } catch (XletStateChangeException e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent key) {
        System.out.println("estoy en la ventana principal metodo keylistener: "
                + key.getKeyCode());
        switch (key.getKeyCode()) {
            case 10:
                btSignin.requestFocus();
                break;
            case 113:
                if (textBox) {
                    textBox = false;
                } else {
                    textBox = true;
                }
                focusTextBox();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println("mac " + mac);
        Comunicacion com = new Comunicacion(EscuchadorCom.VALIDAR_USUARIO + "&clave=" + txName.getTextContent(HVisible.ALL_STATES) + "&mac=" + mac, this);
        com.validaUsuario();
        //change_scene("HS_carrusel");
    }

    public void setLogMsg(String msg) {
        System.out.println("LOG SCENE(" + principal.getNomEscena() + "): "
                + msg);
    }

    public void listoFechaServer(Respuesta respuesta) {
        System.out.println("llego respuesta de fecha");
        int indexOf = respuesta.getFechaServer().indexOf(" ");
        if (indexOf != -1) {
            System.out.println("index " + indexOf);
            this.respuesta = respuesta;
            config_container();
        } else {
            change_scene("HS_carrusel");
        }


    }

    public void isValid(Respuesta respuesta) {
        System.out.println("llebo respuesta de validacion: " + respuesta.getId());
        this.respuesta = respuesta;
        if (respuesta.getId() == 0) {
            cleaner();
            config_container();
        } else {
            principal.setId(respuesta.getId());
            System.out.println("cambiar a carrusel");
            cleaner();
            change_scene("HS_carrusel");
        }
    }

    public void listoNoticia(Respuesta respuesta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
