package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Usuario_I {

    @POST("api/usuario/insertusuario")
    Call<Usuario> InsertUsuario(@Body Usuario usuario);

    @GET("api/usuario/loginusuario?")
    Call<Usuario> Loginusuario(@Query("Correo") String Correo, @Query("Password") String Password);

    @PUT("api/usuario/updateusuario")
    Call<Usuario> updateusuario(@Body Usuario usuario);

    @PUT("api/usuario/UpdateUsuarioCamPassword")
    Call<Usuario> UpdateUsuarioCamPassword(@Body Usuario usuario);

    @PUT("api/usuario/UpdateUsuarioCamEmail")
    Call<Usuario> UpdateUsuarioCamEmail(@Body Usuario usuario);

    @GET("api/usuario/listusuarioxEmail?")
    Call<List<Usuario>> ListarUsuarioxEmail(@Query("Correo") String Correo);

}
