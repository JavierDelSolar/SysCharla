package com.cibertec.syscharla.Interfaz;
import com.cibertec.syscharla.Clases.Charla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface CharlaApi {

    @GET("ListCharla")
    Call<List<Charla>> getCharla();




}
