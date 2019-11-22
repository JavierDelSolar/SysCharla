package com.cibertec.syscharla.Utility;

import com.cibertec.syscharla.Interfaz.CharlaApi;
import com.cibertec.syscharla.Interfaz.InteresApi;
import com.cibertec.syscharla.Interfaz.ProductoApi;
import com.cibertec.syscharla.Interfaz.UsuarioApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static Retrofit retrofit;

    private static CharlaApi charlaApi;
    private static UsuarioApi usuarioApi;
    private static ProductoApi productoApi;
    private static InteresApi interesApi;

    //private static String BASE_URL = "https://jsonplaceholder.typicode.com";
//    private static String BASE_URL_CHARLA   = "http://192.168.1.62/api/Charla/";
//    private static String BASE_URL_PRODUCTO = "http://192.168.1.62/api/Producto/";
//    private static String BASE_URL_USUARIO  = "http://192.168.1.62/api/Usuario/";
//    private static String BASE_URL_INTERES  = "http://192.168.1.62/api/Interes/";

    private static String BASE_URL_CHARLA   = "https://appcharla.azurewebsites.net//api/Charla/";
    private static String BASE_URL_PRODUCTO = "https://appcharla.azurewebsites.net//api/Producto/";
    private static String BASE_URL_USUARIO  = "https://appcharla.azurewebsites.net//api/Usuario/";
    private static String BASE_URL_INTERES  = "https://appcharla.azurewebsites.net//api/Interes/";

    private static String BASE_TABLE_CHARLA = "CHARLA";
    private static String BASE_TABLE_USUARIO = "USUARIO";
    private static String BASE_TABLE_PRODUCTO = "PRODUCTO";
    private static String BASE_TABLE_INTERES = "INTERES";


    public static Retrofit getRetrofit(String BASE_TABLE){


        if(retrofit == null){
            String BASE = "";
            switch (BASE_TABLE) {
                case "CHARLA": BASE = BASE_URL_CHARLA; break;
                case "USUARIO": BASE = BASE_URL_USUARIO; break;
                case "PRODUCTO": BASE = BASE_URL_PRODUCTO;break;
                case "INTERES": BASE = BASE_URL_INTERES;break;
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
            charlaApi = getRetrofit(BASE_TABLE_CHARLA).create(CharlaApi.class);
        }
        return charlaApi;
    }


    //USUARIO

    public static UsuarioApi getUsuarioApi(){
        if(usuarioApi == null) {
            usuarioApi = getRetrofit(BASE_TABLE_USUARIO).create(UsuarioApi.class);
        }
        return usuarioApi;
    }

    //PRODUCTO

    public static ProductoApi getProductoApi(){
        if(productoApi == null) {
            productoApi = getRetrofit(BASE_TABLE_PRODUCTO).create(ProductoApi.class);
        }
        return productoApi;
    }


    //INTERES

    public static InteresApi getInteresApi(){
        if(interesApi == null) {
            interesApi = getRetrofit(BASE_TABLE_INTERES).create(InteresApi.class);
        }
        return interesApi;
    }
}
