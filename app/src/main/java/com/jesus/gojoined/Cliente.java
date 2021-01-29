package com.jesus.gojoined;

/**
 * Created by jesus on 13/11/2016.
 */
public class Cliente {
    private int id;
    private String empresa;
    private String nombre;
    private String apellido;    
    private String telefono;
    private String direccion;
    private String cp;
    private String ciudad;
    private float longitud;
    private float latitud;

    public Cliente(){

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Cliente(int id, String nombre, String apellido, String empresa, String telefono, String direccion, String cp, String ciudad, float longitud, float latitud){
        this.id=id;
        this.nombre=nombre;
        this.telefono=telefono;
        this.apellido=apellido;
        this.empresa=empresa;
        this.telefono=telefono;

        this.direccion=direccion;
        this.cp=cp;
        this.ciudad=ciudad;
        this.longitud=longitud;
        this.latitud=latitud;
    }
}
