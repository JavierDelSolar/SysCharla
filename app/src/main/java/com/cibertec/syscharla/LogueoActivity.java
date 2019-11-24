package com.cibertec.syscharla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogueoActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnIngresar;
    TextView tvRegistrate;
    TextInputEditText tie_Email;
    TextInputEditText tie_Password;
    ImageView ivLogo;
    Variables objVar = Variables.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        btnIngresar = (Button) findViewById(R.id.btn_Ingresar);
        tvRegistrate = (TextView) findViewById(R.id.tv_Registrate);
        tie_Email = (TextInputEditText) findViewById(R.id.tie_Email);
        tie_Password = (TextInputEditText) findViewById(R.id.tie_Password);
        ivLogo = (ImageView)findViewById(R.id.ivLogo);


        btnIngresar.setOnClickListener(this);
        tvRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btnIngresar.getId()) {
            ValidarUsuario();

        } else if (v.getId() == tvRegistrate.getId()) {
            // Toast.makeText(this,"entro", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Registro1Activity.class);
            startActivity(intent);
        }
    }

    private void ValidarUsuario() {

        // validar datos
        if (tie_Email.getText().toString().length() == 0) {
            Toast.makeText(this, "Ingrese Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (tie_Password.getText().toString().length() == 0) {
            Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
            Call<Usuario> call = usuario_i.Loginusuario(tie_Email.getText().toString(), tie_Password.getText().toString());
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        objVar.usuario = response.body();
                        if(objVar.usuario != null) {
                            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(getApplicationContext(), "Usuario no Existe.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario no Existe.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
