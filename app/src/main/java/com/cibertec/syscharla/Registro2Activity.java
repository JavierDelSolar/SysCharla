package com.cibertec.syscharla;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cibertec.syscharla.Clases.Usuario;
import com.cibertec.syscharla.Interfaces.Usuario_I;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro2Activity extends AppCompatActivity implements View.OnClickListener {


    ImageButton ibCamaraRE;
    Button btn_RegistrarRE;
    TextInputEditText tieNombresRE;
    TextInputEditText tieApellidosRE;
    TextInputEditText tieNroCelularRE;
    TextInputEditText tieDireccionRE;
    //de.hdodenhof.circleimageview.CircleImageView

    Usuario usuario = null;

    de.hdodenhof.circleimageview.CircleImageView ivFoto;

    private static final int REQUEST_TOMAR_FOTO = 100;
    private static final int REQUEST_PERMISO_CAMARA = 200;
    private static final int REQUEST_CONFIGURACION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //obtener parametros.
        Bundle parametros = this.getIntent().getExtras();
        usuario = (Usuario)parametros.getSerializable("Usuario");


        btn_RegistrarRE = (Button) findViewById(R.id.btn_RegistrarRE);
        tieNombresRE = (TextInputEditText) findViewById(R.id.tie_NombresRE);
        tieApellidosRE = (TextInputEditText) findViewById(R.id.tie_ApellidosRE);
        tieNroCelularRE = (TextInputEditText) findViewById(R.id.tie_NombresRE);
        tieDireccionRE = (TextInputEditText) findViewById(R.id.tie_DireccionRE);


        ibCamaraRE = (ImageButton) findViewById(R.id.ibCamaraRE);
        ivFoto = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.ivFotoDP);
        ibCamaraRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbrirCamara();
            }
        });
        btn_RegistrarRE.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TOMAR_FOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgeBitmap = (Bitmap) extras.get("data");
            ivFoto.setImageBitmap(imgeBitmap);
        }
    }
    private void AbrirCamara()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //Si se necesita mostrar alguna explicación

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // me han denegado el permiso una vez
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Abrir Camara").setMessage("Es necesario aceptar el persmiso")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SolicitarPermisoCamara();
                            }
                        }).setNegativeButton("Cancelar", null);
                builder.show();
            } else
                SolicitarPermisoCamara();

        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_TOMAR_FOTO);
            }
        }
    }
    private void SolicitarPermisoCamara() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISO_CAMARA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISO_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AbrirCamara();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA);
                    if (!showRationale) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                                .setTitle("Abrir Configuración")
                                .setMessage("Si desea usar esta funcionalidad, gestione el permiso.")
                                .setPositiveButton("Abrir Configuración", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AbrirConfiguracion();
                                    }
                                }).setNegativeButton("Cancelar", null);
                        builder.show();
                    }else{
                        Toast.makeText(this,"Requerimos el permiso para tomar fotos.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private void AbrirConfiguracion() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CONFIGURACION);
    }

    @Override
    public void onClick(View v) {
        if(v == btn_RegistrarRE){

            // validar registros
            if(tieNombresRE.getText().toString().length() == 0){
                Toast.makeText(this,"Ingrese Nombres",Toast.LENGTH_SHORT).show();
                return;
            }
            if(tieApellidosRE.getText().toString().length() == 0){
                Toast.makeText(this,"Ingrese Apellidos",Toast.LENGTH_SHORT).show();
                return;
            }
//            if(tieNroCelularRE.getText().toString().length() == 0){
//                Toast.makeText(this,"Ingrese Nro. Celular",Toast.LENGTH_SHORT);
//                return;
//            }
            if(tieDireccionRE.getText().toString().length() == 0){
                Toast.makeText(this,"Ingrese Dirección",Toast.LENGTH_SHORT).show();
                return;
            }
            RegistrarUsuario();
        }
    }
//    private String convertToString()
//    {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,60,byteArrayOutputStream);
//        byte[] imgByte = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgByte,Base64.DEFAULT);
//    }

    public  String convertImageToBase64(Bitmap bm) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        assert bm != null;
        bm.compress(Bitmap.CompressFormat.JPEG, 60, bos);
        byte[] data = bos.toByteArray();
        return Base64.encodeToString(data, Base64.DEFAULT + '"');
    }
    private void RegistrarUsuario() {

   /*     progressDialog = new ProgressDialog(RetrofitActivity6.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();*/
        // obtener imagen
        String image = "";
        try {
            Bitmap bitmap = ((BitmapDrawable) ivFoto.getDrawable()).getBitmap();
            image = convertImageToBase64(bitmap);

        }catch(Exception ex){}
        if(usuario != null) {
            int retval = 0;
            usuario.setNombres(tieNombresRE.getText().toString());
            usuario.setApellidos(tieApellidosRE.getText().toString());
            usuario.setDireccion(tieDireccionRE.getText().toString());
            usuario.setLatitud("");
            usuario.setLongitud("");
            usuario.setTipoUsuario(2);
            usuario.setFoto(image);
            usuario.setCelular(tieNroCelularRE.getText().toString());

            Usuario_I usuario_i = RetrofitClient.getClient().create(Usuario_I.class);
            Call<Usuario> call = usuario_i.InsertUsuario(usuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),LogueoActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        /*call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
               // progressDialog.dismiss();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Post submitted Title: "+response.body(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario_I> call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });*/


    }
}
