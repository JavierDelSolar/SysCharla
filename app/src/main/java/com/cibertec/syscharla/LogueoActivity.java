package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogueoActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        btnIngresar = (Button)findViewById(R.id.btn_Ingresar);
        btnIngresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}
