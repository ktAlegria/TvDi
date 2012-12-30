/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbClima;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.text.StyledEditorKit.BoldAction;
import model.Caracteristica;
import model.Ciudad;
import model.Condicionactual;
import model.Perfil;
import model.PerfilCaracteristica;
import model.PerfilCiudad;
import model.Pronostico;

/**
 *
 * @author William
 */
public class AccesoBD {

    @EJB
    PerfilCiudadFacadeLocal ejbPerfiCiudad;
    @EJB
    PerfilCaracteristicaFacadeLocal ejbPerfiCaracteristica;
    @EJB
    PerfilFacadeLocal perfilEJB;
    @EJB
    CondicionactualFacadeLocal condicionActualEJB;
    @EJB
    PronosticoFacadeLocal pronosticoEJB;
    @EJB
    CaracteristicaFacadeLocal caracteristicaEJB;

    public AccesoBD() {
    }

    public List<Ciudad> consultarCiudadesPerfil(Perfil perfil, PerfilCiudadFacadeLocal pcfEJB) {
        ejbPerfiCiudad = pcfEJB;
        List<Ciudad> listaCiudades = new ArrayList<Ciudad>();
        listaCiudades.clear();
        List<PerfilCiudad> listaPerfilCiudad = ejbPerfiCiudad.findAll();
        for (PerfilCiudad perfilCiudad : listaPerfilCiudad) {
            if (perfilCiudad.getPerfil().equals(perfil)) {
                listaCiudades.add(perfilCiudad.getCiudad());
            }
        }
        return listaCiudades;

    }

    public List<Caracteristica> consultarCaracteristicasPerfil(Perfil perfil, PerfilCaracteristicaFacadeLocal pcacEJB) {
        ejbPerfiCaracteristica = pcacEJB;
        List<Caracteristica> listaCaracteristica = new ArrayList<Caracteristica>();
        List<PerfilCaracteristica> listaPerfilCaracteristica = ejbPerfiCaracteristica.findAll();
        for (PerfilCaracteristica perfilCaracteristica : listaPerfilCaracteristica) {
            if (perfilCaracteristica.getPerfil().equals(perfil)) {
                listaCaracteristica.add(perfilCaracteristica.getCaracteristica());
            }
        }
        return listaCaracteristica;
    }

    public int validaUsuario(PerfilFacadeLocal ejbPer, String login, String password) {
        Boolean isActive = true;
        int id = 0;
        if (isActive) {
            perfilEJB = ejbPer;
            List<Perfil> perfiles = perfilEJB.findAll();
            for (Perfil perfil : perfiles) {
                if (login.equals(perfil.getLogin())) {
                    if (password.equals(perfil.getPassword())) {
                        id = perfil.getSecPerfil();
                    }
                }
            }
        }
        return id;
    }

    public Condicionactual getCondicionActual(PerfilFacadeLocal perfilEJB, CondicionactualFacadeLocal ejbCon, Ciudad city) {
        this.perfilEJB = perfilEJB;
        this.condicionActualEJB = ejbCon;
        Condicionactual cond = new Condicionactual();
        List<Condicionactual> condiciones = new ArrayList<Condicionactual>();
        condiciones = condicionActualEJB.findAll();
        System.out.println(condiciones);
        for (Condicionactual condicionactual : condiciones) {
            System.out.println("perfil a consultar: " + condicionactual.getCiudad().getSecCiudad());
            System.out.println("id de referencia: " + city.getSecCiudad());
            if (condicionactual.getCiudad().equals(city)) {
                System.out.println("obtuvo la condicion actual");
                cond = condicionactual;
            }
        }
        return cond;
    }

    public Perfil perfilById(int sec, PerfilFacadeLocal ejbPer) {

        perfilEJB = ejbPer;
        Perfil find = perfilEJB.find(sec);
        System.out.println("encontro el perfil de: " + find.getLogin());
        return find;
    }

    public List<Pronostico> consultarPronosticosPerfil(Condicionactual condiAct, PronosticoFacadeLocal ejbPronostico) {
        pronosticoEJB = ejbPronostico;
        List<Pronostico> listPronostico = new ArrayList<Pronostico>();
        List<Pronostico> todos = pronosticoEJB.findAll();
        for (Pronostico pronostico : todos) {
            if (pronostico.getCondicionactual().equals(condiAct)) {
                listPronostico.add(pronostico);
            }

        }
        return listPronostico;
    }

    public int getNumeroPronosticos(PerfilCaracteristicaFacadeLocal ejbPerfiCaracteristica, Perfil per) {
        int numberDias = 0;
        this.ejbPerfiCaracteristica = ejbPerfiCaracteristica;
        List<PerfilCaracteristica> findAll = this.ejbPerfiCaracteristica.findAll();
        for (PerfilCaracteristica perfilCaracteristica : findAll) {
            if (perfilCaracteristica.getPerfil().equals(per)) {
                if (perfilCaracteristica.getCaracteristica().getSecCaracteristica() == 7) {
                    numberDias = 1;
                } else if (perfilCaracteristica.getCaracteristica().getSecCaracteristica() == 8) {
                    numberDias = 2;
                } else if (perfilCaracteristica.getCaracteristica().getSecCaracteristica() == 9) {
                    numberDias = 3;
                }
            }
        }
        return numberDias;
    }

    public String updatePerfil(PerfilFacadeLocal perfilEJB, PerfilCaracteristicaFacadeLocal ejbPerfiCaracteristica, CaracteristicaFacadeLocal ejbCaracteristica, String id, String condicion, String tempF, String tempC, String humedad, String viento, String dias) {
        this.perfilEJB = perfilEJB;
        this.ejbPerfiCaracteristica = ejbPerfiCaracteristica;
        this.caracteristicaEJB = ejbCaracteristica;
        try {   
            Perfil perfil = perfilEJB.find(Integer.parseInt(id));
            boolean cond = Boolean.parseBoolean(condicion);
            boolean tF = Boolean.parseBoolean(tempF);
            boolean tC = Boolean.parseBoolean(tempC);
            boolean hume = Boolean.parseBoolean(humedad);
            boolean vien = Boolean.parseBoolean(viento);
            int di = Integer.parseInt(dias);
            //elimino las caracteristicas anteriores
            List<PerfilCaracteristica> perfilCaracteristica = ejbPerfiCaracteristica.findAll();
            for (PerfilCaracteristica perfilCaracteristica1 : perfilCaracteristica) {
                if (perfilCaracteristica1.getPerfil().equals(perfil)) {
                    ejbPerfiCaracteristica.remove(perfilCaracteristica1);
                }
            }
            PerfilCaracteristica perCarac = new PerfilCaracteristica();
            if (cond) {
                System.out.println("entra con");
                perCarac.setCaracteristica(caracteristicaEJB.find(1));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }
            if (tF) {
                System.out.println("entra tf");
                perCarac.setCaracteristica(caracteristicaEJB.find(2));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }
            if (tC) {
                System.out.println("entra tc");
                perCarac.setCaracteristica(caracteristicaEJB.find(3));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }
            if (hume) {
                System.out.println("entra hume");
                perCarac.setCaracteristica(caracteristicaEJB.find(4));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }
            if (vien) {
                System.out.println("entra vien");
                perCarac.setCaracteristica(caracteristicaEJB.find(6));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }
            if (di == 2) {
                perCarac.setCaracteristica(caracteristicaEJB.find(7));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            } else if (di == 3) {
                perCarac.setCaracteristica(caracteristicaEJB.find(8));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            } else if (di == 4) {
                perCarac.setCaracteristica(caracteristicaEJB.find(9));
                perCarac.setPerfil(perfil);
                ejbPerfiCaracteristica.create(perCarac);
            }

            return "PERFIL ACTUALIZADO";
        } catch (Exception e) {
            return "ERROR ACTUALIZANDO PERFIL";
        }
    }

    public String changePass(PerfilFacadeLocal ejbPerfil,Perfil perfil, String passP, String pass) {
        this.perfilEJB = ejbPerfil;
        if (perfil.getPassword().equals(passP)) {
            String updatePassword = perfilEJB.updatePassword(String.valueOf(perfil.getSecPerfil()), pass);
            if(updatePassword.equals("0")){
                return "No Actualizado";
            }else{
                return "Actualizado Correctamente";
            }
        }else{
            return "Password Anterior Incorrecto";
        }
    }
}
