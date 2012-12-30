/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelClima;

import java.util.Date;

/**
 *
 * @author GUSTAVO_AL
 */

//para poder alamacenar los pronosticos (forecast)
public class Forecast {


    //String ciudad = "";
    //Date fecha = null;
    int dia = 0; //representa el dia del pronostico 0=dia actual 1=siguiente dia ...
    String nombreDia = null;
    int tempLow = 0;
    int tempHight = 0;
    String condicion = null;
    String iconURL = null;


    public String getCondicion() {
        return condicion;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public int getTempHight() {
        return tempHight;
    }

    public void setTempHight(int tempHight) {
        this.tempHight = tempHight;
    }

    public int getTempLow() {
        return tempLow;
    }

    public void setTempLow(int tempLow) {
        this.tempLow = tempLow;
    }
    


}
