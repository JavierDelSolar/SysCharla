package com.cibertec.syscharla;

import com.cibertec.syscharla.Clases.Charla;
import com.cibertec.syscharla.Clases.Usuario;

public class Variables {

    private static Variables instance;
    public static Variables getInstance()
    {
        if(instance == null)
        {
            instance = new Variables();
        }
        return  instance;
    }

    public Usuario usuario;
    public Charla charla;

}
