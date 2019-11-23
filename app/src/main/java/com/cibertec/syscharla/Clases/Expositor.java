package com.cibertec.syscharla.Clases;

import java.io.Serializable;

public class Expositor  implements Serializable {
    private int IDExpositor;
    private String Nombre;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String Descripcion;
    private String Correo;
    private String Foto;
    private String Telefono;

    public int getIDExpositor() {
        return IDExpositor;
    }

    public void setIDExpositor(int IDExpositor) {
        this.IDExpositor = IDExpositor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
