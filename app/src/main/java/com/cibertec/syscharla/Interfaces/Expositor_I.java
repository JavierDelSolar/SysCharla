package com.cibertec.syscharla.Interfaces;

import com.cibertec.syscharla.Clases.Expositor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Expositor_I {

    @GET("api/ExpositorCharla/ListExpositorCharlaId/{IDCharla}")
    Call<List<Expositor>> getListaExpositorCharlaId(@Path("IDCharla") int IDCharla);

}
