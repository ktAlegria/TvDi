/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lectorclima;

import XmlParser.ParserClima;
import ejbClima.AccesoBD;
import ejbClima.CondicionactualFacadeLocal;
import ejbClima.PerfilCaracteristicaFacadeLocal;
import ejbClima.PerfilCiudadFacadeLocal;
import ejbClima.PerfilFacadeLocal;
import ejbClima.PronosticoFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import model.Caracteristica;
import model.Ciudad;
import model.Condicionactual;
import model.Perfil;
import model.Pronostico;
import modelClima.Current_Conditions;
import modelClima.Forecast;

/**
 *
 * @author GUSTAVO_AL
 */
public class InformacionClimatica {

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
    private AccesoBD accesoBD = new AccesoBD();
    List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
    List<Ciudad> ciudades = new ArrayList<Ciudad>();
    int numeroDias = 0;
    List<String> caracteristics = new ArrayList<String>();

    public InformacionClimatica() {
    }

    public void actualizarInfoClima(Perfil perfil, PerfilFacadeLocal ejbP, CondicionactualFacadeLocal conEJB, PronosticoFacadeLocal proEJB, PerfilCiudadFacadeLocal pcEJB, PerfilCaracteristicaFacadeLocal pcaEJB) {

        perfilEJB = ejbP;
        condicionActualEJB = conEJB;
        pronosticoEJB = proEJB;
        System.out.println("este" + perfil.getSecPerfil());
        perfilEJB.noActualizar(perfil.getSecPerfil());
        if (perfil.getActivo()) {
            caracteristicas = accesoBD.consultarCaracteristicasPerfil(perfil, pcaEJB);
            ciudades = accesoBD.consultarCiudadesPerfil(perfil, pcEJB);
            for (Caracteristica caracteristica : caracteristicas) {

                if (caracteristica.getNombreCaracteristica().equals("1dia")) {
                    numeroDias = 1;
                } else if (caracteristica.getNombreCaracteristica().equals("2dias")) {
                    numeroDias = 2;
                } else if (caracteristica.getNombreCaracteristica().equals("3dias")) {
                    numeroDias = 3;
                } else {
                    caracteristics.add(caracteristica.getNombreCaracteristica());
                }
            }

            for (Ciudad ciudad : ciudades) {
                System.out.println("ciudad: " + ciudad.getNombreCiudad());
                ParserClima infoClima = new ParserClima(caracteristics, numeroDias, ciudad.getNombreCiudad());
                Current_Conditions currenCon = infoClima.getCurrenCon();
                List<Forecast> forecasts1 = infoClima.getForecasts();

                //elimino pronosticos primero para que no haya problema con la base de datos

                List<Pronostico> pronos = new ArrayList<Pronostico>();
                pronos = pronosticoEJB.findAll();
                for (Pronostico pronostico : pronos) {
                    if (pronostico.getCondicionactual().getPerfil().equals(perfil) && pronostico.getCondicionactual().getCiudad().equals(ciudad)) {
                        System.out.println("entra remover");
                        pronosticoEJB.remove(pronostico);
                    }
                }

                //condicion actual a registrar en la base de datos
                List<Condicionactual> condiciones = new ArrayList<Condicionactual>();
                condiciones = condicionActualEJB.findAll();
                for (Condicionactual condicionactual : condiciones) {
                    int i1 = condicionactual.getPerfil().getSecPerfil();
                    int i2 = perfil.getSecPerfil();
                    System.out.println("i1: " + i1);
                    System.out.println("i2: " + i2);
                    if (i1 == i2 && condicionactual.getCiudad().equals(ciudad)) {
                        System.out.println("entra al igual");
                        condicionActualEJB.remove(condicionactual);
                    }
                }
                Condicionactual condicionActual = new Condicionactual();
                condicionActual.setCiudad(ciudad);
                Date fecha = new Date();
                condicionActual.setFecha(fecha);
                condicionActual.setHumedad(currenCon.getHumedad());
                condicionActual.setNombrecondicion(currenCon.getCondicion());
                condicionActual.setPerfil(perfil);
                condicionActual.setTemperaturac(currenCon.getTemperaturaC());
                condicionActual.setTemperaturaf(currenCon.getTemperaturaF());
                condicionActual.setUrlicon(currenCon.getUrlIcon());
                condicionActual.setViento(currenCon.getViento());
                condicionActualEJB.create(condicionActual);

                //registro de los prononsticos

                Pronostico pronostico = new Pronostico();
                for (Forecast pronostic : forecasts1) {
                    pronostico.setCondicionactual(condicionActual);
                    pronostico.setCondicion(pronostic.getCondicion());
                    pronostico.setDia(pronostic.getNombreDia());
                    pronostico.setFecha(fecha);
                    pronostico.setIconurl(pronostic.getIconURL());
                    pronostico.setTemphigh(String.valueOf(pronostic.getTempHight()));
                    pronostico.setTemplow(String.valueOf(pronostic.getTempLow()));
                    pronosticoEJB.create(pronostico);
                }


                for (Forecast forecast : forecasts1) {
                    System.out.println("*************************************************");
                    System.out.println("pronostico para el dia: " + forecast.getNombreDia());
                    System.out.println("" + forecast.getCondicion());
                    System.out.println("temp Baja" + forecast.getTempLow());
                }
                System.out.println("__________________________");
                System.out.println("condicion actual: " + currenCon.getCondicion());
                System.out.println("temp_f: " + currenCon.getTemperaturaF());
                System.out.println("temp_c: " + currenCon.getTemperaturaC());
                System.out.println("humidity: " + currenCon.getHumedad());
                System.out.println("icon: " + currenCon.getUrlIcon());
                System.out.println("wind_condition: " + currenCon.getViento());

            }
        }

    }
}
