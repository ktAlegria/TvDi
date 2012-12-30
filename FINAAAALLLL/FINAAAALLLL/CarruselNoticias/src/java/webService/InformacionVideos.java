/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webService;

import ejb.CarruselFacade;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import model.Carrusel;

/**
 *
 * @author GUSTAVO_AL
 */
@WebService()
public class InformacionVideos {
    /**
     * Web service operation
     */
    @EJB
    CarruselFacade ejbCarrusel;

    @WebMethod(operationName = "getCurrentNew")
    public String getCurrentNew() {
        List<Carrusel> findAll = ejbCarrusel.carruselOrderByPosition();
        
        String[] carru = new String[findAll.size() + 1];
        int pos;
        int i = 1;
        long duration;
        for (Carrusel carrusel : findAll) {
            int hours = carrusel.getNoticia().getDuracion().getHours();
            int minutes = carrusel.getNoticia().getDuracion().getMinutes();
            int seconds = carrusel.getNoticia().getDuracion().getSeconds();

            System.out.println("horas  " +  hours);
            duration = (hours * 3600) + (minutes * 60) + seconds;
            carru[i] = carrusel.getNombrevideo() + "%" + carrusel.getNombreaudio() + "%" + carrusel.getBrvideo() + "%" + carrusel.getBraudio() + "%" + duration;
            i++;
        }

        
        File miDir = new File (".");
        String absolutePath = miDir.getAbsolutePath();
        System.out.println(absolutePath);

        int position = 1;
        //TODO write your implementation code here:
        try {
            //Entrada
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Position"));
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
        int count = carru.length - 1;
        System.out.println("leng " + count);
        pos = position;
        if(position < count ){
            position ++;
        }else{
            position = 1;
        }

        try {
            //Escritura
            java.io.BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Position"));
            bufferedWriter.append(String.valueOf(position));
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("posicion " +  position);

        return carru[position];
    }
}
