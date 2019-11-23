package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.Interes;
import com.cibertec.syscharla.Clases.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Interes_I {

    @POST("api/Interes/InsertInteres")
    Call<Interes> InsertInteres(@Body Interes interes);

    @DELETE("api/Interes/DeleteInteres/{IDUsuario}/{IDCharlaProducto}")
    Call<Interes> DeleteInteres(@Path("IDUsuario") int IDUsuario, @Path("IDCharlaProducto") int IDCharlaProducto);

}
