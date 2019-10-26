package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cibertec.syscharla.Clases.Charla;

public class CharlaActivity extends AppCompatActivity {

    TextView tvTitulo, tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charla);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        Bundle bundle = getIntent().getExtras();
        Charla charla = null;
        if(bundle != null){
            charla = (Charla)bundle.getSerializable("charla");
            tvTitulo.setText(charla.getNombre());
            tvDescripcion.setText(charla.getDescripcion());

        }

    }
}
