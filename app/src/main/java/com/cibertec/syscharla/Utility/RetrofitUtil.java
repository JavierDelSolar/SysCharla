package com.cibertec.syscharla.Utility;

import com.cibertec.syscharla.Interfaz.CharlaApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Retrofit retrofit;

     private static CharlaApi charlaApi;
    //private static String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static String BASE_URL_CHARLA = "https://appcharla.azurewebsites.net/api/Charla/";
    private static String BASE_URL_PRODUCTO = "https://appcharla.azurewebsites.net/api/Producto/";
    private static String BASE_URL_USUARIO = "https://appcharla.azurewebsites.net/api/Usuario/";
    private static String BASE_URL_INTERES = "https://appcharla.azurewebsites.net/api/Interes/";

    private static String BASE_TABLE_CHARLA = "CHARLA";
    private static String BASE_TABLE_USUARIO = "USUARIO";
    private static String BASE_TABLE_PRODUCTO = "PRODUCTO";
    private static String BASE_TABLE_INTERES = "INTERES";


    public static Retrofit getRetrofitCharla(String BASE_TABLE){
        if(retrofit == null){
            String BASE = "";
            switch (BASE_TABLE) {
                case "CHARLA": BASE = BASE_TABLE_CHARLA;
                case "USUARIO": BASE = BASE_TABLE_USUARIO;
                case "PRODUCTO": BASE = BASE_TABLE_PRODUCTO;
                case "INTERES": BASE = BASE_TABLE_INTERES;
                break;
            }
            //Creamos retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    //CHARLA
    public static CharlaApi getCharlaApi(){
        if(charlaApi == null) {
            charlaApi = getRetrofitCharla(BASE_TABLE_CHARLA).create(CharlaApi.class);
        }
        return charlaApi;
    }


    //USUARIO


}
