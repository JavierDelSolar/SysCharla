package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.CharlaProducto;
import com.cibertec.syscharla.Clases.UsuarioCharla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharlaProducto_I {

    @GET("api/CharlaProducto/ListCharlaProductoInteres/{IDUsuario}/{IDCharla}")
    Call<List<CharlaProducto>> ListarCharlaProductoInteres(@Path("IDUsuario") int IDUsuario, @Path("IDCharla") int IDCharla);
}
