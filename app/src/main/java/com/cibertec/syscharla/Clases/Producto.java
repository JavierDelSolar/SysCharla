package com.cibertec.syscharla.Clases;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private String costo;
    private String descripcion;
    private String estado;
    private int idFoto;

    public Producto(String nombre, String costo, String descripcion, int idFoto, String estado) {
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

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }


}
