package com.cibertec.syscharla.Clases;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private double costo;
    private String descripcion;
    private int estado;
    private int idFoto;

    public Producto(String nombre, double costo, String descripcion, int idFoto, int estado) {
        this.nombre = nombre;
        this.costo = costo;
        this.descripcion = descripcion;
        this.estado =   estado;
        this.idFoto = idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() { return costo; }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }


}
