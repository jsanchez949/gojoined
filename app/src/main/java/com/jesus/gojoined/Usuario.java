package com.jesus.gojoined;

/**
 * Created by jesus on 03/06/2016.
 */
public class Usuario {
    private String login;
    private String nombre;
    private String apellido;
    private String telefono;
    private int tipousuario;
    private String tiposUsers[] = {"Usuario","","","","Trabajador","","","","","Administrador"};

    public String getTiposUsers(int posicion) {
        return tiposUsers[posicion];
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public int getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(int tipousuario) {
        this.tipousuario = tipousuario;
    }

    public Usuario(String login, String nombre, String apellido, String telefono, String password, int tipousuario){
        this.login=login;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.password=password;
        this.tipousuario=tipousuario;
    }
}
