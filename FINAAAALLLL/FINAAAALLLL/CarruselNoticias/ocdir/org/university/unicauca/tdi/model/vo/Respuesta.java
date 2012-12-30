/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.university.unicauca.tdi.model.vo;

/**
 *
 * @author Administrador
 */
public class Respuesta {

    private String fechaServer;
    private String titulo;
    private String version;
    private byte[] imagen;
    private String descripcion;
    private String fecha;
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFechaServer() {
        return fechaServer;
    }

    public void setFechaServer(String fechaServer) {
        this.fechaServer = fechaServer;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    


    

}
