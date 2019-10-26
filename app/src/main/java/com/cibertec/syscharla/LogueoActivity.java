package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogueoActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnIngresar;
    TextView tvRegistrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        btnIngresar = (Button)findViewById(R.id.btn_Ingresar);
        tvRegistrate = (TextView)findViewById(R.id.tv_Registrate);
        btnIngresar.setOnClickListener(this);
        tvRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnIngresar) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }else if(v == tvRegistrate){
            Intent intent = new Intent(this, Registro1Activity.class);
            startActivity(intent);
        }
    }
}
