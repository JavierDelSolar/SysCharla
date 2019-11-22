package com.cibertec.syscharla.Clases;

import java.io.Serializable;
import java.util.Date;

public class Charla implements Serializable {


    public int getIDCharla() {
        return IDCharla;
    }

    public void setIDCharla(int IDCharla) {
        this.IDCharla = IDCharla;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
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

    public int getCupos() {
        return Cupos;
    }

    public void setCupos(int cupos) {
        Cupos = cupos;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdFotoExpositor() {
        return idFotoExpositor;
    }

    public void setIdFotoExpositor(int idFotoExpositor) {
        this.idFotoExpositor = idFotoExpositor;
    }

    public Charla(int IDCharla, String nombre, String fecha, String direccion, String latitud, String longitud, int cupos, String foto, String descripcion, String observacion, String base64String) {
        this.IDCharla = IDCharla;
        Nombre = nombre;
        Fecha = fecha;
        Direccion = direccion;
        Latitud = latitud;
        Longitud = longitud;
        Cupos = cupos;
        Foto = foto;
        Descripcion = descripcion;
        Observacion = observacion;
        this.base64String = base64String;
    }

    private int IDCharla;
    private String Nombre;
    private String Fecha ;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private int Cupos;
    private String Foto;
    private String Descripcion;
    private String Observacion;
    private String base64String;
    private String expositor;
    private boolean status;
    private int idFoto;
    private int idFotoExpositor;


}
