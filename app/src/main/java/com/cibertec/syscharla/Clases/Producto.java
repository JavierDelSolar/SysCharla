package com.cibertec.syscharla.Clases;

import java.io.Serializable;

public class Producto implements Serializable {
    private int IDProducto;
    private String Nombre;
    private double Costo;
    private String Descripcion;
    private String Foto;
    private  int Interes;

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public int getInteres() {
        return Interes;
    }

    public void setInteres(int interes) {
        Interes = interes;
    }
}
