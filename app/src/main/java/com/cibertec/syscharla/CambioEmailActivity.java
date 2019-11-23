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
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioEmailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView edEmailCE;
    TextView edRepetirEmailCE;
    Button btnGrabarCP;
    Variables objVar = Variables.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_email);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        edEmailCE = (TextView) findViewById(R.id.ed_EmailCE);
        edRepetirEmailCE = (TextView) findViewById(R.id.ed_RepetirEmailCE);
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
            if (edEmailCE.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese Nuevo Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (edRepetirEmailCE.getText().toString().length() == 0) {
                Toast.makeText(this, "Repetir Nuevo Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (edEmailCE.getText().toString().equals(edRepetirEmailCE.getText().toString()) == false) {
                Toast.makeText(this, "Email no coinciden.", Toast.LENGTH_SHORT).show();
                return;
            }

            //VERIFICAR QUE EMAIL NO EXISTA.
            try {
                Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
                Call<List<Usuario>> call = usuario_i.ListarUsuarioxEmail(edEmailCE.getText().toString());
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if (response.isSuccessful()) {
                            List<Usuario> lista = response.body();
                            if (lista != null) {
                                if (lista.size() > 0) {
                                    Toast.makeText(getApplicationContext(), "Correo ya se encuentra Registrado.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "GRABAR.", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder dialogo = new AlertDialog.Builder(CambioEmailActivity.this);
                                    dialogo.setTitle("CAMBIO DE EMAIL.");
                                    dialogo.setMessage("¿Esta seguro de cambiar EMAIL.?");
                                    dialogo.setCancelable(false);
                                    dialogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Usuario usuario = new Usuario();
                                            usuario.setIDUsuario(objVar.usuario.getIDUsuario());
                                            usuario.setCorreo(edEmailCE.getText().toString());

                                            try {
                                                Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
                                                Call<Usuario> call = usuario_i.UpdateUsuarioCamEmail(usuario);
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
                                    }).setNegativeButton("CANCELAR", null);
                                    dialogo.show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Lista es NULL", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
