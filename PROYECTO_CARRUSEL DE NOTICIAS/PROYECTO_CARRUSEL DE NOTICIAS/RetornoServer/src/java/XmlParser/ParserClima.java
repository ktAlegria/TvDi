/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import modelClima.Current_Conditions;
import modelClima.Forecast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author GUSTAVO_AL
 */
public class ParserClima {

    private URL url;
    private HttpURLConnection conn;
    private String dirCon = "http://www.google.com/ig/api?weather=";
    private List<Forecast> forecasts = new ArrayList<Forecast>();
    Current_Conditions currenCon = new Current_Conditions();

    public ParserClima(List<String> caracteristicas, int numeroDias, String lugar) {
        try {
//            las siguientes 4 lineas para la validacion
//            java.util.Properties sysProps = System.getProperties();
//            sysProps.put("proxySet", "true");
//            sysProps.put("proxyHost", "proxy.unicauca.edu.co");
//            sysProps.put("proxyPort", "3128");

            this.url = new URL(dirCon + lugar + "&hl=es");
            System.out.println("url: " + this.url);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStreamReader isr = new InputStreamReader(url.openStream(), "ISO-8859-1");
            BufferedReader br = new BufferedReader(isr);
            InputSource inputSource = new InputSource(isr);
            Document document = builder.parse(inputSource);

            document.getDocumentElement().normalize();
            NodeList currentConditions = document.getElementsByTagName("current_conditions");
            Node timeCurrent = (Element) currentConditions.item(0);
            if (timeCurrent.getNodeType() == Node.ELEMENT_NODE) {

                Element timeElementCurrent = (Element) timeCurrent;

                for (String caract : caracteristicas) {
                    NodeList nodeList = timeElementCurrent.getElementsByTagName(caract);
                    Element element = (Element) nodeList.item(0);
                    System.out.println("nodeList " + nodeList);
                    if (nodeList != null) {
                        if (caract.equals("condition")) {
                            currenCon.setCondicion(element.getAttribute("data"));
                        } else if (caract.equals("temp_f")) {
                            currenCon.setTemperaturaF(element.getAttribute("data"));
                        } else if (caract.equals("temp_c")) {
                            currenCon.setTemperaturaC(element.getAttribute("data"));
                        } else if (caract.equals("humidity")) {
                            currenCon.setHumedad(element.getAttribute("data"));
                        } else if (caract.equals("icon")) {
                            currenCon.setUrlIcon(element.getAttribute("data"));
                        } else if (caract.equals("wind_condition")) {
                            try {
                                currenCon.setViento(element.getAttribute("data"));
                            } catch (Exception e) {
                                System.out.println("excepcion " + e);
                            }
                        }
                    }

                }

                /*
                NodeList currentConditionsList = timeElementCurrent.getElementsByTagName("condition");
                Element conditionCurren = (Element) currentConditionsList.item(0);
                currenCon.setCondicion(conditionCurren.getAttribute("data"));

                NodeList temp_fList = timeElementCurrent.getElementsByTagName("temp_f");
                Element temp_f = (Element) temp_fList.item(0);
                currenCon.setTemperaturaF(temp_f.getAttribute("data"));

                NodeList temp_cList = timeElementCurrent.getElementsByTagName("temp_c");
                Element temp_c = (Element) temp_cList.item(0);
                currenCon.setTemperaturaC(temp_c.getAttribute("data"));

                NodeList humidityList = timeElementCurrent.getElementsByTagName("humidity");
                Element humidity = (Element) humidityList.item(0);
                currenCon.setHumedad(humidity.getAttribute("data"));

                NodeList iconCurrentList = timeElementCurrent.getElementsByTagName("icon");
                Element iconCurrent = (Element) iconCurrentList.item(0);
                currenCon.setUrlIcon(iconCurrent.getAttribute("data"));*/
            }

            NodeList times = document.getElementsByTagName("forecast_conditions");
            Forecast forecast;
            for (int i = 0; i <= numeroDias; i++) {
                Node time = (Element) times.item(i);


                if (time.getNodeType() == Node.ELEMENT_NODE) {
                    forecast = new Forecast();
                    Element timeElement = (Element) time;

                    //pongo dia de las semana
                    NodeList dayWeekList = timeElement.getElementsByTagName("day_of_week");
                    Element dayWeekElement = (Element) dayWeekList.item(0);
                    forecast.setNombreDia(dayWeekElement.getAttribute("data"));

                    NodeList lowTempList = timeElement.getElementsByTagName("low");
                    Element lowTempElement = (Element) lowTempList.item(0);
                    forecast.setTempLow(Integer.valueOf(lowTempElement.getAttribute("data")));

                    NodeList hightTempList = timeElement.getElementsByTagName("high");
                    Element hightTempElement = (Element) hightTempList.item(0);
                    forecast.setTempHight(Integer.valueOf(hightTempElement.getAttribute("data")));

                    NodeList iconList = timeElement.getElementsByTagName("icon");
                    Element iconElement = (Element) iconList.item(0);
                    forecast.setIconURL(iconElement.getAttribute("data"));

                    NodeList condList = timeElement.getElementsByTagName("condition");
                    Element condElement = (Element) condList.item(0);
                    forecast.setCondicion(condElement.getAttribute("data"));
                    forecasts.add(forecast);
                }

            }


        } catch (Exception ex) {
            Logger.getLogger(ParserClima.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public Current_Conditions getCurrenCon() {
        return currenCon;
    }
}
