package com.cibertec.syscharla.Interfaces;



import com.cibertec.syscharla.Clases.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Producto_I {
    @GET("api/Producto/ListProductoxId/{IDProducto}")
    Call<Producto> getListaProductoxId(@Path("IDProducto") int IDProducto);

}
