/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.university.unicauca.tdi.communication;

/**
 *
 * @author Administrador
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.university.unicauca.tdi.model.vo.Respuesta;

public class Comunicacion {

    private URL url;
    private HttpURLConnection conn;
    private Respuesta respuesta;
    private EscuchadorCom escuchador;
    private String dirCon = "http://192.168.121.67:35696/CarruselNoticias/RetornoServer?func=";

    public Comunicacion(String func, EscuchadorCom escuchador) {
        try {
            System.out.println("------- ESCUCHADOR ES: " + escuchador);
            this.escuchador = escuchador;

            
            this.url = new URL(dirCon + func );

            inicializarParametrosConn();
            System.out.println("------- URL: " + this.url.toString());
        } catch (MalformedURLException ex) {
            System.out.println("------- ERROR CREANDO URL: " + this.url.toString());
        }
    }

    private void inicializarParametrosConn() {
        try {
            conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestProperty("Accept-Language", "es-ES");
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/text");
            conn.setRequestProperty("Accept", "application/text");
        } catch (IOException ex) {
            System.out.println("------- ERROR ABRIENDO CONEXION CON SERVIDOR");
        }
    }

    public void getFechaServer() {
        Thread hilo = new Thread() {

            public void run() {
                try {
                    conn.connect();
                    int code = conn.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        respuesta = new Respuesta();
                        InputStream input = conn.getInputStream();
                        DataInputStream datainput = new DataInputStream(input);
                        respuesta.setFechaServer(datainput.readUTF());
                        input.close();
                        datainput.close();
                        Thread.sleep(2000L);
                    } else {
                        System.out.println("--- CODIGO NO OK: " + code);
                    }
                    conn.disconnect();
                    conn = null;
                    escuchador.listoFechaServer(respuesta);
                } catch (Exception ex) {
                    System.out.println("ERROR OBTENIENDO FECHA:  " + ex);
                }
            }
        };
        hilo.start();
    }

    public void validaUsuario() {
        Thread hilo = new Thread() {

            public void run() {
                try {
                    conn.connect();
                    int code = conn.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        respuesta = new Respuesta();
                        InputStream input = conn.getInputStream();
                        DataInputStream datainput = new DataInputStream(input);
                        respuesta.setId(datainput.readByte());
                        input.close();
                        datainput.close();
                        Thread.sleep(2000L);
                    } else {
                        System.out.println("--- CODIGO NO OK: " + code);
                    }
                    conn.disconnect();
                    conn = null;
                    escuchador.isValid(respuesta);
                } catch (Exception ex) {
                    System.out.println("ERROR OBTENIENDO REPUESTA DE VALIDACION:  " + ex);
                }
            }
        };
        hilo.start();
    }

    public void getNoticia() {
        Thread hilo = new Thread() {

            public void run() {
                try {
                    conn.connect();
                    int code = conn.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        respuesta = new Respuesta();
                        InputStream input = conn.getInputStream();
                        DataInputStream datainput = new DataInputStream(input);
                        
                        String titulo = datainput.readUTF();
                        respuesta.setTitulo(titulo);
                        
                        String version = datainput.readUTF();
                        respuesta.setVersion(version);
                        
                        
                        int tam = datainput.readInt();
                        System.out.println("tamaño imagen: " + tam);
                        byte[] imagen = new byte[tam];
                        for (int i = 0; i < imagen.length; i++) {
                            imagen[i] = datainput.readByte();
                        }
                        respuesta.setImagen(imagen);
                        System.out.println("despues de imagen");
                        int read = datainput.read();
                        System.out.println("read " + read);
                        
                        String descripcion = datainput.readUTF();
                        respuesta.setDescripcion(descripcion);
                        
                        System.out.println("despues de descripcion");
                        
                        String fecha = datainput.readUTF();
                        respuesta.setFecha(fecha);
                                               
                        
                        input.close();
                        datainput.close();
                        Thread.sleep(2000L);
                    } else {
                        System.out.println("CODIGO DE RESPUESTA NO OK: " + code);
                    }
                    conn.disconnect();
                    conn = null;
                    escuchador.listoNoticia(respuesta);
                } catch (Exception ex) {
                    System.out.println("ERROR OBTENIENDO IMAGEN: " + ex);
                }
            }
        };
        hilo.start();
    }
}
