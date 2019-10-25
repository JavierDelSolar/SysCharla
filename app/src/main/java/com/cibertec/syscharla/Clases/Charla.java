package com.cibertec.syscharla.Clases;

import java.util.Date;

public class Charla {
    //private int id;
    private String nombre;
    //private Date fecha;
    //private String direccion;
    //private String descripcion;
    //private boolean status;

    public Charla() {
    }

    public Charla(String nombre) {
        this.nombre = nombre;
    }

    /*
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/*
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

 */
}
