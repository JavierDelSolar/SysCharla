package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Clases.UsuarioCharla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioCharla_I {

    @GET("api/UsuarioCharla/ListUsuarioCharlaxID/{IDUsuario}/{IDCharla}")
    Call<UsuarioCharla> getListUsuarioCharla(@Path("IDUsuario") int IDUsuario, @Path("IDCharla") int IDCharla);

    @POST("api/UsuarioCharla/InsertUsuarioCharla")
    Call<UsuarioCharla> InsertUsuarioCharla(@Body UsuarioCharla usuarioCharla);

}
