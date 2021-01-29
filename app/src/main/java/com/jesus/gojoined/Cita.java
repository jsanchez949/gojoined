package com.jesus.gojoined;

/**
 * Created by jesus on 13/11/2016.
 */
public class Cita {
    private int id;
    private int usuarioid;
    private int clienteid;
    private String nombre;
    private String telefono;

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public int getClienteid() {
        return clienteid;
    }

    public void setClienteid(int clienteid) {
        this.clienteid = clienteid;
    }

    private String tarea;
    private String fecha;
    private String cobro;

    public Cita(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getCobro() {
        return cobro;
    }

    public void setCobro(String cobro) {
        this.cobro = cobro;
    }

    public Cita(int id,int usarioid,int clienteid, String nombre, String telefono, String fecha, String tarea, String cobro){
        this.id=id;
        this.usuarioid=usuarioid;
        this.clienteid=clienteid;
        this.nombre=nombre;
        this.telefono=telefono;
        this.fecha=fecha;
        this.tarea=tarea;
        this.cobro=cobro;
    }
}
