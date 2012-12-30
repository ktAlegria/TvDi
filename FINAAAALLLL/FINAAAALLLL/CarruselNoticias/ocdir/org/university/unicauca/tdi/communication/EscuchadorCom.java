/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.university.unicauca.tdi.communication;

import org.university.unicauca.tdi.model.vo.Respuesta;

/**
 *
 * @author Administrador
 */

public interface EscuchadorCom {

    public static final String FUNC_FECHA = "fecha";
    public static final String VALIDAR_USUARIO = "val";
    public static final String FUCN_NOTICIA = "noticia";

    public void listoFechaServer(Respuesta respuesta);
    public void listoNoticia(Respuesta respuesta);
    public void isValid(Respuesta respuesta);
}
