package com.cibertec.syscharla.Clases;

public class UsuarioCharla {

    private int IDUsuarioCharla;
    private int IDUsuario;
    private int IDCharla;
    private int Asistencia;
    private String Justificacion;

    public int getIDUsuarioCharla() {
        return IDUsuarioCharla;
    }

    public void setIDUsuarioCharla(int IDUsuarioCharla) {
        this.IDUsuarioCharla = IDUsuarioCharla;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public int getIDCharla() {
        return IDCharla;
    }

    public void setIDCharla(int IDCharla) {
        this.IDCharla = IDCharla;
    }

    public int getAsistencia() {
        return Asistencia;
    }

    public void setAsistencia(int asistencia) {
        Asistencia = asistencia;
    }

    public String getJustificacion() {
        return Justificacion;
    }

    public void setJustificacion(String justificacion) {
        Justificacion = justificacion;
    }
}
