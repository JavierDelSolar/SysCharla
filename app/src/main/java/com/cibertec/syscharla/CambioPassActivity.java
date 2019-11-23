package com.cibertec.syscharla;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioPassActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edPassActual;
    EditText edNuevoPass;
    EditText edRepetirPass;
    Button btnGrabarCP;
    Variables objVar = Variables.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_pass);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        edPassActual = (EditText) findViewById(R.id.ed_PassActual);
        edNuevoPass = (EditText) findViewById(R.id.ed_NuevoPass);
        edRepetirPass = (EditText) findViewById(R.id.ed_RepetirPass);
        btnGrabarCP = (Button) findViewById(R.id.btn_GrabarCP);
        btnGrabarCP.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btnGrabarCP) {

            if (edPassActual.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Password Actual", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edNuevoPass.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Nueva Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edRepetirPass.getText().toString().length() == 0) {
                Toast.makeText(this, "Debe Repetir Contraseña", Toast.LENGTH_SHORT).show();
                return;
            }

            if (edNuevoPass.getText().toString().equals(edRepetirPass.getText().toString()) == false) {
                Toast.makeText(this, "Contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edPassActual.getText().toString().equals(objVar.usuario.getClave()) == false) {
                Toast.makeText(this, "La Contraseña actual ingresada no es correcto, Ingrese su Contraseña.", Toast.LENGTH_SHORT).show();
                return;
            }


            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("CAMBIO DE CONTRASEÑA");
            dialogo.setMessage("¿Esta seguro de cambiar contraseña.?");
            dialogo.setCancelable(false);
            dialogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                    Usuario usuario = new Usuario();
                    usuario.setIDUsuario(objVar.usuario.getIDUsuario());
                    usuario.setClave(edNuevoPass.getText().toString());

                    try {
                        Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
                        Call<Usuario> call = usuario_i.UpdateUsuarioCamPassword(usuario);
                        call.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                if (response.isSuccessful()) {
                                    //Toast.makeText(getApplicationContext(), "Actualización Exitosa", Toast.LENGTH_SHORT).show();
                                    objVar.usuario = response.body();
                                    Intent intent = new Intent(getApplicationContext(), LogueoActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "No se Actualizo", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialogo.setNegativeButton("CANCELAR",null);
            dialogo.show();
        }
    }
}
