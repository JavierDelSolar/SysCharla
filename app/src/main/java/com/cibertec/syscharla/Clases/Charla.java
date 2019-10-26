package com.cibertec.syscharla.Clases;

import java.io.Serializable;
import java.util.Date;

public class Charla implements Serializable {
//    private int id;
//    private String nombre;
//    //private Date fecha;
//    //private String direccion;
//    private String descripcion;
//    //private boolean status;

    private int id;
    private String nombre;
    private Date fechahora;
    private String direccion;
    private String descripcion;
    private String expositor;
    private boolean status;
    private int idFoto;


    public Charla() {
    }

    public Charla(int id, String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Charla(String nombre, String descripcion,String expositor,Date fechahora, int idFoto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.setExpositor(expositor);
        this.fechahora = fechahora;
        this.setIdFoto(idFoto);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
