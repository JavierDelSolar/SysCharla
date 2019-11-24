package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImagenResultActivity extends AppCompatActivity {
    Variables objVar = Variables.getInstance();
    ImageView imageView5;
    String foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_result);
        imageView5 = findViewById(R.id.imageView5);
        if(objVar.usuario != null) {

            foto = objVar.usuario.getFoto();
            try {
                // Toast.makeText(getActivity(), objVar.usuario.getFoto(), Toast.LENGTH_SHORT).show();

                Picasso.with(this).load( foto).error(R.mipmap.perfil).into(imageView5);
            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }
}
