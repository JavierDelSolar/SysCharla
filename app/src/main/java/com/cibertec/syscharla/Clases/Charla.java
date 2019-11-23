package com.cibertec.syscharla.Clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Charla implements Serializable {

    private int IDCharla;
    private String Nombre;
    @SerializedName("Fecha")
    private String FechaHora;
    private String Direccion;
    private String Latitud;
    private String Longitud;
    private String Descripcion;
    private String Observacion;
    private String Foto;
    private String expositor;
    private boolean status;
    //private int idFoto;
    private int idFotoExpositor;



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

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
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

    public int getIdFotoExpositor() {
        return idFotoExpositor;
    }

    public void setIdFotoExpositor(int idFotoExpositor) {
        this.idFotoExpositor = idFotoExpositor;
    }

    public String getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(String fechaHora) {
        FechaHora = fechaHora;
    }

    public int getIDCharla() {
        return IDCharla;
    }

    public void setIDCharla(int IDCharla) {
        this.IDCharla = IDCharla;
    }
}
