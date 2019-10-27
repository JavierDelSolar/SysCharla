package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro1Activity extends AppCompatActivity implements View.OnClickListener {

    Button btContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);

        btContinuar = (Button)findViewById(R.id.btn_Continuar);
        btContinuar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Registro2Activity.class);
        startActivity(intent);
    }
}
