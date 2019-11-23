package com.cibertec.syscharla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.google.android.material.textfield.TextInputEditText;

public class Registro1Activity extends AppCompatActivity implements View.OnClickListener {

    Button btContinuar;
    TextInputEditText tieEmailRE;
    TextInputEditText tiePasswordRE1;
    TextInputEditText tiePasswordRE2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tieEmailRE = (TextInputEditText)findViewById(R.id.tie_EmailRE);
        tiePasswordRE1 = (TextInputEditText)findViewById(R.id.tie_PasswordRE1);
        tiePasswordRE2 = (TextInputEditText)findViewById(R.id.tie_PasswordRE2);
        btContinuar = (Button)findViewById(R.id.btn_Continuar);
        btContinuar.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if(v == btContinuar) {
            Usuario usuario = new Usuario();
            // VALIDAR DATOS
            if (tieEmailRE.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tiePasswordRE1.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tiePasswordRE2.getText().toString().length() == 0) {
                Toast.makeText(this, "Debe Repetir Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            if (tiePasswordRE1.getText().toString().equals(tiePasswordRE2.getText().toString()) == false) {
                Toast.makeText(this, "Contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                return;
            }

            // validar que Email no se repita.



            usuario.setCorreo(tieEmailRE.getText().toString());
            usuario.setClave(tiePasswordRE1.getText().toString());



            Bundle parmetros = new Bundle();
            parmetros.putSerializable("Usuario", usuario);

            Intent intent = new Intent(this, Registro2Activity.class);
            intent.putExtras(parmetros);
            startActivity(intent);
        }
    }
}
