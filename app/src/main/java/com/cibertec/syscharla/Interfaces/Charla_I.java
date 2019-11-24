package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Charla_I {

    @GET("api/Charla/ListCharlaActuales")
    Call<List<Charla>> getListCharlaActuales();

    @GET("api/Charla/{IDCharla}")
    Call<Charla> getCharlaxId(@Path("IDCharla") int IDCharla);


    @GET("api/Charla/ListarMisCharlasxFechaxOrden/{IDUsuario}/{Tipo}/{Fecha}/{OrderBy}")
    Call<List<Charla>> getListrMisCharlasxFechaxOrden(@Path("IDUsuario") int IDUsuario, @Path("Tipo") String Tipo, @Path("Fecha") String Fecha, @Path("OrderBy") int OrderBy);


    @GET("api/Charla/ListarCharlaxNombre?")
    Call<List<Charla>> getListarCharlaxNombre(@Query("Nombre") String Nombre);


    @GET("api/Charla/ListarMisCharlasxFechaxOrdenxNombre/{IDUsuario}/{Tipo}/{Fecha}/{OrderBy}?")
    Call<List<Charla>> ListarMisCharlasxFechaxOrdenxNombre(@Path("IDUsuario") int IDUsuario, @Path("Tipo") String Tipo, @Path("Fecha") String Fecha, @Path("OrderBy") int OrderBy, @Query("Nombre") String Nombre);


}
