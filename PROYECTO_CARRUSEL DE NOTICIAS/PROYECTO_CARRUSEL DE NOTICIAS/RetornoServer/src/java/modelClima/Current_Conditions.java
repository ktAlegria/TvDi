/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelClima;

/**
 *
 * @author GUSTAVO_AL
 */
public class Current_Conditions {

    String condicion = null;
    String temperaturaF = null;
    String temperaturaC = null;
    String humedad = null;
    String urlIcon = null;
    String viento = null;

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getTemperaturaC() {
        return temperaturaC;
    }

    public void setTemperaturaC(String temperaturaC) {
        this.temperaturaC = temperaturaC;
    }

    public String getTemperaturaF() {
        return temperaturaF;
    }

    public void setTemperaturaF(String temperaturaF) {
        this.temperaturaF = temperaturaF;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getViento() {
        return viento;
    }

    public void setViento(String viento) {
        this.viento = viento;
    }

    
}
