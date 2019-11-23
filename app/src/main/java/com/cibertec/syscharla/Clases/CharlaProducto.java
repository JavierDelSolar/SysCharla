package com.cibertec.syscharla.Clases;

import java.io.Serializable;

public class CharlaProducto implements Serializable {

    private int IDCharlaProducto;
    private int IDCharla;
    private int IDProducto;


    // REQUIERO DATOS DE LOS PRODCUTOS
    private Producto producto;

    // CAMPO NECESITO PARA MI LISTA DE INTERESES, NO ES PARTE DE LA TABLA.
    private int Interes;

    public int getIDCharlaProducto() {
        return IDCharlaProducto;
    }

    public void setIDCharlaProducto(int IDCharlaProducto) {
        this.IDCharlaProducto = IDCharlaProducto;
    }

    public int getIDCharla() {
        return IDCharla;
    }

    public void setIDCharla(int IDCharla) {
        this.IDCharla = IDCharla;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getInteres() {
        return Interes;
    }

    public void setInteres(int interes) {
        Interes = interes;
    }
}
